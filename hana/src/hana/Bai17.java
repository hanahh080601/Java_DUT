package hana;

public class Bai17 {
	public static boolean soNT(int n) {
		if (n < 2) {
			return false;
		}
		int squareRoot = (int) Math.sqrt(n);
		for (int i = 2; i <= squareRoot; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static void Out(int n) {
		System.out.printf("Tat ca cac so nguyen to nho hon %d la: \n", n);
		if (n >= 2) {
			System.out.print(2);
		}
		for (int i = 3; i < n; i += 2) {
			if (soNT(i)) {
				System.out.print(" " + i);
			}
		}
	}

	public static void main(String[] args) {
		Out(30);
	}

}
