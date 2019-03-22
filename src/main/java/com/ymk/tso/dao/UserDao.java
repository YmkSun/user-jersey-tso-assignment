package com.ymk.tso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ymk.tso.data.LoginData;
import com.ymk.tso.data.UserData;

/**
 * User Dao class
 * @author yemyokyaw
 *
 */
public class UserDao implements AppDao<UserData> {

	private final String TABLE_NAME = "tb_user";

	private static UserDao instance = new UserDao();

	/**
	 * singleton structure
	 * @return
	 */
	public static UserDao getInstance() {
		return instance;
	}

	private UserDao() {
	}

	/**
	 * signIn mehtod
	 * @param login
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public LoginData signIn(LoginData login, Connection con) throws SQLException {
		LoginData data = new LoginData();
		String sql = "SELECT id, role FROM " + TABLE_NAME + " WHERE username = ? AND password = ? AND status<>0 ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, login.getUsername());
		stmt.setString(2, login.getPassword());
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			data.setId(rs.getLong("id"));
			data.setRole(rs.getShort("role"));
		}
		return data;
	}

	/**
	 * getAll method
	 * @param id
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<UserData> getAll(String val, long id, Connection con) throws SQLException {

		List<UserData> dataList = new ArrayList<>();
		UserData data;

		String sql = "SELECT id, username, full_name, email, phone, department, role FROM " + TABLE_NAME;
		String whereClause = " WHERE status<>0 ";
		
		if(id != 0) 
			whereClause += " AND department IN (SELECT department from " + TABLE_NAME + " WHERE id = ? ) "; 

		if (!val.isEmpty())
			whereClause += " AND (username LIKE %?% OR full_name LIKE %?% email LIKE %?% ";

		PreparedStatement stmt = con.prepareStatement(sql + whereClause);

		int order = 0;
		
		if(id != 0) {
			stmt.setLong(1, id);
			order++;
		}
		
		if (!val.isEmpty()) {
			stmt.setString(order+1, val);
			stmt.setString(order+2, val);
			stmt.setString(order+3, val);
		}

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			data = new UserData();
			data.setId(rs.getLong("id"));
			data.setUsername(rs.getString("username"));
			data.setFullName(rs.getString("full_name"));
			data.setEmail(rs.getString("email"));
			data.setPhone(rs.getString("phone"));
			data.setDept(rs.getInt("department"));
			data.setRole(rs.getShort("role"));
			dataList.add(data);
		}

		return dataList;
	}
	
	/**
	 * getById method
	 * @param data
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	@Override
	public UserData getById(long id, Connection con) throws SQLException {
		UserData data = new UserData();
		String sql = "SELECT id, username, full_name, email, phone, department, role FROM " + TABLE_NAME
				+ " WHERE id = ? AND status<>0 ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			data.setId(rs.getLong("id"));
			data.setUsername(rs.getString("username"));
			data.setFullName(rs.getString("full_name"));
			data.setEmail(rs.getString("email"));
			data.setPhone(rs.getString("phone"));
			data.setDept(rs.getInt("department"));
			data.setRole(rs.getShort("role"));
		}
		return data;
	}

	/**
	 * save method
	 * @param data
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	@Override
	public UserData save(UserData data, Connection con) throws SQLException {
		int result = 0;
		String sql = "INSERT INTO " + TABLE_NAME
				+ " (username, full_name, password, email, phone, department, role, status) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, data.getUsername());
		stmt.setString(2, data.getFullName());
		stmt.setString(3, data.getPassword());
		stmt.setString(4, data.getEmail());
		stmt.setString(5, data.getPhone());
		stmt.setInt(6, data.getDept());
		stmt.setShort(7, data.getRole());
		stmt.setShort(8, (short) 1);

		result = stmt.executeUpdate();

		return (result != 0) ? data : null;
	}

	/**
	 * update method
	 * @param id
	 * @param data
	 * @param role
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	@Override
	public UserData update(long id, UserData data, short role, Connection con) throws SQLException {
		int result = 0;
		
		String sql = "UPDATE " + TABLE_NAME + " SET phone=? ";
		
		if(role == 1) {
			sql += " , full_name=?, email=?, username=?, department=?, role=?";
		}
		
		sql += " WHERE id=?";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, data.getPhone());
		int order = 2; 
		
		if(role == 1) {
			order = 7;
			stmt.setString(2, data.getFullName());
			stmt.setString(3, data.getEmail());
			stmt.setString(4, data.getUsername());
			stmt.setInt(5, data.getDept());
			stmt.setShort(6, data.getRole());
		}
		stmt.setLong(order, data.getId());

		result = stmt.executeUpdate();

		return (result != 0) ? data : null;
	}

	/**
	 * delete method
	 * @param id
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int delete(long id, Connection con) throws SQLException {
		int result = 0;
		String sql = "UPDATE " + TABLE_NAME + " SET status=0 WHERE id=?";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		result = stmt.executeUpdate();

		return result;
	}

	/**
	 * clean method to delete records permanently deleted
	 * @param id
	 * @param con
	 * @throws SQLException
	 */
	@Override
	public void clean(long id, Connection con) throws SQLException {
		String sql = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);
		stmt.executeQuery();
	}

	/**
	 * isAuthUser to check the user is authenticate
	 * @param id
	 * @param username
	 * @param password
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public boolean isAuthUser(long id, String username, String password, Connection con) throws SQLException {
		boolean flag = false;
		String sql = "SELECT id FROM " + TABLE_NAME + " WHERE id = ? AND username = ? AND password = ? AND status<>0 ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);
		stmt.setString(2, username);
		stmt.setString(3, password);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * accessRole method : to get role of user
	 * @param id
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public short accessRole(long id, Connection con) throws SQLException {
		short role = 0;
		String sql = "SELECT role FROM " + TABLE_NAME + " WHERE id = ? AND status<>0 ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			role = rs.getShort("role");
		}
		return role;
	}

}
