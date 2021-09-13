import java.awt.*;
import java.awt.event.*;
public class Bai4 extends Frame implements ActionListener
{
	Label lb1, lb2, lb3, lb4, lb5, lb6, lb7, lb, lbpt1, lbpt2, lbe;
	TextField txta, txtb, txtc, txtd, txte, txtf, txtkq;
	Button ketqua, reset, thoat;
	Panel pn, pn1, pn2, pn3, pn4, pn11, pn12, pn13, pn14;
	
	public void GUI()
	{
		lb = new Label("GIAI HE PT 1 AN DANG:");
		lbpt1 = new Label("ax + by = c" );
		lbpt2 = new Label("dx + ey = f");
		lbe = new Label("");
		lb1 = new Label("Nhap a: ");
		lb2 = new Label("Nhap b: ");
		lb3 = new Label("Nhap c: ");
		lb4 = new Label("Nhap d: ");
		lb5 = new Label("Nhap e: ");
		lb6 = new Label("Nhap f: ");
		lb7 = new Label("Ket qua: ");
		
		txta = new TextField();
		txtb = new TextField();
		txtc = new TextField();
		txtd = new TextField();
		txte = new TextField();
		txtf = new TextField();
		txtkq = new TextField();
		txtkq.enable(false);
		
		ketqua = new Button("Ket Qua");
		reset = new Button("Reset");
		thoat = new Button("Thoat");
		
		ketqua.addActionListener(this);
		reset.addActionListener(this);
		thoat.addActionListener(this);
		
		pn = new Panel( new GridLayout(4,1));
		pn11 = new Panel( new FlowLayout());
		pn12 = new Panel( new FlowLayout());
		pn13 = new Panel( new FlowLayout());
		pn14 = new Panel( new FlowLayout());
		pn1 = new Panel( new GridLayout(4, 1));
		pn2 = new Panel( new GridLayout(2,6));
		pn3 = new Panel( new GridLayout(1,2));
		pn4 = new Panel( new GridLayout(1,3));
		
		pn11.add(lb);
		pn12.add(lbpt1);
		pn13.add(lbpt2);
		pn14.add(lbe);
		
		pn1.add(pn11);
		pn1.add(pn12);
		pn1.add(pn13);
		pn1.add(pn14);
		
		pn2.add(lb1);
		pn2.add(txta);
		pn2.add(lb2);
		pn2.add(txtb);
		pn2.add(lb3);
		pn2.add(txtc);
		pn2.add(lb4);
		pn2.add(txtd);
		pn2.add(lb5);
		pn2.add(txte);
		pn2.add(lb6);
		pn2.add(txtf);
		
		pn3.add(lb7);
		pn3.add(txtkq);
		
		pn4.add(ketqua);
		pn4.add(reset);
		pn4.add(thoat);
		
		pn.add(pn1);
		pn.add(pn2);
		pn.add(pn3);
		pn.add(pn4);
		
		add(pn); //add vào frame
		setSize(450,380); // thiết lập kích thước
		show(); // hiển thị
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == ketqua)
		{
			double m , dx , dy , x , y;
			double a = Double.parseDouble(txta.getText());
			double b = Double.parseDouble(txtb.getText());
			double c = Double.parseDouble(txtc.getText());
			double d = Double.parseDouble(txtd.getText());
			double e1 = Double.parseDouble(txte.getText());
			double f = Double.parseDouble(txtf.getText());
			m = a*e1 - d*b;
			dx = c*e1 - f*b;
			dy = a*f - d*c;
			
			if(m == 0)
			{
				if(dx + dy == 0)
				{
					txtkq.setText("He pt vo so nghiem");
				} 
				else 
				{
					txtkq.setText("He pt vo nghiem");
				}
			}
			else {
				x = dx/d;
				y = dy/d;
				txtkq.setText("x = " + Double.toString((double)x) + ", y = " + Double.toString((double)y));
			}
		}
		if(e.getSource() == reset)
		{
			txta.setText("");
			txtb.setText("");
			txtc.setText("");
			txtd.setText("");
			txte.setText("");
			txtf.setText("");
			txtkq.setText("");
		}
		if(e.getSource() == thoat)
		{
			System.exit(0);
		}
	} 
	
	public Bai4(String st)
	{
		super(st);
		GUI();
	}
	
	public static void main(String[] args)
	{
		new Bai4("Giai he phuong trinh bac 1");
	}
}
