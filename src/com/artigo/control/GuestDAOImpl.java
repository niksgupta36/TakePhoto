package com.artigo.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




public class GuestDAOImpl {
	static Connection conn;
	static PreparedStatement ps;
	

	public int getorder(String faceid) {
		int order=0;
		conn = ConnectionProvider.getCon();
		
		try {
			ps=conn.prepareStatement("select * from Guest");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				FaceVerify verify = new FaceVerify();
				
				if(verify.getVerify(rs.getString(1), faceid))
				return rs.getInt(2);
		}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return order;
		
	}
	
	public void insertorder(int orderid, String faceid) {
		
		conn = ConnectionProvider.getCon();
		
		try {
			ps=conn.prepareStatement("insert into Guest values(?,?)");
			
			
			ps.setString(1, faceid);
			ps.setLong(2, orderid);
			
			ps.executeUpdate();
			conn.close();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	
		
	}
	
	
}
