package hana;

public class Bai16 {
	public static boolean soDoiXung(int n) {
		int pos = 0, temp = n;
		while (temp > 0) {
			pos = pos * 10 + temp % 10;
			temp /= 10;
		}
		if (pos == n) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		boolean check = soDoiXung(121221);
		if (check) {
			System.out.println(" La so doi xung");
		} else {
			System.out.println(" Khong la so doi xung");
		}

	}

}
