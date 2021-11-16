<%@ page import="java.sql.*" %> 
<%@ page import="com.mysql.jdbc.Connection" %> 
<%@ page import="com.mysql.jdbc.Statement" %> 

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	
	String address = "192 Nguyen Luong Bang";
	
	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://127.0.0.1:3306/dulieu";
		Connection con = (Connection) DriverManager.getConnection(url, "root", "");
		Statement stmt = (Statement) con.createStatement();
		String sql = "SELECT * FROM admin where username = '" + username + "' and pass = '" + password + "'";
		ResultSet rs = stmt.executeQuery(sql);
		
		if (rs.next()) {
			request.setAttribute("address", address);
			RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
			rd.forward(request, response);
			rs.close();
			stmt.close();
		} 
		else {
			rs.close();
			stmt.close();
			response.sendRedirect("login.jsp");
		}
	}
	catch(Exception a)
	{
		System.out.println("Error " + a);
	}
%>