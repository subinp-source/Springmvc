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
<h1>Customers List</h1>  
<table border="2" width="70%" cellpadding="2">  
<tr><th>Id</th><th>Name</th><th>Email</th><th>password</th><th>Delete</th></tr>  
   <c:forEach var="customer" items="${customerList}">   
   <tr>  
   <td>${customer.customer_id}</td>  
   <td>${customer.username}</td>  
   <td>${customer. email}</td>  
   <td>${customer.password}</td>   
   <%-- <td><a href="DeleteCustomer/${customer.customer_id}">Delete</a></td> --%>  
   <td><form action="DeleteCustomer"><input hidden="text" name="customer_id" value="${customer.customer_id}" /><input type="submit" /></form></td>
   </tr>  
   </c:forEach>  
   </table>  
   <br/>  
</body>
</html>