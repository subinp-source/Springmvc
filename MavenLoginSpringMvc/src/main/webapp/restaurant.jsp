<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>  
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title><link rel="stylesheet" type="text/css" href="css/restaurant.css"/>
</head>
<body> <h2>
 <c:forEach var="food" items="${Foodlist}">
        ${food.food_item} Rs.${food.food_price} 
        <form action="booking">
        <input hidden="text" name="customer_id"  value="${customer_id}"/>
        <input hidden="text" name="username"  value="${username}"/>
        <input hidden="text" name="food_id"  value="${food.food_id}"/>
        <input hidden="text" name="food_item"  value="${food.food_item}"/>
        <input type="text" name="count"   id="count" required>
        <input type="submit" value="add" ><br><br>
   </form>
   </c:forEach><br></h2>
   
   <h2><form action="summation">
   <input hidden="text" name="customer_id"  value="${customer_id}"/>
   <input hidden="text" name="username"  value="${username}"/>
   <input hidden="text" name="sum"  value="${sum}"/>
   <input type="submit" value="order"></form></h2>
    
    
  </body> 
  </html>
  
  
  
  
  
  
 <%--  <h2><table align="center">   
   <c:forEach var="cartlisting" items="${listing}">   
   <tr>  
   <td>${cartlisting.food_item}</td>  
   <td>${cartlisting.quantity}</td>  
       
   </tr>  
   </c:forEach>  
   </table>  
   <br/></h2>
     --%>
    