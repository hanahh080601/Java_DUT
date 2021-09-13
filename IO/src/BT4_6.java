import java.io.*;
public class BT4_6 {
	static int m, n, a[][];
	
	public static int nhapso() throws IOException
	{
		InputStreamReader lv = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(lv);
		String s = br.readLine();
		return Integer.parseInt(s);
	}
	
	public static void NhapMT() {
		try 
		{
			do
			{
				System.out.print("Nhap so hang m = ");
				m = nhapso();
				System.out.print("Nhap so cot n = ");
				n = nhapso();
			}
			while(m <= 0 && n <= 0);
		}
		catch(Exception e) {
			System.out.println("Loi");
		}
		a = new int [m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				try {
				System.out.print("a[" + i + "][" + j + "] = ");
				a[i][j] = nhapso();
				}
				catch(Exception e) {
					System.out.println("Loi");
				}
			}
		}
	}
	
	private static int Tich(int a[][], int n)
	{
		int mul = 1;
		for(int i = 0; i < n; i++)
		{
			if(a[0][i] % 3 == 0) mul *= a[0][i];
		}
		return mul;
	}
	
	private static int[] arrayMax(int a[][], int n, int m)
	{
		int arr[] = new int [m];
		for(int i = 0; i < m; i++)
		{
			for(int j = 0; j < n-1; j++)
			{
				arr[i] = a[i][j];
				for(int k = j + 1; k < n; k++)
				{
					if(a[i][k] > arr[j]) arr[i] = a[i][k];
				}
			}
		}
		return arr;
	}
	
	private static int[] array(int a[], int m) {
		int temp[] = new int [m-1];
		for (int i = 0; i < m-1; i++) {
			temp[i] = a[i+1];
		}
		return temp;
	}
	
	public static void main(String args[]) {
		NhapMT();
		System.out.println(Tich(a, n));
		for (int i = 0; i < m; i++) {
			System.out.print(arrayMax(a, n, m)[i] + " ");
		}
		System.out.println();
		for (int i = 0; i < m-1; i++) {
			System.out.print(array(arrayMax(a, n, m), m)[i] + " ");
		}
	}
}
