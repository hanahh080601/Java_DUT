<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.ArrayList" %>
<%@ page language="java" import="Model.BEANS.Wife" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome screen</title>
</head>
<body>
	<table>
		<tr>
			<th>Name</th>
			<th>Address</th>
			<th>Is Alive</th>
		</tr>
	<%
		ArrayList<Wife> wifeArray = (ArrayList<Wife>) request.getAttribute("wifeArray");
		for(int i = 0; i < wifeArray.size(); i++) {
	%>
		<tr>
			<td><%= wifeArray.get(i).getName() %></td>
			<td><%= wifeArray.get(i).getAddress() %></td>
			<td><%= wifeArray.get(i).isAlive() %></td>
		</tr>
	<% } %>
	</table>
	
</body>
</html>