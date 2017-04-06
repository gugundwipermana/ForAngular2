package com.gudperna.dao.impl;

import com.gudperna.dao.AddressDAO;
import com.gudperna.model.Address;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.jersey.internal.util.Base64;

public class AddressDAOImpl implements AddressDAO {
	
	private Connection connection;

	public AddressDAOImpl(Connection connection) {
		this.connection = connection;
	}

	public ArrayList<Address> getByUser(int id) {
		ArrayList<Address> result = new ArrayList<Address>();
		Statement stmt = null;
		ResultSet rs = null;


 		try {
 			if(connection != null) {
	 			stmt = connection.createStatement();
	 			rs = stmt.executeQuery("SELECT * FROM address a WHERE a.user_id="+id);
	 			while(rs.next()) {
	 				Address address = new Address();

	 				address.setId(rs.getInt("a.id"));
	 				address.setUserId(rs.getInt("a.user_id"));
	 				address.setName(rs.getString("a.name"));
	 				address.setAddress(rs.getString("a.address"));
	 				address.setCityId(rs.getInt("a.city_id"));
	 				result.add(address);
	 			}
 			}
 		} catch (SQLException ex) {
 			Logger.getLogger(AddressDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
 		} finally {
 			if(connection != null) try {
 				connection.close();
 			} catch (SQLException ex) {
 				Logger.getLogger(AddressDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
 			}
 
 			if(stmt != null) try {
 				stmt.close();
 			} catch (SQLException ex) {
 				Logger.getLogger(AddressDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
 			}
 
 			if(rs != null) try {
 				rs.close();
 			} catch (SQLException ex) {
 				Logger.getLogger(AddressDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
 			}
 		}

		return result;
	}
}