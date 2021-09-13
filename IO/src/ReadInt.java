import java.io.*;
public class ReadInt {
	public int nhapso() throws IOException
	{
		InputStreamReader lv = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(lv);
		String s = br.readLine();
		return Integer.parseInt(s);
	}
	
	public void TongTich(int m)
	{
		int s = 0, p = 1;
		while(m!=0)
		{
			s+=(m%10);
			p*=(m%10);
			m/=10;
		}
		System.out.println("Tong cac chu so la: " + s);
		System.out.println("Tich cac chu so la: " + p);
	}
	
	public static void main(String args[])
	{
		ReadInt dt = new ReadInt();
		int m = 0;
		try 
		{
			do
			{
				System.out.print("Nhap so nguyen duong m: ");
				m = dt.nhapso();
			}
			while(m <= 0);
		}
		catch(Exception e) {}
		System.out.println("So da nhap la: " + m);
		dt.TongTich(m);
	}
}
