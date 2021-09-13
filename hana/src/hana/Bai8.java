package hana;

public class Bai8 {
	public static double Tinh(int n)
	{
		double s = 0;
		for( int i = 1 ; i <= n ; i++)
		{
			s = s + (double) 1/i;
		}	
		return s;
	}
	
	public static void main(String[] args) 
	{
		double kq = Tinh(1);
		System.out.println("S = " + kq);
	}

}
