import java.io.*;
public class BT4_2b {
	public int nhapso() throws IOException
	{
		InputStreamReader lv = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(lv);
		String s = br.readLine();
		return Integer.parseInt(s);
	}
	
	public boolean soDoiXung(int n)
	{
		int pos = 0, temp = n;
		while (temp > 0) {
			pos = pos * 10 + temp % 10;
			temp /= 10;
		}
		if (pos == n) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		BT4_2b dt = new BT4_2b();
		int n = 0;
		try 
		{
			do
			{
				System.out.print("Nhap so nguyen duong n: ");
				n = dt.nhapso();
			}
			while(n <= 0);
		}
		catch(Exception e) {}
		if (dt.soDoiXung(n)) {
			System.out.println(" La so doi xung");
		} else {
			System.out.println(" Khong la so doi xung");
		}
	}
}
