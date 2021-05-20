<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
  
<h1>Food List</h1>  
<table border="2" width="70%" cellpadding="2">  
<tr><th>Food</th><th>Food price</th><th>Quantity Available</th></tr>  
   <c:forEach var="food" items="${list}">   
   <tr>  
   <td>${food.food_item}</td>  
   <td>${food.food_price}</td>  
   <td>${food.final_quantity}</td>
   </tr>  
   </c:forEach>  
   </table> <br>
   <form action="booking">
    Dosa:<input type="text" name="dosa"><br>
    Beef roast:<input type="text" name="beef"><br>
    chapathi:<input type="text" name="chapathi"> <br>
    <input type="submit" value="order"></form>
   <br/>  
</body>
</html>