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
	<style>
		#customers {
  			font-family: Arial, Helvetica, sans-serif;
  			border-collapse: collapse;
  			width: 100%;
		}

		#customers td, #customers th {
  			border: 1px solid #ddd;
  			padding: 8px;
		}

		#customers tr:nth-child(even){background-color: #f2f2f2;}

		#customers tr:hover {background-color: #ddd;}

		#customers th {
  			padding-top: 12px;
  			padding-bottom: 12px;
  			text-align: left;
  			background-color: #8766ff;
  			color: white;
		}
	</style>
	<table id="customers">
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