import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JButton;

public class Bai2_Detail1 extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtCode;
	private JTextField txtName;
	private JTextField txtCredit;
	private JButton btnAdd, btnClear;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bai2_Detail1 frame = new Bai2_Detail1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Bai2_Detail1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 316);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Code");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel.setBounds(60, 47, 67, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblName.setBounds(60, 92, 67, 14);
		contentPane.add(lblName);
		
		JLabel lblCredit = new JLabel("Credit");
		lblCredit.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblCredit.setBounds(60, 141, 67, 14);
		contentPane.add(lblCredit);
		
		txtCode = new JTextField();
		txtCode.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtCode.setBounds(113, 46, 96, 19);
		contentPane.add(txtCode);
		txtCode.setColumns(10);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtName.setColumns(10);
		txtName.setBounds(113, 91, 248, 19);
		contentPane.add(txtName);
		
		txtCredit = new JTextField();
		txtCredit.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtCredit.setColumns(10);
		txtCredit.setBounds(113, 140, 96, 19);
		contentPane.add(txtCredit);
		
		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnAdd.setBounds(62, 199, 85, 29);
		contentPane.add(btnAdd);
		btnAdd.addActionListener(this);
		
		btnClear = new JButton("Clear");
		btnClear.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnClear.setBounds(254, 199, 85, 29);
		contentPane.add(btnClear);
		btnClear.addActionListener(this);
	}
	
	public static Connection con;
	
	public static void Connect()
	{
		System.out.println("Ket noi CSDL");
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-1QK42IB\\SQLEXPRESS:1433; DatabaseName=TestJava",
					"sa", "123456");
			System.out.println("Connected!");
		}
		catch(Exception e)
		{
			System.out.println("Error!");
		}
	}
	
	public static void InsertToDB(String code, String name, int credit) {
		try {
			Statement stmt = con.createStatement();
			String query = "insert into Course values ('";
			query += code + "', '" + name + "', " + Integer.toString(credit) + ")";
			System.out.print(query);
			stmt.executeUpdate(query);
			stmt.close();
			System.out.println("Insert into database successfully.");
		}
		catch(Exception e) {
			System.out.println("Error: " + e);
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAdd)
		{
			String code = txtCode.getText();
			String name = txtName.getText();
			int credit = Integer.parseInt(txtCredit.getText());
			Connect();
			InsertToDB(code, name, credit);
			
		}
		if(e.getSource() == btnClear)
		{
			txtCode.setText("");
			txtName.setText("");
			txtCredit.setText("");
		}
		
	}
}
