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


<form action="GotoWelcomePage/${customer.username}/${customer.customer_id}">
<input type="submit" value="Go">
</form><br>




</body>
</html>