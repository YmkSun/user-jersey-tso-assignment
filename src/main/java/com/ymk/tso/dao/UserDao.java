package com.ymk.tso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ymk.tso.data.UserData;

public class UserDao implements AppDao<UserData> {

	private final String TABLE_NAME = "tb_user";

	private static UserDao instance = new UserDao();

	public static UserDao getInstance() {
		return instance;
	}

	private UserDao() {
	}

	public List<UserData> getAll(String val, Connection con) throws SQLException {

		List<UserData> dataList = new ArrayList<>();
		UserData data;

		String sql = "SELECT id, username, full_name, email, phone, department, role FROM " + TABLE_NAME;
		String whereClause = " WHERE status<>0 ";

		if (!val.isEmpty())
			whereClause += " AND (username LIKE %?% OR full_name LIKE %?% email LIKE %?% ";

		PreparedStatement stmt = con.prepareStatement(sql + whereClause);

		if (!val.isEmpty()) {
			stmt.setString(1, val);
			stmt.setString(2, val);
			stmt.setString(3, val);
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

	@Override
	public UserData update(long id, UserData data, Connection con) throws SQLException {
		int result = 0;
		String sql = "UPDATE " + TABLE_NAME
				+ " SET username=?, full_name=?, email=?, phone=?, department=?, role=? WHERE id=?";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, data.getUsername());
		stmt.setString(2, data.getFullName());
		stmt.setString(3, data.getEmail());
		stmt.setString(4, data.getPhone());
		stmt.setInt(5, data.getDept());
		stmt.setShort(6, data.getRole());
		stmt.setLong(7, data.getId());

		result = stmt.executeUpdate();

		return (result != 0) ? data : null;
	}

	@Override
	public int delete(long id, Connection con) throws SQLException {
		int result = 0;
		String sql = "UPDATE " + TABLE_NAME + " SET status=0 WHERE id=?";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		result = stmt.executeUpdate();

		return result;
	}

	@Override
	public void clean(long id, Connection con) throws SQLException {
		String sql = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);
		stmt.executeQuery();
	}

}
