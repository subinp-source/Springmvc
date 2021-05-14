<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");// HTTP 1.1
response.setHeader("pragma","no-cache");// HTTP 1.0
response.setHeader("Expires","0");// proxies
      if(session.getAttribute("username")==null){
    	  response.sendRedirect("login.jsp");
      }
%>

welcome ${username}<br>
<a href="hotels.jsp">Restaurants</a><br><br>
<form action="Logout">
<input type="submit" value="logout">
</form>
</body>
</html>