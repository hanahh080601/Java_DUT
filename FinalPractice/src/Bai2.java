import java.sql.*;
import java.text.*;
import java.io.*;
import java.util.regex.*;

public class Bai2 {
	public static Connection con;
	public static void Connect()
	{
		System.out.println("Connecting...");
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-1QK42IB\\SQLEXPRESS:1433; DatabaseName=DATA1",
					"sa", "123456");
			System.out.println("Connected!");
		}
		catch(Exception e)
		{
			System.out.println("Error!");
		} 
	}
	
	public static void main(String args[])
	{
		Connect();
		try 
		{
			File f_input = new File("input.txt");
			FileReader fr = new FileReader(f_input);
			
			File f_error = new File("error.txt");
			FileWriter fw = new FileWriter(f_error);
			
			BufferedReader br = new BufferedReader(fr);
			String line;
			
			int count = 0;
			boolean check = true;
			
			while((line = br.readLine()) != null)
			{
				count += 1;
				String id = line.substring(0, 10);
				String name = line.substring(10, 60);
				String dob = line.substring(60, 70);
				String day = dob.substring(0,2);
		        String month = dob.substring(3,5);
		        String year = dob.substring(6,10);
		        String gender = line.substring(70,73);
		    	String dt = line.substring(73);
		        if(!isValidDate(dob))
		        {
		        	fw.write("Dong " + count + ": Loi dinh dang ngay sinh!\n");
		        	check = false;
		        }
		        if(Float.parseFloat(dt) > 10 || Float.parseFloat(dt) < 0)
		        {
		        	fw.write("Dong " + count + ": Loi diem thi khong hop le!\n");
		        	check = false;
		        }
		        if(check == true)
		        {
		        	String date = month + "/" + day + "/" + year;
		        	String query = "Insert into HOCVIEN values('" + id + "','" + name.trim() + "','" + date + "','" + gender.trim() + "'," + dt +")";
		        	Statement stmt = con.createStatement();
		        	stmt.executeUpdate(query);
		        	stmt.close();
		        }
			}
			fr.close();
			fw.close();
			br.close();	
		} catch (Exception e) {
			System.out.println("Reading file error:" + e);
		}
	}
	
	private static Pattern dateRegexPattern = Pattern.compile("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)");

    public static boolean isValidDate(String dateString) {
        Matcher dateMatcher = dateRegexPattern.matcher(dateString);

        if (dateMatcher.matches()) {

           dateMatcher.reset();

           if (dateMatcher.find()) {
               String day = dateMatcher.group(1);
               String month = dateMatcher.group(2);
               int year = Integer.parseInt(dateMatcher.group(3));

               if ("31".equals(day) && 
                  ("4".equals(month) || "6".equals(month) || "9".equals(month) ||
                   "11".equals(month) || "04".equals(month) || "06".equals(month) || 
                   "09".equals(month))) {
                   return false; // 1, 3, 5, 7, 8, 10, 12 has 31 days
               } else if ("2".equals(month) || "02".equals(month)) {
                    //leap year
                    if (year % 4 == 0) {
                        return !"30".equals(day) && !"31".equals(day);
                    } else {
                        return !"29".equals(day) && !"30".equals(day) && !"31".equals(day);
                    }
               } else {
                   return true;
               }
           } else {
               return false;
           }
        } else {
            return false;
        }
    }
}
