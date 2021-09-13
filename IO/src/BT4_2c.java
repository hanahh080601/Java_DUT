import java.io.*;
public class BT4_2c {
	public int nhapso() throws IOException
	{
		InputStreamReader lv = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(lv);
		String s = br.readLine();
		return Integer.parseInt(s);
	}
	
	public int Fibo(int n) {
		if (n == 0 || n == 1)
			return 1;
		else
			return Fibo(n - 1) + Fibo(n - 2);
	}

	public void CheckFibo(int n) {
		int k = 0;
		for (int i = 0; i <= n; i++) {
			if (n == Fibo(i)) {
				System.out.println(n + " la so Fibo. Vi tri la: " + (i + 1));
				k = i;
				break;
			}
		}
		if (k == 1)
			System.out.println(n + " khong la so Fibo");
	}
	
	public static void main(String args[])
	{
		BT4_2c bt = new BT4_2c();
		int n = 0;
		try 
		{
			do
			{
				System.out.print("Nhap so nguyen duong n: ");
				n = bt.nhapso();
			}
			while(n <= 0);
		}
		catch(Exception e) {}
		bt.CheckFibo(n);
	}
}
