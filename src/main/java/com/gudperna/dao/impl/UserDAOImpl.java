package com.gudperna.dao.impl;

import com.gudperna.util.ConnectionUtil;
import com.gudperna.dao.UserDAO;
import com.gudperna.dao.AddressDAO;
import com.gudperna.dao.impl.AddressDAOImpl;

import com.gudperna.model.User;
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

public class UserDAOImpl implements UserDAO {
	
	private Connection connection;

	public UserDAOImpl(Connection connection) {
		this.connection = connection;
	}

	public ArrayList<User> getAll() {
		ArrayList<User> result = new ArrayList<User>();
		Statement stmt = null;
		ResultSet rs = null;


 		try {
 			if(connection != null) {
	 			stmt = connection.createStatement();
	 			rs = stmt.executeQuery("SELECT * FROM users a");
	 			while(rs.next()) {
	 				User user = new User();
	 				user.setId(rs.getInt("a.id"));
	 				user.setEmail(rs.getString("a.email"));
	 				user.setPassword(rs.getString("a.password"));
	 				user.setName(rs.getString("a.name"));

	 				AddressDAO addressDAO = new AddressDAOImpl(ConnectionUtil.getConnection());
	 				user.setAddress(addressDAO.getByUser(rs.getInt("a.id")));

	 				result.add(user);
	 			}
 			}
 		} catch (SQLException ex) {
 			Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
 		} finally {
 			if(connection != null) try {
 				connection.close();
 			} catch (SQLException ex) {
 				Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
 			}
 
 			if(stmt != null) try {
 				stmt.close();
 			} catch (SQLException ex) {
 				Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
 			}
 
 			if(rs != null) try {
 				rs.close();
 			} catch (SQLException ex) {
 				Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
 			}
 		}

		return result;
	}



	@Override
	public User getById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		User user = new User();
		try {
			ps = connection.prepareStatement("SELECT * FROM users a WHERE id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				user.setId(rs.getInt("a.id"));
	 			user.setEmail(rs.getString("a.email"));
	 			user.setPassword(rs.getString("a.password"));
	 			user.setName(rs.getString("a.name"));
			}
		} catch(SQLException ex) {
			Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

		return user;
	}

	@Override
	public void insert(User user) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("INSERT INTO users(email, password, name) values(?,?,?)");

			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());

			ps.executeUpdate();
			connection.commit();
		} catch(SQLException ex) {
			Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void edit(User user) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("UPDATE users SET email=?, password=?, name=? WHERE id=?");
			
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());

			ps.setInt(4, user.getId());
			ps.executeUpdate();
			connection.commit();
		} catch(SQLException ex) {
			Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void delete(int id) {
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate("DELETE FROM users WHERE id="+id);
			connection.commit();
		} catch(SQLException ex) {
			Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}




	/**
	 * ----------------------------------
	 * For Authentication
	 * ----------------------------------
	 * cekUser()
	 * getByEmail()
	 *
	 */
	public boolean cekUser(User user) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		boolean user_exists = false;

		try {
			ps = connection.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return true;
			}

		} catch(SQLException ex) {
			Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
	 	}

	 	return false;
	}

	public User getByEmail(String email) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = new User();
		try {
			ps = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
			ps.setString(1, email);
			rs = ps.executeQuery();
			if(rs.next()) {
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
			}
		} catch(SQLException ex) {
			Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
	 	}

		return user;
	}
}