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
dear ${username},

<h1>Order Details List</h1>  
 <table border="2" width="70%" cellpadding="2">  
<tr><th>food item</th><th>quantity</th><th>sum</th></tr>  
   <c:forEach var="cart" items="${fooditems}">   
   <tr>  
   <td>${cart.food_item}</td>  
   <td>${cart.quantity}</td>  
   <td>${cart.sum}</td>
   </tr>  
   </c:forEach>  
   </table>  
   <br/>
   <br/><br>
   you cost Rs.${sum} for this order.<br><br>
   <h2>Shipping Address</h2>
 <form action="shippingAlibaba">
 <input hidden="text" name="username" value="${username}"/>
   <input type="text" name="address" required>
   <input type="submit" value="confirm order">
   </form><br> 
</body>
</html>