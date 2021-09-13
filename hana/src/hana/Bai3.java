package hana;

public class Bai3 {
	public static void ptb2(float a, float b, float c)
	{
		if(a == 0)
		{
			if(b == 0)
			{
				System.out.println("PTVN");
			} 
			else 
			{
				System.out.println("PT có một nghiệm x =" + (- c/b));
			}
		}
		
		float delta = b*b - 4*a*c;
		float x1, x2 ;
		
		if(delta > 0)
		{
			x1 = (float) ((-b + Math.sqrt(delta)) / (2*a));
            x2 = (float) ((-b - Math.sqrt(delta)) / (2*a));
            System.out.println("PT có 2 nghiệm : " + " x1= " + x1 + " x2 "+ x2 );
		}else if( delta == 0) {
			x1 = ( -b / 2*a);
			System.out.println("PT có nghiệm kép x1 = x2 = " + x1);
		}else {
			System.out.println("PTVN");
		}
	}

	public static void main(String[] args) 
	{
		ptb2(1.2f, 22.2f, 4.4f);	
	}
}
