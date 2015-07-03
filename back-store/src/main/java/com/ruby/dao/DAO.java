package com.ruby.dao;

public abstract class DAO<T> {
	
	public abstract T load();
	
	public abstract boolean update(T obj);
	
	public abstract boolean delete(T obj);
}
