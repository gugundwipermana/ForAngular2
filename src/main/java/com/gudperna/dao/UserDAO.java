package com.gudperna.dao;

import com.gudperna.model.User;

import java.util.ArrayList;

public interface UserDAO {
	public ArrayList<User> getAll();
	public User getById(int id);
	public void insert(User user);
	public void edit(User user);
	public void delete(int id);
}

// /src/main/java/com/gudperna/dao/UserDAO.java