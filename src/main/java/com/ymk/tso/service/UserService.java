package com.ymk.tso.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.ymk.tso.controller.rest.UserController;
import com.ymk.tso.dao.UserDao;
import com.ymk.tso.data.LoginData;
import com.ymk.tso.data.UserData;
import com.ymk.tso.exception.RoleAuthException;
import com.ymk.tso.exception.RoleAuthException.RESOURCE_CANNOT_BE_ACCESSED_REASON;
import com.ymk.tso.util.ConnectionUtil;
import com.ymk.tso.util.Utility;

/**
 * UserService class - service on user actions
 * @author yemyokyaw
 *
 */
public class UserService implements AppService<UserData> {

	Logger logger = Logger.getLogger(UserController.class);

	private static UserService instance = new UserService();

	/**
	 * singleton structure
	 * @return
	 */
	public static UserService getInstance() {
		return instance;
	}

	private UserService() {
	}

	/**
	 * signIn method for user login
	 * @param login
	 * @return
	 */
	public LoginData signIn(LoginData login) {
		try (Connection con = ConnectionUtil.getConnection()) {
			LoginData data = UserDao.getInstance().signIn(login, con);
			if (data.getId() != 0) {
				data.setUsername(login.getUsername());
				data.setPassword(login.getPassword());
				String auth = Utility.generateAuth(data.getUsername(), data.getPassword());
				data.setAuth(auth);
			}
			logger.info("User Signing in with: " + login.getUsername());
			return data;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * getAll method to extract all user data
	 * @param val
	 * @param key
	 * @param auth
	 * @return
	 */
	@Override
	public List<UserData> getAll(String val, String key, String auth) {
		
		checkAuthStr(auth);
		String[] authParts = Utility.decodeAuth(auth).split(":");

		try (Connection con = ConnectionUtil.getConnection()) {
			checkAuthUser(con, key, authParts);
			
			long checker = Long.parseLong(key);
			
			short role = UserDao.getInstance().accessRole(checker, con);

			// check to block the permission of User not to access other users
			isUser(role);
			
			
			
			// check the role of Manager to retrieve the data from same department
			if(role != 2) {
				checker = 0;
			}

			logger.info("User List Extracted by user > id : " + key);
			
			return UserDao.getInstance().getAll(val, checker, con);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * getById method to get user by id
	 * @param id
	 * @param key
	 * @param auth
	 * @return
	 */
	@Override
	public UserData getById(long id, String key, String auth) {

		checkAuthStr(auth);
		String[] authParts = Utility.decodeAuth(auth).split(":");

		try (Connection con = ConnectionUtil.getConnection()) {
			checkAuthUser(con, key, authParts);
			
			logger.info("User:" + id + " Data Extracted by user > id : " + key);
			return UserDao.getInstance().getById(id, con);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * save method to store new data
	 * @param data
	 * @param key
	 * @param auth
	 * @return
	 */
	@Override
	public UserData save(UserData data, String key, String auth) {

		checkAuthStr(auth);
		String[] authParts = Utility.decodeAuth(auth).split(":");

		try (Connection con = ConnectionUtil.getConnection()) {
			checkAuthUser(con, key, authParts);

			short role = UserDao.getInstance().accessRole(Long.parseLong(key), con);

			// check the role permission of Admin role to add new users...
			isAdmin(role);

			logger.info("New User Saved by user > id : " + key);
			
			return UserDao.getInstance().save(data, con);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * update method to change existing user data
	 * @param id
	 * @param data
	 * @param key
	 * @param auth
	 * @return
	 */
	@Override
	public UserData update(long id, UserData data, String key, String auth) {

		checkAuthStr(auth);
		String[] authParts = Utility.decodeAuth(auth).split(":");

		try (Connection con = ConnectionUtil.getConnection()) {
			checkAuthUser(con, key, authParts);
			
			short role = UserDao.getInstance().accessRole(Long.parseLong(key), con);
			
			logger.info("User:" + id + " Updated by user > id : " + key);
			
			return UserDao.getInstance().update(id, data, role, con);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * delete method to update the status of user to 0 as deleted
	 * @param id
	 * @param key
	 * @param auth
	 * @return
	 */
	@Override
	public int delete(long id, String key, String auth) {

		checkAuthStr(auth);
		String[] authParts = Utility.decodeAuth(auth).split(":");

		try (Connection con = ConnectionUtil.getConnection()) {
			
			checkAuthUser(con, key, authParts);
			
			short role = UserDao.getInstance().accessRole(Long.parseLong(key), con);

			// check the role permission of Admin role to delete new users...
			isAdmin(role);

			logger.info("User:" + id + " Deleted by user > id : " + key);
			return UserDao.getInstance().delete(id, con);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * clean method to permanently delete the user
	 * @param id
	 * @param key
	 * @param auth
	 * @return
	 */
	@Override
	public String clean(long id, String key, String auth) {

		checkAuthStr(auth);
		String[] authParts = Utility.decodeAuth(auth).split(":");

		try (Connection con = ConnectionUtil.getConnection()) {
			checkAuthUser(con, key, authParts);

			short role = UserDao.getInstance().accessRole(Long.parseLong(key), con);

			// check the role permission of Admin role to delete new users...
			isAdmin(role);

			UserDao.getInstance().clean(id, con);

			logger.info("User:" + id + " permanently deleted by user > id : " + key);
			return "user deleted";
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * checkAuthUser method to check the authenticated users
	 * @param con
	 * @param id
	 * @param keys
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public void checkAuthUser(Connection con, String id, String... keys) throws NumberFormatException, SQLException {
		if (!UserDao.getInstance().isAuthUser(Long.parseLong(id), keys[0], keys[1], con))
			throw new RoleAuthException(RESOURCE_CANNOT_BE_ACCESSED_REASON.AUTH_INVALID);
	}
	
	/**
	 * checkAuthStr mehtod to check the auth string
	 * @param auth
	 */
	public void checkAuthStr(String auth) {
		if (auth == null || auth.isEmpty())
			throw new RoleAuthException(RESOURCE_CANNOT_BE_ACCESSED_REASON.NO_AUTH);
	}

	/**
	 * isAdmin method to check Admin role access
	 * @param role
	 */
	public void isAdmin(short role) {
		if (role != 1)
			throw new RoleAuthException(RESOURCE_CANNOT_BE_ACCESSED_REASON.NON_ADMIN);
	}
	
	/**
	 * isUser method to bolck User level from access
	 * @param role
	 */
	public void isUser(short role) {
		if (role == 3)
			throw new RoleAuthException(RESOURCE_CANNOT_BE_ACCESSED_REASON.USER_HAS_NO_ACCESS);
	}

}
