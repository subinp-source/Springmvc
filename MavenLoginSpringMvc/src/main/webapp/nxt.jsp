<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
dear ${name}, you cost Rs.${sum} for this order.<br>And food will be dispatched to this ${address} soon...<br>
<form action="out">
<input type="submit" value="Log out">
</form>
</body>
</html>