package com.project.register.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class RegisterControllerDao {

	public void insert(String fn, String ud, String pwd, String ads, String phr) throws ClassNotFoundException, SQLException {
		
		String a =fn;
		String b=ud;
		String c=pwd;
		String d=ads;
		String e=phr;
		String f=null;
		String query="insert into dao values (?,?,?,?,?,?)";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/swiggy","root","1234");
		PreparedStatement st=con.prepareStatement(query);
		st.setString(1,a);
		st.setString(2,b);
		st.setString(3,c);
		st.setString(4,d);
		st.setString(5,e);
		st.setString(6,f);
		
		int count= st.executeUpdate();
		System.out.println(count+" rows effected");
		
		st.close();
		con.close();
		
	}
	
	
	

}
