package hana;

public class Bai6 {
	
	public static void main(String[] args)
	{
		Karaoke(-12, 10);
	}
	
	public static void Karaoke(int a, int b)
	{
		int x;
		if(a >= 0 && b >= 0 && a <= 24 && b<= 24 )
		{
			if(b > a)
			{
				if(a >= 18 && b >= 18)
				{
					x = (b-a)*60000;
					System.out.println("Tong tien la: " + x);
				} 
				else if( a < 18 && b < 18)
				{
					x = (b-a)*45000;
					System.out.println("Tong tien la: " + x);
				}
				else 
				{
					x = (b-18)*60000 + (18-a)*45000;
					System.out.println("Tong tien la: " + x);
				}
			}
			else 
			{
				System.out.println("Vui lòng nhập giờ bắt đầu trước giờ kết thúc");
			}
		}
		else 
		{
			System.out.println("Vui lòng nhập giờ trong khoảng từ 0 đến 24");
		}
	}
	
}
