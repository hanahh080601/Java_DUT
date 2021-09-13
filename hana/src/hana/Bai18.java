package hana;

public class Bai18 {
	public static void main(String[] args) {
		int n, S;
		n = 1;
		while (n < 1000) {
			S = 0;
			for (int i = 1; i < n; i++)
				if (n % i == 0)
					S += i;
			if (S == n)
				System.out.println(n);
			n++;
		};
	}
}
