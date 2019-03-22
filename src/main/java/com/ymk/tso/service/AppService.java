package com.ymk.tso.service;

import java.util.List;

public interface AppService<T> {

	List<T> getAll(String val, String key, String auth);

	T getById(long id, String key, String auth);

	T save(T data, String key, String auth);

	T update(long id, T data, String key, String auth);

	int delete(long id, String key, String auth);

	String clean(long id, String key, String auth);
}
