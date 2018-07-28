package com.artigo.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAOImpl {
	
	static Connection conn;
	static PreparedStatement ps;
	
	public void addCustomer(Customer cust) {
		// TODO Auto-generated method stub
		conn = ConnectionProvider.getCon();
		try {
			ps = conn.prepareStatement("insert into Customer values(?,?,?,?)");
			ps.setString(1, cust.getName());
			ps.setString(2, cust.getPassword());
			ps.setString(3, cust.getEmailid());
			ps.setString(4, cust.getFaceid());
			ps.execute();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String logincheck(String name) {
		// TODO Auto-generated method stub
		conn = ConnectionProvider.getCon();
		
		try {
			ps = conn.prepareStatement("select PASSWORD from Customer where NAME= (?)");
			ps.setString(1, name);
			
		ResultSet rs =	ps.executeQuery();
		String s="";
		while(rs.next()) s=rs.getString(1);
			return s;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
