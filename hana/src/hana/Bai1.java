package hana;

public class Bai1 {

	public static double max(double a, double b, double c) {
		if (a > b && a > c)
			return (a);
		else if (b > c)
			return (b);
		else
			return (c);
	}

	public static double max1(double a, double b, double c) {
		double max = a;
		if (max < b)
			max = b;
		if (max < c)
			max = c;
		return max;
	}

	public static double max2(double a, double b, double c) {
		double max;
		max = (a > b && a > c) ? a : ((b > c) ? b : c);
		return max;
	}

	public static void main(String[] args) {
		System.out.println("Max cua 3 so la: " + max1(12, 32.4, 1.5));
	}
}
