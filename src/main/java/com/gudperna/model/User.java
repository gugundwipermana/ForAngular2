package com.gudperna.model;
import java.util.ArrayList;

public class User {
	private int id;
	private String email;
	private String password;
	private String name;

	// HAVE
	private ArrayList<Address> address;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public ArrayList<Address> getAddress() {
		return address;
	}

	public void setAddress(ArrayList<Address> address) {
		this.address = address;
	}

}

// /src/main/java/com/gudperna/model/User.java