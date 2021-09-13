import java.sql.*;

public class BTc6_1 {
	public static void main(String args[]) {
		System.out.println("Ket noi CSDL");
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-1QK42IB\\SQLEXPRESS:1433; DatabaseName=DATA",
					"sa", "123456");
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM Table1";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				int id = rs.getInt("Id");
				String name = rs.getString("Name");
				String address = rs.getString("Addresss");
				int total = rs.getInt("Total");
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
