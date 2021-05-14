package com.project.web.dao;
import java.sql.*;
public class ConnectionDao {
	
	public boolean getConnect(String ud, String pwd) {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/swiggy","root","1234");
			PreparedStatement st = con.prepareStatement("select * from dao where ud=? and pwd=?");
			st.setString(1,ud);
			st.setString(2,pwd);
			ResultSet rs=st.executeQuery();
			if(rs.next()) {
				
				return true;
			
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return false;
		
		
	}
	
	
	
	

}
