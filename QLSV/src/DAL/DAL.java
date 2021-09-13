package DAL;

import java.text.SimpleDateFormat;
import java.util.Vector;

import DTO.LSH;
import DTO.SV;

public class DAL {
	private static DAL instance = new DAL();
	private DAL(){}
	public static DAL getInstance()
	{
	   return instance;
	}
	
	public Vector<SV> GetListSV()
	{
		String query = "Select * from SV";
		return DBHelper.getInstance().GetDBSV(query);
	}
	
	public SV GetSVByMSSV(String id)
	{
		SV sv = new SV();
		for(SV s: GetListSV())
		{
			if(s.MSSV.equals(id))
			{
				sv = s;
			}
		}
		return sv;
	}
	
	public Vector<LSH> GetListLSH()
	{
		String query = "Select * from LSH";
		return DBHelper.getInstance().GetDBLSH(query);
	}
	
	public Vector<String> GetNameCol()
	{
		String query = "Select * from SV";
		return DBHelper.getInstance().GetNameCol(query);
	}
	
	public Vector<SV> GetSVByNameAndIDLop(String name, int idlop)
	{
		Vector<SV> list = new Vector<SV>();
		String query;
		if(idlop == 0 && name != "")
		{
			for (SV s : DBHelper.getInstance().GetDBSV("Select * from SV")) {
				if(s.NameSV.toUpperCase().contains(name.toUpperCase()))
				{
					list.add(s);
				}
			}
		}
		else
        {
            query = "select * from SV where ID_Lop = " + Integer.toString(idlop);
            if (name == "")
            {
                for(SV s : DBHelper.getInstance().GetDBSV(query))
                {
                    list.add(s);
                }
            }
            else
            {
                for(SV s:  DBHelper.getInstance().GetDBSV(query))
                {
                    if (s.NameSV.toUpperCase().contains(name.toUpperCase()))
                    {
                        list.add(s);
                    }
                }    
            }
        }
        return list;
	}
	
	public void DeleteSV(String id)
	{
		String query = "Delete from SV where MSSV = " + id;
		DBHelper.getInstance().UpdateDB(query);
	}
	
	public void AddSV(SV s)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(s.NS);
		int g;
		if(s.Gender == true) g = 1;
		else g = 0;
		String query = "Insert into SV values ('"+ s.MSSV + "','" + s.NameSV + "','" + Integer.toString(g) + "','" //non
		+ strDate + "'," + s.ID_Lop + ')'; 
		System.out.println(query);
		DBHelper.getInstance().UpdateDB(query);
	}
	
	public void Edit(SV s)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(s.NS);
		int g;
		if(s.Gender == true) g = 1;
		else g = 0;
		String query = "Update SV set NameSV = '" + s.NameSV + "', Gender=" + Integer.toString(g) 
		+ ", NS='" + strDate + "', ID_Lop=" + Integer.toString(s.ID_Lop) + "where MSSV = '" + s.MSSV + "'" ;
		System.out.println(query);
		DBHelper.getInstance().UpdateDB(query);
	}

}
