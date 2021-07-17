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
<h1> welcome:  ${username},</h1>
 <h3>you can now select the restaurant show below.</h3><br>
 <form  method="get" action="RestaurantName">
<input hidden="text" name="customer_id"  value="${customer_id}"/>
<input hidden="text" name="username"  value="${username}"/>
       <select name="restaurantlist">
           <option>Alibaba Restaurant</option>
           <option>Azheekal Restaurant</option>
           <option>ordinary</option>
       </select>
     <input type="submit" name="submit"/>
    </form>
</body>
</html>


 <%-- <h3>Alibaba restaurant :</h3><form action="/MavenLoginSpringMvc/detail/${username}/${customer_id}" ><input type="submit" value="click here">
 </form>
<!--   <form action="something">
 <input type="submit" 
 </form> --> --%>