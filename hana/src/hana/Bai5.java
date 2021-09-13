package hana;

public class Bai5 {
	public static double stg(double a, double b, double c)
	{
		if(b < a && a < c || c < a && a < b)
			return a;
		if(a < b && b < c || c < b && b < a)
			return b;
		else return c;
	}
	public static void main(String[] args)
	{
		System.out.println("So trung gian la: " + stg(5, 20, 1));
	}
}
