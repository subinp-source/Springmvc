<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 welcome: <%= request.getAttribute("message")  %>
 <h2>you can now select the restaurant show below.</h2><br>
 <h3>Alibaba restaurant :</h3><form action="details"><input type="submit" value="click here">
 </form>
<!--   <form action="something">
 <input type="submit" 
 </form> -->
</body>
</html>