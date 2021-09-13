import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class BT4_2a {
	public int nhapso() throws IOException
	{
		InputStreamReader lv = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(lv);
		String s = br.readLine();
		return Integer.parseInt(s);
	}
	
	public int changeNumber(int n) 
	{
		String m = Integer.toString(n);
		int count = Integer.toString(n).length();
		String c = "";
		for(int i = count-1; i >= 0; i--)
		{
			c += m.charAt(i);
		}
		return Integer.parseInt(c);
	}
	
	public static void main(String args[])
	{
		BT4_2a dt = new BT4_2a();
		int n = 0;
		try 
		{
			do
			{
				System.out.print("Nhap so nguyen n: ");
				n = dt.nhapso();
			}
			while(n <= 0);
		}
		catch(Exception e) {}
		System.out.println("So dao la: " + dt.changeNumber(n));
	}
}
