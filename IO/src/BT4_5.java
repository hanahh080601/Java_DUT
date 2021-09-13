import java.io.*;
public class BT4_5 {
	static int a[], n = 0, k = 0, p = 0;
	
	public int nhapso() throws IOException
	{
		InputStreamReader lv = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(lv);
		String s = br.readLine();
		return Integer.parseInt(s);
	}
	
	private int Sum(int a[], int n)
	{
		int sum = 0;
		for(int i = 0; i < n; i++)
		{
			if(a[i] > 0 && a[i]%2 == 1)
			{
				sum += a[i];
			}
		}
		return sum;
	}
	
	private int Find(int a[], int n, int k)
	{
		for(int i = 0; i < n; i++)
		{
			if(a[i] == k) return i;
		}
		return -1;
	}
	
	private void Arrange(int a[], int n)
	{
		for(int i = 0; i < n-1; i++)
		{
			for(int j = i + 1; j < n; j ++)
			{
				if(a[i] > a[j])
				{
					int temp = a[i];
					a[i] = a[j];
					a[j] = temp;
				}
			}
		}
		for(int i = 0; i < n; i++)
		{
			System.out.println("a[" + i + "] = " + a[i]);
		}
	}
	
	private void insertArrange(int a[], int n, int p)
	{
		for(int i = 0; i < n-1; i++)
		{
			for(int j = i + 1; j < n; j ++)
			{
				if(a[i] > a[j])
				{
					int temp = a[i];
					a[i] = a[j];
					a[j] = temp;
				}
			}
		}
		int index = 0;
		for(int i = 0; i < n; i++)
		{
			if(a[i] <= p && a[i+1] >= p) index = i;
		}
		for(int i = 0; i <= index; i++)
		{
			System.out.println("a[" + i + "] = " + a[i]);
		}
		System.out.println("a[" + (index + 1) +"] = " + p);
		for(int i = index + 2; i < n + 1; i++)
		{
			System.out.println("a[" + i + "] = " + a[i-1]);
		}
	}
	
	public static void main(String args[])
	{
		BT4_5 bt = new BT4_5();
		try 
		{
			do
			{
				System.out.print("Nhap so phan tu mang n (n > 0): ");
				n = bt.nhapso();
			}
			while(n <= 0);
		}
		catch(Exception e) {}
		a = new int [n];
		for(int i = 0; i < n; i++)
		{
			try {
				System.out.print("a[" + i + "] = ");
				a[i] = bt.nhapso();
			}
			catch(Exception e) {}
		}
		System.out.println("BT4_5b: Tong cac so duong le la " + bt.Sum(a, n));
		try 
		{
			do
			{
				System.out.print("Nhap so phan tu mang k (k > 0): ");
				k = bt.nhapso();
			}
			while(k <= 0);
		}
		catch(Exception e) {}
		if(bt.Find(a, n, k) == -1) System.out.println("Khong ton tai phan tu = k");
		else System.out.println("BT4_5c:" + k + " o vi tri " + bt.Find(a, n, k));
		System.out.println("BT4_5d: Mang a sau khi sap xep");
		bt.Arrange(a, n);
		try 
		{
			do
			{
				System.out.print("Nhap so phan tu mang p (p > 0): ");
				p = bt.nhapso();
			}
			while(p <= 0);
		}
		catch(Exception e) {}
		System.out.println("BT4_5e: Mang a sau khi chen va sap xep");
		bt.insertArrange(a, n, p);
		
	}
}
