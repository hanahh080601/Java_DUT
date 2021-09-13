package hana;

public class Bai11 {
	public static int GT(int n) {
		int gt = 1;
		while (n > 0) {
			gt *= n;
			n -= 2;
		};
		return gt;
	}

	public static void main(String[] args) {
		System.out.println("Giai thua = " + GT(3));
	}

}
