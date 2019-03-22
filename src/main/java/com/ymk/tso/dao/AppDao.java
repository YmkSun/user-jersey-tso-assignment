package com.ymk.tso.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface AppDao<T> {
	
	List<T> getAll(String val, long id, Connection con) throws SQLException;

	T getById(long id, Connection con) throws SQLException;

	T save(T data, Connection con) throws SQLException;

	T update(long id, T data, short role, Connection con) throws SQLException;

	int delete(long id, Connection con) throws SQLException;

	void clean(long id, Connection con) throws SQLException;
	
}
