<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>  
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/welcomedesign.css"/>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 welcome:  ${username}         <%--  <%= request.getAttribute("customer")  %> --%>
 <h2>you can now select the restaurant show below.</h2><br>
 <h3>Alibaba restaurant :</h3><form action="/MavenLoginSpringMvc/detail/${username}/${customer_id}" ><input type="submit" value="click here">
 </form>
<!--   <form action="something">
 <input type="submit" 
 </form> -->
</body>
</html>