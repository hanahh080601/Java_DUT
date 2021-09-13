package hana;

public class Bai9 {
	public static int GiaiThua(int n) {
		int s = 1;
		for (int i = 2; i <= n; i++) {
			s *= i;
		}
		return s;
	}

	public static void main(String[] args) {
		double s = 15.0;
		for (int i = 1; i <= 5; i++) {
			s += (double) (Math.pow(-1, i) / GiaiThua(i));
		}
		System.out.println("S = " + s);
	}
}
