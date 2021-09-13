
public class ltdt {
	public static void main(String[] args)
	{
		Thongbao1 dt1 = new Thongbao1();
		dt1.start();
		Thongbao2 dt2 = new Thongbao2();
		dt2.start();
	}
}

class Thongbao1 extends Thread
{
	public void run()
	{
		for(int i = 0; i < 5000; i++)
		{
			System.out.println("Xin chao");
		}
	}
}

class Thongbao2 extends Thread
{
	public void run()
	{
		for(int i = 0; i < 5000; i++)
		{
			System.out.println("Hello");
		}
	}
}
