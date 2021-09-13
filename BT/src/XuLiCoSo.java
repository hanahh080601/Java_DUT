import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math;

public class XuLiCoSo extends JFrame implements ActionListener{
	JTextField txta, txtb, txtc, txtkq;
	JLabel lb, lba, lbb, lbc, lbkq;
	JButton exit, dt, reset, cv;
	JPanel pn, pn1, pn2, pn3, pn4, pn5;
	
	public void GUI()
	{
		txta = new JTextField();
		txtb = new JTextField();
		txtc = new JTextField();
		txtkq = new JTextField();
		txtkq.enable(false);
		
		exit = new JButton("Exit");
		exit.addActionListener(this);
		reset = new JButton("Reset");
		reset.addActionListener(this);
		dt = new JButton("Tinh dien tich");
		dt.addActionListener(this);
		cv = new JButton("Tinh chu vi");
		cv.addActionListener(this);
		
		lb = new JLabel("Tinh dien tich chu vi");
		lba = new JLabel("Do dai a: ");
		lbb = new JLabel("Do dai b: ");
		lbc = new JLabel("Do dai c: ");
		lbkq = new JLabel("Ket qua: ");
		
		pn1 = new JPanel(new FlowLayout());
		pn1.add(lb);
		
		pn2 = new JPanel(new GridLayout(3,2));
		pn2.add(lba);
		pn2.add(txta);
		pn2.add(lbb);
		pn2.add(txtb);
		pn2.add(lbc);
		pn2.add(txtc);
		
		pn3 = new JPanel();
		pn3.add(dt);
		pn3.add(cv);
		
		pn4 = new JPanel(new GridLayout(1,2));
		pn4.add(lbkq);
		pn4.add(txtkq);
		
		pn5 = new JPanel(new FlowLayout());
		pn5.add(reset);
		pn5.add(exit);
		
		pn = new JPanel();
		BoxLayout boxlayout = new BoxLayout(pn, BoxLayout.Y_AXIS);
		pn.setLayout(boxlayout);
		pn.add(pn1);
		pn.add(pn2);
		pn.add(pn3);
		pn.add(pn4);
		pn.add(pn5);
		
		add(pn);
		setSize(450,300);
		show();	
	}
	
	public static void main(String[] args) {
		new XuLiCoSo("Tinh dien tich chu vi");
	}
	
	public XuLiCoSo(String st) {
		super(st);
		GUI();
	}
	
	public static boolean isNumeric(String str) { 
		  try 
		  {  
			  Double.parseDouble(str);  
			  return true;
		  } 
		  catch(NumberFormatException e)
		  {  
			  return false;  
		  }  
		}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == dt)
		{
			double a, b, c;
			if(isNumeric(txta.getText()) && isNumeric(txtb.getText()) && isNumeric(txtc.getText()))
			{
				a = Double.parseDouble(txta.getText());
				b = Double.parseDouble(txtb.getText());
				c = Double.parseDouble(txtc.getText());
				if((a <= 0) || (b <= 0) || (c <= 0))
				{
					txtkq.setText("Do dai cac canh phai la so duong!");
				}
				else 
				{
					if((a + b > c) && (b + c > a) && (a + c > b))
					{
						double t = (a+b+c)*(a+b-c)*(b+c-a)*(c+a-b);
						double dtich = Math.sqrt(t)/4;
						txtkq.setText(Double.toString(dtich));
					}
					else txtkq.setText("a,b,c khong phai la canh cua tam giac!");
				}
			}
			else txtkq.setText("Chi duoc nhap so!");	
		}
		if(e.getSource() == cv)
		{
			double a, b, c;
			if(isNumeric(txta.getText()) && isNumeric(txta.getText()) && isNumeric(txta.getText()))
			{
				a = Double.parseDouble(txta.getText());
				b = Double.parseDouble(txtb.getText());
				c = Double.parseDouble(txtc.getText());
				if((a <= 0) || (b <= 0) || (c <= 0))
				{
					txtkq.setText("Do dai cac canh phai la so duong!");
				}
				else 
				{
					if((a + b > c) && (b + c > a) && (a + c > b))
					{
						double cv = a + b + c;
						txtkq.setText(Double.toString(cv));
					}	
					else txtkq.setText("a,b,c khong phai la canh cua tam giac!");
				}	
			}
			else txtkq.setText("Chi duoc nhap so!");	
		}
		if(e.getSource() == reset)
		{
			txta.setText("");
			txtb.setText("");
			txtc.setText("");
			txtkq.setText("");
		}
		if(e.getSource() == exit)
		{
			System.exit(0);
		}
	}

	
	
}
