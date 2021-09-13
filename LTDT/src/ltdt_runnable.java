
public class ltdt_runnable {
	public static void main (String args[])
	{
		Thread dt1 = new Thread(new Chao1());
		dt1.start();
		Thread dt2 = new Thread(new Chao2());
		dt2. start();
	}
}

class Chao1 implements Runnable
{
	public void run()
	{
		for(int i = 0; i < 5000; i++)
		{
			System.out.println("Xin chao");
		}
	}
}

class Chao2 implements Runnable
{
	public void run()
	{
		for(int i = 0; i < 5000; i++)
		{
			System.out.println("Hello");
		}
	}
}
