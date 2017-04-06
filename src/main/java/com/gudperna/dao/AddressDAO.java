package com.gudperna.dao;

import com.gudperna.model.Address;

import java.util.ArrayList;

public interface AddressDAO {
	public ArrayList<Address> getByUser(int id);
}

// /src/main/java/com/gudperna/dao/UserDAO.java