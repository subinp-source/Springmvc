<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>  
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body> 
 <c:forEach var="food" items="${list}">
        ${food.food_item} Rs.${food.food_price} 
        <form action="booking/${food.food_id}/${food.food_item}">
        <input type="text" name="count">
        <input type="submit" value="add"><br><br>
   </form>
   </c:forEach><br>
    <form action="summation/${sum}"><input type="submit" value="order"></form> 
  </body> 
  </html>