package hana;

public class Bai7 {
	public static void SoNgay(int month, int year)
	{
		switch(month)
		{
			case 1,3,5,7,8,10,12:
				System.out.println("So ngay cua thang " + month + " la: 31 ngay");
				break;
			case 4,6,9,11:
				System.out.println("So ngay cua thang " + month + " la: 30 ngay");
				break;
			case 2:
				if((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
				{
					System.out.println("So ngay cua thang " + month + " la: 29 ngay");
				}
				else
				{
					System.out.println("So ngay cua thang " + month + " la: 28 ngay");
				}
				break;
			default:
                System.out.println("Nhập tháng không hợp lệ.");
		}
	}

	public static void main(String[] args)
	{
		SoNgay(2,2021);
	}
}
