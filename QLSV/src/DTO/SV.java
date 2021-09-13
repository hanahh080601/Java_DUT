package DTO;
import java.util.*;
import java.util.Calendar;

public class SV{
	public String MSSV;
	public String NameSV;
	public boolean Gender;
	public Date NS;
	public int ID_Lop;
	
	public SV() {}
	
	public static boolean ComparatorMSSV(SV o1, SV o2) {
			if(o1.MSSV.compareToIgnoreCase(o2.MSSV) > 0) return false;
			else return true;
		}
	
	public static boolean ComparatorName(SV o1, SV o2) 
	{
		if(o1.MSSV.compareToIgnoreCase(o2.MSSV) > 0) return false;
		else return true;
	}
	
	public static boolean ComparatorGender(SV o1, SV o2) 
	{
		if (Boolean.compare(o1.Gender, o2.Gender) <= 0) return false;
		else return true;
	}
	
	public static boolean ComparatorNS(SV o1, SV o2) 
	{
		if(o1.NS.before(o2.NS)) return true;
		else return false;
	}
	
	public static boolean ComparatorIDLop(SV o1, SV o2) 
	{
		if(o1.ID_Lop <= o2.ID_Lop) return true;
		else return false;
	}
	
	public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);    
        int year = calendar.get(Calendar.YEAR);
        return year;
    }

	public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        return month;
    }

    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return day;
    }
}
