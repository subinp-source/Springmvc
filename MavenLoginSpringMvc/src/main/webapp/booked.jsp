<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Dear , your total cost is Rs. <%= request.getAttribute("totalprice") %>.<br>
Thanks for booking.<br>
<a href="index.jsp">LOGIN</a>
</body>
</html>