package Model.DAO;
import java.sql.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.util.ArrayList;

import Model.BEANS.*;

public class CheckLoginDAO {
	public boolean isExistUser(String userName, String password) {
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/dulieu";
			Connection con = (Connection) DriverManager.getConnection(url, "root", "");
			Statement stmt = (Statement) con.createStatement();
			String sql = "SELECT * FROM admin where username = '" + userName + "' and pass = '" + password + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				rs.close();
				stmt.close();
				return true;
			} 
			else {
				rs.close();
				stmt.close();
				return false;
			}
		}
		catch(Exception a)
		{
			System.out.println("Error " + a);
		}
		return false;
	}
	
	public ArrayList<Wife> getWifeList(String userName) {
		ArrayList<Wife> result = new ArrayList<Wife>();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/dulieu";
			Connection con = (Connection) DriverManager.getConnection(url, "root", "");
			Statement stmt = (Statement) con.createStatement();
			String sql = "SELECT * FROM wife";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String name = rs.getString("name");
				String address = rs.getString("address");
				boolean alive = rs.getBoolean("alive");
				Wife wife = new Wife();
				wife.setName(name);
				wife.setAddress(address);
				wife.setAlive(alive);
				result.add(wife);
			}
		}
		catch(Exception a)
		{
			System.out.println("Error " + a);
		}
		return result;
	}
}
