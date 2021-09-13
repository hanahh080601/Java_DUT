package hana;

public class Bai14 {
	public static boolean soNT(int n) {
		if (n <= 1) {
			return false;
		}
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		if (soNT(7)) {
			System.out.println("La so nguyen to");
		} else {
			System.out.println("Khong la so nguyen to");
		}

	}

}
