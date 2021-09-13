import java.awt.*;
import java.awt.event.*;
class Bai6 extends Frame implements ActionListener
{
	
		Label lb1, lb2, lb3, lb;
		TextField txta, txtb, txtkq;
		Button tinh, reset, thoat;
		Panel pn, pn1, pn2, pn3;
		
		public void GUI()
		{
			lb = new Label("TINH TIEN KARAOKE");
			lb1 = new Label("Nhap a: ");
			lb2 = new Label("Nhap b: ");
			lb3 = new Label("Tong tien: ");
			
			txta = new TextField();
			txtb = new TextField();
			txtkq = new TextField();
			
			tinh = new Button("Tinh tien");
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
				float a = Float.parseFloat(txta.getText());
				float b = Float.parseFloat(txtb.getText());
				float x;
				if(a >= 0 && b >= 0 && a <= 24 && b<= 24 )
				{
					if(b > a)
					{
						if(a >= 18 && b >= 18)
						{
							x = (b-a)*60000;
							txtkq.setText("Tong tien la: " + Float.toString((float)x));
						} 
						else if( a < 18 && b < 18)
						{
							x = (b-a)*45000;
							txtkq.setText("Tong tien la: " + Float.toString((float)x));
						}
						else 
						{
							x = (b-18)*60000 + (18-a)*45000;
							txtkq.setText("Tong tien la: " + Float.toString((float)x));
						}
					}
					else 
					{
						txtkq.setText("Vui lòng nhập giờ bắt đầu trước giờ kết thúc");
					}
				}
				else 
				{
					txtkq.setText("Vui lòng nhập giờ trong khoảng từ 0 đến 24");
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
		
		public Bai6(String st)
		{
			super(st);
			GUI();
		}
		
		public static void main(String[] args)
		{
			new Bai6("Tinh tien Karaoke");
		}
}