package hana;

public class Bai10 {
	public static double GiaiThua(int n) {
		int giai_thua = 1;
		for (int i = 1; i <= n; i++)
			giai_thua *= i;
		return giai_thua;
	}

	public static void main(String[] args) {
		double s = 0;
		for (int i = 1; i <= 5; i++) {
			s += (double) 1 / GiaiThua(2 * i - 1);
		}
		System.out.println("S = " + s);
	}
}
