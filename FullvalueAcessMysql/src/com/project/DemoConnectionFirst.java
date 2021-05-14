package com.project;
import java.sql.*;




public class DemoConnectionFirst {
	public static void main(String[] args) throws Exception{
		String url="jdbc:mysql://localhost:3306/students1";//jdbc:mysql://localhost:3306/sonoo
		String uname="root";
		String pass="1234";
	    String query="select * from teacher";
	    
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		Statement st=con.createStatement();
		ResultSet rs= st.executeQuery(query);
		
		while(rs.next()) {
		String name= rs.getInt(1)+":"+rs.getString(2)+":"+rs.getString(3);
		System.out.println(name);
		}
		
		st.close();
		con.close();
		

	}
	

}
