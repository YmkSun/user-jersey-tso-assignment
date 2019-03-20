package com.ymk.tso.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ymk.tso.dao.UserDao;
import com.ymk.tso.data.UserData;
import com.ymk.tso.util.ConnectionUtil;

public class UserService implements AppService<UserData> {

	private static UserService instance = new UserService();

	public static UserService getInstance() {
		return instance;
	}

	private UserService() {
	}

	/**
	 * getAll().
	 */
	@Override
	public List<UserData> getAll(String val) {
		try (Connection con = ConnectionUtil.getConnection()) {
			return UserDao.getInstance().getAll(val, con);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public UserData getById(long id) {
		try (Connection con = ConnectionUtil.getConnection()) {
			return UserDao.getInstance().getById(id, con);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public UserData save(UserData data) {
		try (Connection con = ConnectionUtil.getConnection()) {
			return UserDao.getInstance().save(data, con);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public UserData update(long id, UserData data) {
		try (Connection con = ConnectionUtil.getConnection()) {
			return UserDao.getInstance().update(id, data, con);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int delete(long id) {
		try (Connection con = ConnectionUtil.getConnection()) {
			return UserDao.getInstance().delete(id, con);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public String clean(long id) {
		try (Connection con = ConnectionUtil.getConnection()) {
			UserDao.getInstance().clean(id, con);
			return "user deleted";
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
