import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
 
public class Bai2M {
 
    public static HashMap<String, Integer> GetMaTVFromDB() {
    	HashMap<String, Integer> mp = new HashMap<String, Integer>();
    	try {
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        	Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-1QK42IB\\SQLEXPRESS:1433; DatabaseName=QLTOUR",
					"sa", "123456");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from THANHVIEN");
            // show data
            while (rs.next()) {
//                System.out.println(rs.getString(1));
                mp.put(rs.getString(1), 1);
            }
            rs.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    	return mp;
    }
 
    public static String readFromFile(String data) throws IOException {
    	BufferedReader br;
    	String everything = "";
		try {
			br = new BufferedReader(new FileReader(data));
			StringBuilder sb = new StringBuilder();
    	    String line;
			try {
				line = br.readLine();
				while (line != null) {
	    	        sb.append(line);
	    	        sb.append(System.lineSeparator());
	    	        line = br.readLine();
	    	    }
	    	    everything = sb.toString();
	    	    System.out.println(everything);
		    } 
			finally {
				br.close();
		    }
		} 
		catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		return everything;
    }
    public static void WriteErrorToFile(String error) {
    	try {
            FileWriter fw = new FileWriter("error.txt");
            fw.write(error);
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
 
    }
    public static void ExecuteDB(String query) {
    	try {
	       	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	       	Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-1QK42IB\\SQLEXPRESS:1433; DatabaseName=QLTOUR",
					"sa", "123456");
	       	Statement stmt = conn.createStatement();
	       	stmt.executeUpdate(query);
	       	conn.close();
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    }
    public static void InsertToDB(String s) {
    	String[] arr = s.split(",|\n");
    	for (int i = 0; i<arr.length;i++ ) {
    		arr[i] = arr[i].strip();
    	}
        for (int i=0;i<arr.length;i+=6) {
        	String query = "Insert into THANHVIEN values ('" + arr[i+0] + "', '" + arr[i+1] + "', '" + arr[i+2] + "', '" + arr[i+3] + "', '" + arr[i+4] + "', '" + arr[i+5] + "', " + 0  + ")";
            System.out.println(query);
            ExecuteDB(query);
        }
    }
 
    public static void EditDB(String s)
    {
    	String[] arr = s.split(",|\n");
    	for (int i = 0; i<arr.length;i++ ) {
    		arr[i] = arr[i].strip();
    	}
    	for (int i=0;i<arr.length;i+=3) {
    		String query = "";
    		String MaTV = arr[i];
    		String DiemThuong = arr[i+1];
    		String Level = arr[i+2];
    		if (Level.equals("VIP")) {
    			query = "Update THANHVIEN set ChiPhiNhan = "+ 50000 +"*"+ DiemThuong+" where MaThanhVien = '"+MaTV+"'";
    		}
    		else if (Level.equals("NOR")) {
    			query = "Update THANHVIEN set ChiPhiNhan = "+ 20000 +"*"+ DiemThuong+" where MaThanhVien = '"+MaTV+"'";
    		}
            System.out.println(query);
            ExecuteDB(query);
    	}
    }
 
    public static boolean isStringInt(String s)
    {
        try
        {
            int x = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }
    }
 
    public static String checkFile(String s) {
    	String ans = "";
    	String error = "";
    	String[] arr = s.split(",|\n");
    	HashMap<String, Integer> mp = GetMaTVFromDB();
    	for (var i: mp.keySet()) {
    		System.out.println("      "+i);
    	}
    	for (int i = 0; i<arr.length;i++ ) {
    		arr[i] = arr[i].strip();
    	}
    	for (int i=0;i<arr.length;i+=3) {
    		String errorInLine = "";
    		String MaTV = arr[i];
    		String DiemThuong = arr[i+1];
    		String Level = arr[i+2];
    		if (!mp.containsKey(MaTV)) {
    			if (!errorInLine.equals("")) errorInLine+=", ";
    			errorInLine += "Ma thanh vien khong ton tai trong database";
    		}
    		if (!isStringInt(DiemThuong)) {
    			if (!errorInLine.equals("")) errorInLine+=", ";
    			errorInLine += "Diem thuong khong phai la so nguyen duong";
    		}
    		if (isStringInt(DiemThuong)) {
    			if (Integer.parseInt(DiemThuong) > 500) {
    				if (!errorInLine.equals("")) errorInLine+=", ";
        			errorInLine += "Diem thuong lon hon 500";
    			}
    			if (Integer.parseInt(DiemThuong) < 0) {
    				if (!errorInLine.equals("")) errorInLine+=", ";
        			errorInLine += "Diem thuong be hon 0";
    			}
    		}
    		if (!Level.equals("VIP") && !Level.equals("NOR")) {
    			if (!errorInLine.equals("")) errorInLine+=", ";
    			errorInLine += "Level khong phai la VIP hoac NOR";
    		}
    		if (!errorInLine.equals("")) error+= "Dong " + (i/3+1) + ": "+ errorInLine + "\n";
    		if (errorInLine.equals("")) ans += MaTV + "," + DiemThuong +","+ Level +"\n";
    	}
    	System.out.println(error);
    	WriteErrorToFile(error);
    	System.out.println(ans);
		return ans;
    }
    public static void main(String args[]) {
    	try 
    	{
			String s1 = readFromFile("data1.txt");
			InsertToDB(s1);
			String s2 = readFromFile("data2.txt");
			String s2_checked = checkFile(s2);
			EditDB(s2_checked);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}