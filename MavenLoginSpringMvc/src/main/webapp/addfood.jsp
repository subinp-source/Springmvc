<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><%@ page isELIgnored="false"%><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <c:forEach var="food" items="${FoodDetailsList}">
        ${food.food_item} Rs.${food.food_price} 
        <form action="updatingfood/${food.food_id}">
        <input type="text" name="count">
        <input type="submit" value="add"><br><br>
   </form>
   </c:forEach><br>
        <table border="2" width="70%" cellpadding="2">  
<tr><th>food item</th><th>quantity</th></tr>  
    <c:forEach var="food" items="${FoodDetailsList}">  
   <tr>  
   <td> ${food.food_item}</td>  
   <td>${food.final_quantity}</td>  
       
   </tr>  
   </c:forEach>  
   </table>  
   <br/> 
   <a href="admin.jsp">back to admin page</a>
</body>
</html>