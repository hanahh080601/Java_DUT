import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BT4_1 {
	public int nhapso() throws IOException
	{
		InputStreamReader lv = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(lv);
		String s = br.readLine();
		return Integer.parseInt(s);
	}
	
	public int UCLN(int a, int b) {
		while (a != b) {
			if (a > b) {
				a = a - b;
			} else {
				b = b - a;
			}
		}
		return a;
	}
	
	public int BCNN(int a, int b) {
		int kq = UCLN(a, b);
		return a * b / kq;
	}
	
	public static void main(String args[])
	{
		BT4_1 ub = new BT4_1();
		int a = 0, b = 0;
		try
		{
			do
			{
				System.out.print("Nhap a:");
				a = ub.nhapso();
			}
			while (a < 0);
			do
			{
				System.out.print("Nhap b:");
				b = ub.nhapso();
			}
			while (b < 0);
		}
		catch(Exception e) {
			System.out.println("a,b phai la so nguyen khong am!");
		}
		System.out.println("UCLN cua a, b la: " + ub.UCLN(a, b));
		System.out.println("BCNN cua a, b la: " + ub.BCNN(a, b));
	}
}
