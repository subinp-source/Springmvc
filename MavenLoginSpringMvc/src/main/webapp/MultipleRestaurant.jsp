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
welcome,${username}<br>
1.Alibaba Restaurant:<form action="AlibabaRestaurant">
<input hidden="text" name="customer_id"  value="${customer_id}"/>
<input hidden="text" name="username"  value="${username}"/>
<input type="submit" value="go"><br>
1.Azheekal Restaurant:<form action="AzheekalRestaurant">
<input type="submit" value="go"><br>
</form>
</body>
</html>