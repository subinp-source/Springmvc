<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>  
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/restaurant.css"/>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form  method="get" action="SelectFoodOfAlibaba">
<input hidden="text" name="customer_id"  value="${customer_id}"/>
<input hidden="text" name="username"  value="${username}"/>
       <h1><select name="foodlist">
           <option>rolls</option>
           <option>chowmein</option>
       </select>
     <input type="submit" name="submit"/>
    </form></h2>
</body>
</html>