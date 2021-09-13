package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

import DTO.LSH;
import DTO.SV;

public class DBHelper {
	
	private static DBHelper instance = new DBHelper();
	   private DBHelper(){}
	   public static DBHelper getInstance(){
	      return instance;
	   }
	
	
	public Vector<SV> GetDBSV(String query)
	{
		Vector<SV> vectorData = new Vector<SV>();
		try 
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-1QK42IB\\SQLEXPRESS:1433; DatabaseName=QLSV_JAVA", "sa", "123456");
			Statement stmt = con.createStatement();
			String sql = query;
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rm = rs.getMetaData();
			int numberCols = rm.getColumnCount();
			SV row;
			while(rs.next())
			{ 
				row = new SV();
				row.MSSV = rs.getString(1);
				row.NameSV = rs.getString(2);
				row.Gender = rs.getBoolean(3);
				row.NS = rs.getDate(4);
				row.ID_Lop = rs.getInt(5);
				vectorData.addElement(row);
			}	
			rs.close();
			stmt.close();
			con.close();
			return vectorData;
		}
		catch(Exception e)
		{
			System.out.println("Error!");
		}
		return vectorData;
	}
	
	public Vector<LSH> GetDBLSH(String query)
	{
		Vector<LSH> vectorData = new Vector<LSH>();
		try 
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-1QK42IB\\SQLEXPRESS:1433; DatabaseName=QLSV_JAVA", "sa", "123456");
			Statement stmt = con.createStatement();
			String sql = query;
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rm = rs.getMetaData();
			int numberCols = rm.getColumnCount();
			while(rs.next())
			{ 
				LSH row = new LSH(rs.getInt(1),rs.getString(2));
				vectorData.addElement(row);
			}
			rs.close();
			stmt.close();
			con.close();
			return vectorData;
		}
		catch(Exception e)
		{
			System.out.println("Error!");
		}
		return vectorData;
	}
	
	public Vector<String> GetNameCol(String query)
	{
		Vector<String> vectorColNames = new Vector<String>();
		try 
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-1QK42IB\\SQLEXPRESS:1433; DatabaseName=QLSV_JAVA", "sa", "123456");
			Statement stmt = con.createStatement();
			String sql = query;
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rm = rs.getMetaData();
			int numberCols = rm.getColumnCount();
			for(int i=1; i <= numberCols; ++i)
			{
				vectorColNames.addElement(rm.getColumnName(i));
			}
			rs.close();
			stmt.close();
			con.close();
			return vectorColNames;
		}
		catch(Exception e)
		{
			System.out.println("Error!");
		}
		return vectorColNames;
	}
	
	public void UpdateDB(String query)
	{
		try 
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-1QK42IB\\SQLEXPRESS:1433; DatabaseName=QLSV_JAVA", "sa", "123456");
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println("Error!");
		}
	}
}
