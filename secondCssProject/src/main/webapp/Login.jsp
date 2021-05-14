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
 String s1=request.getParameter("t1");
String s2= request.getParameter("t2");
  if(s1.equals("subin") && s2.equals("12345")){
	  request.sendRedirect("index1.html");
  }else{
	  request.sendRedirect("error,html");
  }

%>
</body>
</html>