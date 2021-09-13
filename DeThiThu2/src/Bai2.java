import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Bai2 {
	
	public static Connection con;
	
	public static void Connect()
	{
		System.out.println("Ket noi CSDL");
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-1QK42IB\\SQLEXPRESS:1433; DatabaseName=QLTOUR",
					"sa", "123456");
			System.out.println("Connected!");
		}
		catch(Exception e)
		{
			System.out.println("Error!");
		}
	}
	
	public static boolean isNumeric(String str)
	{
	    for (char c : str.toCharArray())
	    {
	        if (!Character.isDigit(c)) return false;
	    }
	    return true;
	}
	
	public static boolean isInteger(String s) {
	    try 
	    { 
	        Integer.parseInt(s); 
	    } 
	    catch(NumberFormatException e) 
	    { 
	        return false; 
	    } 
	    catch(NullPointerException e) 
	    {
	        return false;
	    }
	    return true;
	}
	
	public static boolean isIDin(List<String> l, String id)
	{
		for(int i = 0; i < l.size(); i++)
		{
			if(id.equals(l.get(i)))
			{
				return true;
			}
		}
		return false;
	}
	
	public static void main(String args[]) {
		
		try 
		{
			 Connect();
		     File f_input1 = new File("data1.txt");
		     FileReader fr1 = new FileReader(f_input1);
    		  		 
		     BufferedReader br1 = new BufferedReader(fr1);
		     
		     String line;
		     List<String> listID = new ArrayList<String>();
		     boolean check1 = true;
		     
		     while ((line = br1.readLine()) != null)
		     {
		    	 String[] record = line.split(", ");
		    	 String id = record[0];
		    	 String name = record[1];
		    	 String ns = record[2];
		    	 String dc = record[3];
		    	 String mail = record[4];
		    	 String sdt = record[5];
		    	 if(!isNumeric(sdt))
		    	 {
		    		 check1 = false;
		    	 }
		    	 if(id.length() > 8)
		    	 {
		    		 check1 = false;
		    	 }
		    	 if(check1 == true)
		    	 {	 
		    		 listID.add(id);
			    	 String sql = "INSERT INTO THANHVIEN VALUES"
			    	 		+ "('" + id +"','" + name + "','" + ns + "','" + dc + "','" + mail + "','" + sdt +"', 0.0)";
			    	 //System.out.println(sql);
					 Statement stmt = con.createStatement();
					 stmt.executeUpdate(sql);
					 stmt.close();
			     }
		     } 
		     fr1.close();
		     br1.close();
		     
		     File f_input2 = new File("data2.txt");
		     FileReader fr2 = new FileReader(f_input2);
		     BufferedReader br2 = new BufferedReader(fr2);
		     
		     File f_error = new File("error.txt");
    		 FileWriter fw = new FileWriter(f_error);
    		 
    		 int count = 0;
    		 boolean check2 = true;
    		 if(check1 == true) 
    		 {
    			 System.out.println("Connect again!");
    			 Connect();
    		 }
    		 while ((line = br2.readLine()) != null)
    		 {
    			 count += 1;
    			 String[] record = line.split(", ");
    			 String id = record[0];
    			 String diem = record[1];
    			 String level = record[2];
    			 if(isInteger(diem))
    			 {
    				 if(Integer.parseInt(diem) < 0)
    				 {
    					 fw.write("Dong " + count + ": Loi diem thuong khong phai la so nguyen duong!\n");
    		    	     check2 = false;
    				 }
    				 if(Integer.parseInt(diem) > 500)
    				 {
    					 fw.write("Dong " + count + ": Loi diem thuong vuot qua 500!\n");
    		    	     check2 = false;
    				 }
    				 else 
    				 {
						check2 = true;
					 }
    			 }
    			 else 
    			 {
    				 fw.write("Dong " + count + ": Loi diem thuong khong phai la so nguyen!\n");
		    	     check2 = false;
				 }
    			 if(!level.trim().equals("VIP") && !level.trim().equals("NOR"))
    			 {
    				 fw.write("Dong " + count + ": Loi dinh dang Level!\n");
		    	     check2 = false;
    			 }
    			 if(!isIDin(listID, id))
    			 {
    				 fw.write("Dong " + count + ": Loi khong ton tai ma thanh vien trong CSDL!\n");
		    	     check2 = false;
    			 }
    			 if(check2 == true)
    			 {
    				 String sql = "";
    				 if(level.trim().equals("VIP"))
    				 {
    					 sql = "UPDATE THANHVIEN SET ChiPhiNhan = ChiPhiNhan + " + 50000 +"*"+ diem + " WHERE MaThanhVien = '" + id + "'";
    				 }
    				 else 
    				 {
    					 sql = "UPDATE THANHVIEN SET ChiPhiNhan = ChiPhiNhan + " + 20000 +"*"+ diem + " WHERE MaThanhVien = '" + id + "'";
					 }
			    	 System.out.println(sql);
					 Statement stmt = con.createStatement();
					 stmt.executeUpdate(sql);
					 stmt.close();
    			 }
    		 }
    		 fr2.close();
		     fw.close();
		     br2.close();	     
		 } 
		 catch(Exception ex) 
		 {
		     System.out.println("Reading file error: "+ex);
		 }
	}

}
