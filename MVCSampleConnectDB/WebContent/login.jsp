<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login screen</title>
</head>
<body>
	<%
		String temp = "temp";
		session.setAttribute("temp", temp);
	%>
	<form action="CheckLoginServlet" method="post">
		Username: <input type="text" name="userName" />
		Password: <input type="password" name="password" />
		<input type="submit" value="Login" />
	</form>
</body>
</html>