package com.project;
import java.sql.*;


public class DemoInsertConnection {
	public static void main(String[] args) throws Exception  {
		String url="jdbc:mysql://localhost:3306/students1";//jdbc:mysql://localhost:3306/sonoo
		String uname="root";
		String pass="1234";
	    String query="insert into teacher values (?,?,?)";
	    int id=6;
	    String c="c";
	    String name="sadhik";
	    
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		PreparedStatement st=con.prepareStatement(query);
		st.setInt(1, id);
		st.setString(2,c);
		st.setString(3, name);
		int count= st.executeUpdate();
		System.out.println(count+" rows affected");
		
		
		st.close();
		con.close();
		

	}

}
