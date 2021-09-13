package hana;

public class Bai2 {
	public static void ptb1(double a, double b)
	{
		double x;
		if(a == 0)
		{
			if(b == 0) 
			{
				System.out.println("PT co vo so nghiem");
			}
			else 
			{
				System.out.println("PT vo nghiem");
			}
		}
		else 
		{
			x = -b / a;
			System.out.println("PT co nghiem la: " + x);
		}
		return;
	}

	public static void main(String[] args) 
	{
		ptb1(10.0,25.0);
	}

}
