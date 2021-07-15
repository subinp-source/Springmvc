<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page isELIgnored="false"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<h1>Order Details List</h1>  
<table border="2" width="70%" cellpadding="2">  
<tr><th>Customer Id</th><th>Food id</th><th>Quantity</th></tr>  
   <c:forEach var="details" items="${OrderFoodList}">   
   <tr>  
   <td>${details.customer_id}</td>  
   <td>${details.food_id}</td>  
   <td>${details.order_quantity}</td>    
   </tr>  
   </c:forEach>  
   </table>  
   <br/>
   <a href="admin.jsp">back to admin page</a>
     
</body>
</html>