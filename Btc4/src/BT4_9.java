import java.awt.*;
import java.awt.event.*;
class BT4_9 extends Frame implements ActionListener
{
	
		Label lb1, lb2, lb;
		TextField txta, txtkq;
		Button tim, reset, exit;
		Panel pn, pn1, pn2, pn3;
		
		public void GUI()
		{
			lb = new Label("IN RA DAY SO NGUYEN TO NHO HON HOAC BANG N");
			lb1 = new Label("Nhap n: ");
			lb2 = new Label("Ket qua");
			
			txta = new TextField();
			txtkq = new TextField();
			txtkq.enable(false);
			
			tim = new Button("Tim");
			reset = new Button("Reset");
			exit = new Button("Exit");
			
			tim.addActionListener(this);
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
			
			pn3.add(tim);
			pn3.add(reset);
			pn3.add(exit);
			
			pn.add(pn1);
			pn.add(pn2);
			pn.add(pn3);
			
			add(pn); //add vào frame
			setSize(400,300); // thiết lập kích thước
			show();// hiển thị
		}
		
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == tim)
			{
				int a = Integer.parseInt(txta.getText());
				String s = "2 ";
				if(a < 2) txtkq.setText("Khong ton tai so nguyen to nho hon 2");
				else
				{
					for (int i = 3; i < a; i +=2 ) 
					{
						boolean check = true;
						for (int j = 2; j <= ((int) Math.sqrt(i)); j++) 
						{
							if (i % j == 0) check = false;
						}
						if(check == true) s += (Integer.toString((int)i) + " ");
					}
					txtkq.setText(s);	
				}
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
		
		public BT4_9(String st)
		{
			super(st);
			GUI();
		}
		
		public static void main(String[] args)
		{
			new BT4_9("In ra so nguyen to nho hon hoac bang n");
		}
}