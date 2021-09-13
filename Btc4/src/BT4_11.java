import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.lang.Math;

public class BT4_11 extends JFrame implements ActionListener{
	double a = 0, b = 0;
	double temp = 0, result = 0;
	int operation = 0;
	boolean isEqual = false, isPercent = false, isSqrt = false, isMs = false;
	JButton btn;
	TextField txt, t;
	JPanel pn, pn1, pn2, pn3, pn4, pn5, pn6;
	String btnNumCal[] = {"7", "8", "9", "/", "sqrt", "4", "5", "6", "*", "%", "1", "2", "3", "-", "1/x", "0", "+/-", ".", "+", "="};
	String btnM[] = {"MC", "MR", "MS", "M+"};
	String btnDel[] = {"Backspace", "CE", "C"};
	
	public JButton initBtn(String btnname) {
		btn = new JButton(btnname);
	    btn.addActionListener(this);
	    return btn;
	}
	
	public void GUI()
	{
		txt = new TextField();
		t = new TextField();
		t.enable(false);
		
		pn1 = new JPanel(new GridLayout(1, 1));
		pn1.add(txt);
		
		pn2 = new JPanel(new GridLayout(5,1));
		pn2.add(t);
		for (int i = 0; i < 4; i++) {
			pn2.add(initBtn(btnM[i]));
		}
		pn2.setBorder(new EmptyBorder(new Insets(0, 0, 0, 10)));
		
		pn4 = new JPanel(new GridLayout(1,3));
		for(int i = 0; i < 3; i++) {
			pn4.add(initBtn(btnDel[i]));
		}
		
		pn5 = new JPanel(new GridLayout(4,5));
		for(int i = 0; i < 20; i++) {
			pn5.add(initBtn(btnNumCal[i]));
		}
		
		pn3 = new JPanel();
		BoxLayout boxlayout = new BoxLayout(pn3, BoxLayout.Y_AXIS);
		pn3.setLayout(boxlayout);
		pn3.add(pn4);
		pn3.add(pn5);
		
		pn6 = new JPanel();
		BoxLayout boxlayout1 = new BoxLayout(pn6, BoxLayout.X_AXIS);
		pn6.setLayout(boxlayout1);
		pn6.add(pn2);
		pn6.add(pn3);
		
		pn = new JPanel();
		BoxLayout boxlayout2 = new BoxLayout(pn, BoxLayout.Y_AXIS);
		pn.setLayout(boxlayout2);
		pn.add(pn1);
		pn.add(pn6);
		add(pn);
		setSize(400, 300);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "0":
				if(isEqual) {
					txt.setText("");
					isEqual = false;
				}
				txt.setText(txt.getText() + "0");
				break;
			case "1":
				if(isEqual) {
					txt.setText("");
					isEqual = false;
				}
				txt.setText(txt.getText() + "1");
				break;
			case "2":
				if(isEqual) {
					txt.setText("");
					isEqual = false;
				}
				txt.setText(txt.getText() + "2");
				break;
			case "3":
				if(isEqual) {
					txt.setText("");
					isEqual = false;
				}
				txt.setText(txt.getText() + "3");
				break;
			case "4":
				if(isEqual) {
					txt.setText("");
					isEqual = false;
				}
				txt.setText(txt.getText() + "4");
				break;
			case "5":
				if(isEqual) {
					txt.setText("");
					isEqual = false;
				}
				txt.setText(txt.getText() + "5");
				break;
			case "6":
				if(isEqual) {
					txt.setText("");
					isEqual = false;
				}
				txt.setText(txt.getText() + "6");
				break;
			case "7":
				if(isEqual) {
					txt.setText("");
					isEqual = false;
				}
				txt.setText(txt.getText() + "7");
				break;
			case "8":
				if(isEqual) {
					txt.setText("");
					isEqual = false;
				}
				txt.setText(txt.getText() + "8");
				break;
			case "9":
				if(isEqual) {
					txt.setText("");
					isEqual = false;
				}
				txt.setText(txt.getText() + "9");
				break;
				
			case "+":
				
				if (isPercent) {
					a = temp;
					isPercent = false;
				}
				else a = Double.parseDouble(txt.getText());
				txt.setText("");
				operation = 1;
				break;
			case "-":
				if (isPercent) {
					a = temp;
					isPercent = false;
				}
				else a = Double.parseDouble(txt.getText());
				txt.setText("");
				operation = 2;
				break;
			case "*":
				if (isPercent) {
					a = temp;
					isPercent = false;
				}
				else a = Double.parseDouble(txt.getText());
				txt.setText("");
				operation = 3;
				break;
			case "/":
				if (isPercent) {
					a = temp;
					isPercent = false;
				}
				else a = Double.parseDouble(txt.getText());
				txt.setText("");
				operation = 4;
				break;
			case "1/x":
				if (isPercent) {
					a = temp;
					isPercent = false;
				}
				try {
					txt.setText(Double.toString(1 / Double.parseDouble(txt.getText())));
				}
				catch(Exception ex) {
				}
				break;
			case "+/-":
				txt.setText(Double.toString(Double.parseDouble(txt.getText())*(-1)));
				break;
			case ".":
				txt.setText(txt.getText() + ".");
				break;
			case "sqrt":
				isSqrt = true;
				try {
					temp = Math.sqrt(Double.parseDouble(txt.getText()));
					txt.setText("sqrt(" + txt.getText() + ")");
				}
				catch(Exception ex) {
				}
				break;
			case "%":
				isPercent = true;
				temp = Double.parseDouble(txt.getText()) / 100;
				txt.setText(txt.getText() + "%");
				break;
			case "Backspace":
				String s = txt.getText().substring(0, txt.getText().length()-1);
				txt.setText(s); 
				break;
			case "CE":
				txt.setText("");
				break;
			case "C":
				a = 0;
				b = 0;
				temp = 0;
				result = 0;
				txt.setText("");
				break;
			case "M+":
				if (txt.getText() != "") {
					t.setText("M+");
					result += Double.parseDouble(txt.getText());
				}
				break;
			case "MS":
				t.setText("MS");
				if (!isMs) {
					result = Double.parseDouble(txt.getText());
					isMs = true;
				}
				else if (txt.getText() != "") {
					result -= Double.parseDouble(txt.getText());
				}
				break;
			case "MR":
				txt.setText(Double.toString(result));
				t.setText("");
				result = 0;
				break;
			case "MC":
				txt.setText(Double.toString(result));
				t.setText("");
				result = 0;
				break;
			case "=":
				isEqual = true;
				if (isPercent) {
					b = temp;
				}
				else if(isSqrt) {
					a = temp;
					txt.setText(Double.toString(a));
					isSqrt = false;
				}
				else {
					b = Double.parseDouble(txt.getText());
				}
				switch(operation) {
					case 1:
						txt.setText(Double.toString(a+b));
						operation = 0;
						break;
					case 2:
						txt.setText(Double.toString(a-b));
						operation = 0;
						break;
					case 3:	
						txt.setText(Double.toString(a*b));
						operation = 0;
						break;
					case 4:	
						txt.setText(Double.toString(a/b));
						operation = 0;
						break;
					default:
						if (isPercent) {
							a = temp;
						}
						else a = Double.parseDouble(txt.getText());
						txt.setText(Double.toString(a));
				}
				break;
		}
	}
	
	public BT4_11(String st)
	{
		super(st);
		GUI();
	}
	
	public static void main(String[] args)
	{
		new BT4_11("Calculator");
	}
}