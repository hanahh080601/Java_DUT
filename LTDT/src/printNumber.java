
class A extends Thread
{
	public void run()
	{
		for(int i = 0; i < 10; i++)
		{
			System.out.print(i + " ");
			try {
				Thread.sleep(1000);
			}
			catch(InterruptedException e) {
				
			}
		}
	}
}

class B extends Thread
{
	public void run()
	{
		for(int i = 0; i < 10; i++)
		{
			System.out.print((char)(i+65) + " ");
			try {
				Thread.sleep(2500);
			}
			catch(InterruptedException e) {
				
			}
		}
	}
}

public class printNumber {
	public static void main(String args[])
	{
		new A().start();
		new B().start();
	}
}
