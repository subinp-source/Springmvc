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
 <c:forEach var="food" items="${StartersdeepfryFoodList}">
        ${food.food_item} old price Rs.${food.food_price} 
        <form action="pricechangeStartersdeepfry/${food.food_id}">
        <input type="text" name="price">
        <input type="submit" value="change"><br><br>
   </form>
   </c:forEach><br>
   <a href="admin.jsp">back to admin page</a>
</body>
</html>