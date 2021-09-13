import java.awt.*;
import java.awt.event.*;
class BT4_10 extends Frame implements ActionListener
{
	
		Label lb1, lb2, lb;
		TextField txta, txtkq;
		Button ok, reset, exit;
		Panel pn, pn1, pn2, pn3;
		
		public void GUI()
		{
			lb = new Label("KIEM TRA 1 SO CO THUOC DAY FIBO HAY KHONG");
			lb1 = new Label("Nhap n: ");
			lb2 = new Label("Ket qua");
			
			txta = new TextField();
			txtkq = new TextField();
			txtkq.enable(false);
			
			ok = new Button("OK");
			reset = new Button("Reset");
			exit = new Button("Exit");
			
			ok.addActionListener(this);
			reset.addActionListener(this);
			exit.addActionListener(this);
			
			pn = new Panel( new GridLayout(3,1));
			pn1 = new Panel( new FlowLayout());
			pn2 = new Panel( new GridLayout(2,2));
			pn3 = new Panel( new FlowLayout());
			
			pn1.add(lb);
			
			pn2.add(lb1);
			pn2.add(txta);
			pn2.add(lb2);
			pn2.add(txtkq);
			
			pn3.add(ok);
			pn3.add(reset);
			pn3.add(exit);
			
			pn.add(pn1);
			pn.add(pn2);
			pn.add(pn3);
			
			add(pn); //add vào frame
			setSize(400,300); // thiết lập kích thước
			show(); // hiển thị
		}
		
		public static int Fibo(int n) {
			if (n == 0 || n == 1)
				return 1;
			else
				return Fibo(n - 1) + Fibo(n - 2);
		}

		public static String CheckFibo(int n) {
			int k = 0;
			String s = "";
			for (int i = 0; i <= n; i++) {
				if (n == Fibo(i)) {
					k = i;
					s += Integer.toString((int)n) + " thuoc day Fibo";
					return s;
				}
			}
			if (k == 0)
			{
				s += Integer.toString((int)n) + " khong thuoc day Fibo";
				return s;
			}
			return s;
		}
		
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == ok)
			{
				int n = Integer.parseInt(txta.getText());
				txtkq.setText(CheckFibo(n));
			}
			if(e.getSource() == reset)
			{
				txta.setText("");
				txtkq.setText("");
			}
			if(e.getSource() == exit)
			{
				System.exit(0);
			}
		} 
		
		public BT4_10(String st)
		{
			super(st);
			GUI();
		}
		
		public static void main(String[] args)
		{
			new BT4_10("Kiem tra 1 so co thuoc day Fibo hay khong");
		}
}