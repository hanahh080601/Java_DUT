package hana;

public class Bai13 {

	public static int UCLN(int a, int b) {
		while (a != b) {
			if (a > b) {
				a = a - b;
			} else {
				b = b - a;
			}
		}
		return a;
	}

	public static int BCNN(int a, int b) {
		int kq = UCLN(a, b);
		return a * b / kq;
	}

	public static void main(String[] args) {
		System.out.println("UCLN = " + UCLN(12, 24) + ", BCNN = " + BCNN(12, 24));
	}

}
