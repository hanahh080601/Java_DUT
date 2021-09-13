import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.lang.Math;


public class Bai1 extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txta;
	private JTextField txtb;
	private JTextField txtc;
	private JTextField txtResult;
	private JButton btnArea;
	private JButton btnBound;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bai1 frame = new Bai1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Bai1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 553, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbla = new JLabel("\u0110\u00F4\u0323 da\u0300i ca\u0323nh a:");
		lbla.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lbla.setBounds(63, 61, 121, 24);
		contentPane.add(lbla);
		
		JLabel lblb = new JLabel("\u0110\u00F4\u0323 da\u0300i ca\u0323nh b:");
		lblb.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblb.setBounds(63, 119, 121, 24);
		contentPane.add(lblb);
		
		JLabel lblc = new JLabel("\u0110\u00F4\u0323 da\u0300i ca\u0323nh c:");
		lblc.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblc.setBounds(63, 176, 121, 24);
		contentPane.add(lblc);
		
		txta = new JTextField();
		txta.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txta.setBounds(194, 64, 227, 24);
		contentPane.add(txta);
		txta.setColumns(10);
		
		txtb = new JTextField();
		txtb.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtb.setColumns(10);
		txtb.setBounds(194, 122, 227, 24);
		contentPane.add(txtb);
		
		txtc = new JTextField();
		txtc.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtc.setColumns(10);
		txtc.setBounds(194, 179, 227, 24);
		contentPane.add(txtc);
		
		btnArea = new JButton("Ti\u0301nh di\u00EA\u0323n ti\u0301ch");
		btnArea.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnArea.setBounds(194, 230, 227, 24);
		contentPane.add(btnArea);
		btnArea.addActionListener(this);
		
		btnBound = new JButton("Ti\u0301nh chu vi");
		btnBound.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnBound.setBounds(194, 281, 227, 24);
		contentPane.add(btnBound);
		btnBound.addActionListener(this);
		
		JLabel lblresult = new JLabel("K\u00EA\u0301t qua\u0309:");
		lblresult.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblresult.setBounds(111, 333, 73, 24);
		contentPane.add(lblresult);
		
		txtResult = new JTextField();
		txtResult.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtResult.setColumns(10);
		txtResult.setBounds(194, 336, 227, 24);
		contentPane.add(txtResult);
	}
	
	public static boolean isNumeric(String str) 
	{ 
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnArea)
		{
			double a, b, c;
			if(isNumeric(txta.getText()) && isNumeric(txtb.getText()) && isNumeric(txtc.getText()))
			{
				a = Double.parseDouble(txta.getText());
				b = Double.parseDouble(txtb.getText());
				c = Double.parseDouble(txtc.getText());
				if((a > 0) && (b > 0) && (c > 0))
				{
					if((a + b > c) && (b + c > a) && (a + c > b))
					{
						double p = (a+b+c)*(a+b-c)*(b+c-a)*(a+c-b);
						double dt = Math.sqrt(p)/4;
						txtResult.setText(Double.toString(dt));
					}
					else 
					{
						txtResult.setText("a,b,c không phải là cạnh của tam giác!");
					}
				}
				else 
				{
					txtResult.setText("Độ dài các cạnh phải là số dương!");
				}
			}
			else 
			{
				txtResult.setText("Độ dài các cạnh phải là số!");
			}
		}
		if(e.getSource() == btnBound)
		{
			double a, b, c;
			if(isNumeric(txta.getText()) && isNumeric(txtb.getText()) && isNumeric(txtc.getText()))
			{
				a = Double.parseDouble(txta.getText());
				b = Double.parseDouble(txtb.getText());
				c = Double.parseDouble(txtc.getText());
				if((a > 0) && (b > 0) && (c > 0))
				{
					if((a + b > c) && (b + c > a) && (a + c > b))
					{
						double cv = a + b + c; 
						txtResult.setText(Double.toString(cv));
					}
					else 
					{
						txtResult.setText("a,b,c không phải là cạnh của tam giác!");
					}
				}
				else 
				{
					txtResult.setText("Độ dài các cạnh phải là số dương!");
				}
			}
			else 
			{
				txtResult.setText("Độ dài các cạnh phải là số!");
			}
		}	
	}
}
