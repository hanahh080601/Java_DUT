import java.awt.*;
import java.awt.event.*;
class BT4_8 extends Frame implements ActionListener{
	Label lb, lb1, lb2, lb3;
	TextField txta, txtb, txtkq;
	Button cong, tru, nhan, chia, exit, reset;
	Panel pn, pn1, pn2, pn3, pn4;
	
	public void GUI()
	{
		lb = new Label("MINH HOA CAC PHEP TOAN");
		lb1 = new Label("Nhap a: ");
		lb2 = new Label("Nhap b: ");
		lb3 = new Label("Ket qua: ");
		
		txta = new TextField();
		txtb = new TextField();
		txtkq = new TextField();
		txtkq.enable(false);
		
		cong = new Button("Cong");
		tru = new Button("Tru");
		nhan = new Button("Nhan");
		chia = new Button("Chia");
		reset = new Button("Reset");
		exit = new Button("Exit");
		
		cong.addActionListener(this);
		tru.addActionListener(this);
		nhan.addActionListener(this);
		chia.addActionListener(this);
		reset.addActionListener(this);
		exit.addActionListener(this);
		
		pn = new Panel( new GridLayout(4,1));
		pn1 = new Panel( new FlowLayout());
		pn2 = new Panel( new GridLayout(3,2));
		pn3 = new Panel( new FlowLayout());
		pn4 = new Panel (new FlowLayout());
		
		pn1.add(lb);
		
		pn2.add(lb1);
		pn2.add(txta);
		pn2.add(lb2);
		pn2.add(txtb);
		pn2.add(lb3);
		pn2.add(txtkq);
		
		pn3.add(cong);
		pn3.add(tru);
		pn3.add(nhan);
		pn3.add(chia);
		
		pn4.add(exit);
		pn4.add(reset);
		
		pn.add(pn1);
		pn.add(pn2);
		pn.add(pn3);
		pn.add(pn4);
		
		add(pn); //add vào frame
		setSize(400,300); // thiết lập kích thước
		show(); // hiển thị
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == cong)
		{
			double a = Double.parseDouble(txta.getText());
			double b = Double.parseDouble(txtb.getText());
			txtkq.setText(Double.toString((double)a+b));
		}
		if(e.getSource() == tru)
		{
			double a = Double.parseDouble(txta.getText());
			double b = Double.parseDouble(txtb.getText());
			txtkq.setText(Double.toString((double)a-b));
		}
		if(e.getSource() == nhan)
		{
			double a = Double.parseDouble(txta.getText());
			double b = Double.parseDouble(txtb.getText());
			txtkq.setText(Double.toString((double)a*b));
		}
		if(e.getSource() == chia)
		{
			double a = Double.parseDouble(txta.getText());
			double b = Double.parseDouble(txtb.getText());
			if(b != 0)
			{
				txtkq.setText(Double.toString((double)a/b));
			}
			else txtkq.setText("Khong thuc hien phep chia voi 0!");
			
		}
		if(e.getSource() == reset)
		{
			txta.setText("");
			txtb.setText("");
			txtkq.setText("");
		}
		if(e.getSource() == exit)
		{
			System.exit(0);
		}
	} 
	
	public BT4_8(String st)
	{
		super(st);
		GUI();
	}
	
	public static void main(String[] args) 
	{
		new BT4_8("Minh hoa cac phep toan");
	}
}
