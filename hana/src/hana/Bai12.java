package hana;

public class Bai12 {
	public static long Tong(int n) {
		int m, tong = 0;
		while (n > 0) {
			m = n % 10;
			tong = tong + m;
			n = n / 10;
		};
		return tong;
	}

	public static long Tich(int n) {
		long tich = 1;
		while (n > 0) {
			int t = n % 10;
			tich = tich * t;
			n = n / 10;
		}
		return tich;
	}

	public static void main(String[] args) {
		System.out.println("Tong = " + Tong(163));
		System.out.println("Tich = " + Tich(123));
	}

}
