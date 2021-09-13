package hana;
import java.sql.*;
import javax.sql.*;

public class Test_ODBC {
	public static void Main(String args[]) {
		System.out.println("Ket noi CSDL");
		try {
			// Ket noi CSDL su dung jdbc:odbc
//			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
//			Connection con = DriverManager.getConnection("jdbc:odbc:DATA","", "");
			// Ket noi truc tiep den he quan tri SQL Server
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-1QK42IB\\SQLEXPRESS:1433; DatabaseName = DATA", "sa", "");
			// Ket noi truc tiep den he quan tri CSDL MySQL
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/DATA");
			Statement stmt = con.createStatement();
			// Chen thong tin vao bang
//			String sql21 = "INSERT INTO Table1 VALUES(1,'Hao Minh', 'Quang Binh', 40)";
//			stmt.executeUpdate(sql21);
//			String sql22 = "UPDATE Table1 SET Total = Total + Total*0.1";
//			stmt.executeUpdate(sql22);
			String sql = "SELECT * FROM Table1";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				int id = rs.getInt("Id");
				String name = rs.getString("Names");
				String address = rs.getString("Addresses");
				double total = rs.getDouble("Total");
				System.out.println("id = " + id + ", name = " + name + ", address = " + address + ", total = " + total);
			}
			rs.close();
			stmt.close();
		}
		catch(Exception e)
		{
			System.out.println("Error!");
		}
	}
}
