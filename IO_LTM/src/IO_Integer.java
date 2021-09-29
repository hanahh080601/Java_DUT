import java.io.*;

public class IO_Integer {
	public int nhapso() throws IOException
	{
		InputStreamReader lv = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(lv);
		String s = br.readLine();
		return Integer.parseInt(s);
	}
	
	public int Tong(int m)
	{
		int s = 0;
		while(m!=0)
		{
			s+=(m%10);
			m/=10;
		}
		return s;
	}
	
	public int DaoSo(int n) 
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
	
	public int Fibonacci(int n) {
		  int a = 0;
		  int b = 1;
		  int next = 0;
		  for(int i = 0; i <= n; i++) {
		    if (i <= 1) {
		      next = i;
		    } 
		    else {
		      next = a + b;
		      a = b;
		      b = next;
		    }
		  }
		  return next;
		}

	public void CheckFibo(int n) {
		int k = 0;
		for (int i = 0; i <= n; i++) {
			if (n == Fibonacci(i)) {
				System.out.println("c. So " + n + " la so Fibo. Vi tri la: " + (i + 1));
				k = i;
				break;
			}
		}
		if (k == 0)
			System.out.println("c. So " + n + " khong la so Fibo");
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
		} 
		else {
			return false;
		}
	}
	
	public static void main(String args[])
	{
		IO_Integer dt = new IO_Integer();
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
		System.out.println("a. Tong cac chu so cua m la: " + dt.Tong(m));
		System.out.println("b. So dao nguoc cua m la: " + dt.DaoSo(m));
		dt.CheckFibo(m);
		if(dt.soDoiXung(m)) {
			System.out.println("d. So dao nguoc cua m la so doi xung");
		}
		else System.out.println("d. So dao nguoc cua m khong phai la so doi xung");
	}	
}
