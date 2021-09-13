package hana;

	public class Bai4 {		
		public static void hpt1(double a1, double b1, double c1, double a2, double b2, double c2)
		{
			double d , dx , dy , x , y;
			d = a1*b2 - a2*b1;
			dx = c1*b2 - c2*b1;
			dy = a1*c2 - a2*c1;
			
			if(d== 0)
			{
				if(dx + dy == 0)
				{
					System.out.println("HPTVSN");
				} 
				else 
				{
					System.out.println("HPTVN");
				}
			}
			else {
				x = dx/d;
				y = dy/d;
				System.out.println("x = " + x + ", y = " +y);
			}
		}
		public static void main(String[] args) 
		{
			hpt1(2,4,5,6,7,4);	
		}
	}