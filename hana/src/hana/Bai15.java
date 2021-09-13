package hana;

public class Bai15 {
	static boolean check(double x)
    {
        double sq = Math.sqrt(x);
        return ((sq - Math.floor(sq)) == 0);
    }
	public static void main(String[] args) {
        if (check(36))
            System.out.println("La so chinh phuong");
        else
            System.out.println("Khong la so chinh phuong");

	}


}
