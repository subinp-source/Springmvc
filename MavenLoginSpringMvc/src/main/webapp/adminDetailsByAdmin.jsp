<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>  
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Admin List</h1>  
<table border="2" width="70%" cellpadding="2">  
<tr><th>Name</th><th>password</th><th>Delete</th></tr>  
   <c:forEach var="admin" items="${adminList}">   
   <tr>    
   <td>${admin.username}</td>  
   <td>${admin.password}</td>   
   <td><a href="DeleteAdmin/${admin.username}">Delete</a></td>  
   </tr>  
   </c:forEach>  
   </table><br>  
   <br/>
   <a href="./admin.jsp">go back</a>
   </body>
   </html>