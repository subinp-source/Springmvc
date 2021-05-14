package com.mysql.conection;
import java.sql.*;

public class DemoConnection {

	public static void main(String[] args) throws Exception  {
		String url="jdbc:mysql://localhost:3306/students1";//jdbc:mysql://localhost:3306/sonoo
		String uname="root";
		String pass="1234";
	    String query="select * from student";
	    
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		Statement st=con.createStatement();
		ResultSet rs= st.executeQuery(query);
		
		rs.next();
		String name= rs.getString(1);
		System.out.println(name);
		
		st.close();
		con.close();
		

	}

}
