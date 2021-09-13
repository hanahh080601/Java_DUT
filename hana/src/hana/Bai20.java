package hana;

public class Bai20 {
	public static int Fibo(int n) {
		if (n == 0 || n == 1)
			return 1;
		else
			return Fibo(n - 1) + Fibo(n - 2);
	}

	public static void CheckFibo(int n) {
		int k = 0;
		for (int i = 0; i <= n; i++) {
			if (n == Fibo(i)) {
				System.out.println(n + " la so Fibo. Vi tri la: " + (i + 1));
				k = i;
				break;
			}
		}
		if (k == 0)
			System.out.println(n + " khong la so Fibo");
	}

	public static void main(String[] args) {
		CheckFibo(30);
	}
}
