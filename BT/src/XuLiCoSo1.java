import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;

public class XuLiCoSo1 extends JFrame implements ActionListener{
	TextField txta, txtb, txtc, txtd;
	Label lb, lba, lbb, lbc, lbd;
	JButton exit, ok, reset;
	JPanel pn, pn1, pn2, pn3, pn4, pn5;
	
	public void GUI()
	{
		txta = new TextField();
		txtb = new TextField();
		txtc = new TextField();
		txtd = new TextField();
		txtb.enable(false);
		txtc.enable(false);
		txtd.enable(false);
		
		exit = new JButton("Exit");
		exit.addActionListener(this);
		reset = new JButton("Reset");
		reset.addActionListener(this);
		ok = new JButton("OK");
		ok.addActionListener(this);
		
		lb = new Label("Xu li co so");
		lba = new Label("Nhap so nguyen duong: ");
		lbb = new Label("Bieu dien nhi phan: ");
		lbc = new Label("Bieu dien bat phan: ");
		lbd = new Label("Bieu dien thap luc phan: ");
		
		pn1 = new JPanel(new FlowLayout());
		pn1.add(lb);
		
		pn2 = new JPanel(new GridLayout(4,2));
		pn2.add(lba);
		pn2.add(txta);
		pn2.add(lbb);
		pn2.add(txtb);
		pn2.add(lbc);
		pn2.add(txtc);
		pn2.add(lbd);
		pn2.add(txtd);
		
		pn3 = new JPanel(new GridLayout(1,3));
		pn3.add(ok);
		pn3.add(reset);
		pn3.add(exit);
		
		pn = new JPanel();
		BoxLayout boxlayout = new BoxLayout(pn, BoxLayout.Y_AXIS);
		pn.setLayout(boxlayout);
		pn.add(pn1);
		pn.add(pn2);
		pn.add(pn3);
		add(pn);
		setSize(400,300);
		show();	
	}
	
	public static String convert(int num, int choice){
		String st = "";
        int arr[] = new int[40];
        int index = 0;
        while(num > 0){
            arr[index++] = num%choice;
            num = num/choice;
        }
        for(int i = index-1;i >= 0;i--){
        	switch(arr[i])
        	{
        		case 10: 
        			st += "A";
        			break;
        		case 11:
        			st += "B";
        			break;
        		case 12:
        			st += "C";
        			break;
        		case 13:
        			st += "D";
        			break;
        		case 14:
        			st += "E";
        			break;
        		case 15:
        			st += "F";
        			break;
        		default:
        			st += Integer.toString(arr[i]);
        			break;
        	}     
        }
        return st;
    }
	
	public static void main(String[] args) {
		new XuLiCoSo1("Xu li co so: Chuyen doi so");
	}
	
	public XuLiCoSo1(String st) {
		super(st);
		GUI();
	}
	
	public int checkND(String s) throws Exception {
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(c < '0' || c > '9')
			{
				throw new Exception("Vui long nhap so nguyen duong!");
			}
		}
		if (Integer.parseInt(s) < 0) {
			throw new Exception("Vui long nhap so nguyen duong!");
		}
		return Integer.parseInt(s);
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int a = -1;
		if(e.getSource() == ok)
		{
			try
			{
				a = checkND(txta.getText());
			}
			catch(Exception s) 
			{
				txtb.setText(s.getMessage());
				txtc.setText(s.getMessage());
				txtd.setText(s.getMessage());
			}
			txtb.setText(convert(Integer.parseInt(txta.getText()),2));
			txtc.setText(convert(Integer.parseInt(txta.getText()),8));
			txtd.setText(convert(Integer.parseInt(txta.getText()),16));
		}
		if(e.getSource() == reset)
		{
			txta.setText("");
			txtb.setText("");
			txtc.setText("");
			txtd.setText("");
		}
		if(e.getSource() == exit)
		{
			System.exit(0);
		}
	}
}
