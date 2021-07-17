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
Enter passcode,if you are a admin,
<form action="passcode">
<input type="text" name="passcode">
<input type="submit" value="ok">
</form><br>
or,go to booking page by clicking below..


<form action="GotoWelcomePage">
<input hidden="number" name="customer_id"  value="${customer_id}"/>
 <input hidden="text" name="username"  value="${username}"/>
<input type="submit" value="Go">
</form><br>




</body>
</html>