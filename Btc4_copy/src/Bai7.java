import java.awt.*;
import java.awt.event.*;
class Bai7 extends Frame implements ActionListener
{
	
		Label lb1, lb2, lb3, lb;
		TextField txta, txtb, txtkq;
		Button tinh, reset, thoat;
		Panel pn, pn1, pn2, pn3;
		
		public void GUI()
		{
			lb = new Label("TINH SO NGAY THEO THANG, NAM");
			lb1 = new Label("Nhap thang: ");
			lb2 = new Label("Nhap nam: ");
			lb3 = new Label("So ngay: ");
			
			txta = new TextField();
			txtb = new TextField();
			txtkq = new TextField();
			txtkq.enable(false);
			
			tinh = new Button("So ngay");
			reset = new Button("Reset");
			thoat = new Button("Thoat");
			
			tinh.addActionListener(this);
			reset.addActionListener(this);
			thoat.addActionListener(this);
			
			pn = new Panel( new GridLayout(3,1));
			pn1 = new Panel( new FlowLayout());
			pn2 = new Panel( new GridLayout(3,2));
			pn3 = new Panel( new GridLayout(1,3));
			
			pn1.add(lb);
			
			pn2.add(lb1);
			pn2.add(txta);
			pn2.add(lb2);
			pn2.add(txtb);
			pn2.add(lb3);
			pn2.add(txtkq);
			
			pn3.add(tinh);
			pn3.add(reset);
			pn3.add(thoat);
			
			pn.add(pn1);
			pn.add(pn2);
			pn.add(pn3);
			
			add(pn); //add vào frame
			setSize(400,300); // thiết lập kích thước
			show(); // hiển thị
		}
		
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == tinh)
			{
				int a = Integer.parseInt(txta.getText());
				int b = Integer.parseInt(txtb.getText());
				switch(a)
				{
					case 1,3,5,7,8,10,12:
						txtkq.setText("So ngay cua thang " + Integer.toString((int)a) + " la: 31 ngay");
						break;
					case 4,6,9,11:
						txtkq.setText("So ngay cua thang " + Integer.toString((int)a) + " la: 30 ngay");
						break;
					case 2:
						if((b % 4 == 0 && b % 100 != 0) || (b % 400 == 0))
						{
							txtkq.setText("So ngay cua thang " + Integer.toString((int)a) + " la: 29 ngay");
						}
						else
						{
							txtkq.setText("So ngay cua thang " + Integer.toString((int)a) + " la: 28 ngay");
						}
						break;
					default:
		                txtkq.setText("Nhập tháng không hợp lệ.");
				}
			}
			if(e.getSource() == reset)
			{
				txta.setText("");
				txtb.setText("");
				txtkq.setText("");
			}
			if(e.getSource() == thoat)
			{
				System.exit(0);
			}
		} 
		
		public Bai7(String st)
		{
			super(st);
			GUI();
		}
		
		public static void main(String[] args)
		{
			new Bai7("Tinh so ngay theo thang, nam");
		}
}