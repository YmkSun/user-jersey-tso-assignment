package com.ymk.tso.service;

import java.util.List;

public interface AppService<T> {

	List<T> getAll(String val);

	T getById(long id);

	T save(T data);

	T update(long id, T data);

	int delete(long id);

	String clean(long id);
}
