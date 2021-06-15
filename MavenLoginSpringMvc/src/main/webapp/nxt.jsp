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
dear ${name},

<h1>Order Details List</h1>  
 <table border="2" width="70%" cellpadding="2">  
<tr><th>food item</th><th>quantity</th></tr>  
   <c:forEach var="cart" items="${items}">   
   <tr>  
   <td>${cart.food_item}</td>  
   <td>${cart.order_quantity}</td>  
       
   </tr>  
   </c:forEach>  
   </table>  
   <br/> <%-- <c:forEach var = "i" begin = "1" end = "3">
    ${namefood} : ${quant}     <p>
      
   
   </c:forEach> --%>
   <br/><br>

   you cost Rs.${sum} for this order.<br><br>
   <h2><a href="final1.jsp">next</a></h2>
   
   
<a href="restaurant.jsp">back</a><br>
<a href="index.jsp">home</a>
<%-- <form action="out">
<input type="submit" value="Log out">
</form> --%>
</body>
</html>