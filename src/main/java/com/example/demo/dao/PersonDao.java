package com.example.demo.dao;

import java.io.Serializable;
import java.util.List;

public interface PersonDao <T> extends Serializable{
	
	public List<T> getAll();
	public List<T> findById(long id);
	public List<T> findByName(String name);

}
