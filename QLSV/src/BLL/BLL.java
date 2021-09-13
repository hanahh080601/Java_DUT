package BLL;
import java.util.Vector;

import DAL.DAL;
import DTO.LSH;
import DTO.SV;

public class BLL {
	private static BLL instance = new BLL();
	private BLL(){}
	public static BLL getInstance()
	{
	   return instance;
	}
	
	public Vector<SV> GetListSV()
	{
		return DAL.getInstance().GetListSV();
	}
	

	public Vector<SV> GetListSVByMSSV(Vector<String> lmssv)
	{
		Vector<SV> listSvs = new Vector<SV>();
		for (String i : lmssv) 
		{
			listSvs.add(DAL.getInstance().GetSVByMSSV(i));
		}
		return listSvs;
	}
	
	public SV getSVByMSSV(String mssv)
	{
		return DAL.getInstance().GetSVByMSSV(mssv);
	}
	
	public Vector<LSH> GetListLSH()
	{
		return DAL.getInstance().GetListLSH();
	}
	
	public Vector<String> GetNameCol()
	{
		return DAL.getInstance().GetNameCol();
	}
	
	public Vector<SV> GetSVByNameAndIDLop(String name, int idlop)
	{
		return DAL.getInstance().GetSVByNameAndIDLop(name, idlop);
	}
	
	public void AddSV(SV s)
	{
		DAL.getInstance().AddSV(s);
	}
	
	public void Edit(SV s)
	{
		DAL.getInstance().Edit(s);
	}
	
	public void DeleteSV(String id)
	{
		DAL.getInstance().DeleteSV(id);
	}
	
	public void Execute_DB(SV sv)
	{
		int check = -1;
		for (SV s : GetListSV()) {
			if(s.MSSV.equals(sv.MSSV)) //hay, xứng đáng ăn chửi :v
			{
				DAL.getInstance().Edit(sv);
				check = 0;
			}
		}
		if(check == -1) DAL.getInstance().AddSV(sv);
		System.out.println(check);
	}
	
	public Vector<SV> SortSV(Vector<String> lmssv, int index)
	{
		Vector<SV> list = GetListSVByMSSV(lmssv);
		switch(index)
		{
			case 0:
				for(int i = 0; i < list.size() - 1; i++)
				{
					for(int j = i + 1; j < list.size(); j++)
					{
						if(!(SV.ComparatorMSSV(list.get(i), list.get(j))))
						{
							SV temp = list.get(i);
							list.set(i, list.get(j));
							list.set(j, temp);	
						}
					}
				}
				break;
			case 1:
				for(int i = 0; i < list.size() - 1; i++)
				{
					for(int j = i + 1; j < list.size(); j++)
					{
						if(!(SV.ComparatorName(list.get(i), list.get(j))))
						{
							SV temp = list.get(i);
							list.set(i, list.get(j));
							list.set(j, temp);	
						}
					}
				}
				break;
			case 2:
				for(int i = 0; i < list.size() - 1; i++)
				{
					for(int j = i + 1; j < list.size(); j++)
					{
						if(!(SV.ComparatorGender(list.get(i), list.get(j))))
						{
							SV temp = list.get(i);
							list.set(i, list.get(j));
							list.set(j, temp);	
						}
					}
				}
				break;
			case 3:
				for(int i = 0; i < list.size() - 1; i++)
				{
					for(int j = i + 1; j < list.size(); j++)
					{
						if(!(SV.ComparatorNS(list.get(i), list.get(j))))
						{
							SV temp = list.get(i);
							list.set(i, list.get(j));
							list.set(j, temp);	
						}
					}
				}
				break;
			case 4:
				for(int i = 0; i < list.size() - 1; i++)
				{
					for(int j = i + 1; j < list.size(); j++)
					{
						if(!(SV.ComparatorIDLop(list.get(i), list.get(j))))
						{
							SV temp = list.get(i);
							list.set(i, list.get(j));
							list.set(j, temp);	
						}
					}
				}
				break;
		}
		return list;
	}

}
