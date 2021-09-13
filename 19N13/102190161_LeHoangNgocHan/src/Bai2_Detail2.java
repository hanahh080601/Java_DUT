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
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JButton;

public class Bai2_Detail2 extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtCode;
	private JTextField txtName;
	private JTextField txtCredit;
	private JButton btnSearch;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bai2_Detail2 frame = new Bai2_Detail2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
	
	private static String code;
	private static String name;
	private static String credit;
	
	public static void Search(int choice, String input)
	{
		Connect();
		if(choice == 0)
		{
			code = null;
			name = null;
			credit = null;
			try {
				Statement stmt = con.createStatement();
				String sql = "SELECT * FROM Course where Code = '" + input + "'";
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					code = rs.getString("Code");
					name = rs.getString("Name");
					credit = rs.getString("Credit");
				}
				rs.close();
				stmt.close();
			} catch (Exception e) {
				System.out.println("Error!");
			}
		}
		
		if(choice == 1)
		{
			code = null;
			name = null;
			credit = null;
			try {
				Statement stmt = con.createStatement();
				String sql = "SELECT * FROM Course where Name = '" + input + "'";
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					code = rs.getString("Code");
					name = rs.getString("Name");
					credit = rs.getString("Credit");
				}
				rs.close();
				stmt.close();
			} catch (Exception e) {
				System.out.println("Error!");
			}
		}
		
		if(choice == 2)
		{
			code = null;
			name = null;
			credit = null;
			try {
				Statement stmt = con.createStatement();
				String sql = "SELECT * FROM Course where Credit = " + input;
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					code = rs.getString("Code");
					name = rs.getString("Name");
					credit = rs.getString("Credit");
				}
				rs.close();
				stmt.close();
			} catch (Exception e) {
				System.out.println("Error!");
			}
		}
		
	}
	
	
	
	public Bai2_Detail2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 293);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBounds(10, 23, 461, 279);
		contentPane.add(contentPane_1);
		
		JLabel lblEnterCode = new JLabel("Enter code");
		lblEnterCode.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblEnterCode.setBounds(18, 47, 85, 14);
		contentPane_1.add(lblEnterCode);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblName.setBounds(46, 94, 67, 14);
		contentPane_1.add(lblName);
		
		JLabel lblCredit = new JLabel("Credit");
		lblCredit.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblCredit.setBounds(46, 143, 67, 14);
		contentPane_1.add(lblCredit);
		
		txtCode = new JTextField();
		txtCode.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtCode.setColumns(10);
		txtCode.setBounds(113, 44, 137, 19);
		contentPane_1.add(txtCode);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtName.setColumns(10);
		txtName.setBounds(113, 91, 248, 19);
		contentPane_1.add(txtName);
		
		txtCredit = new JTextField();
		txtCredit.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtCredit.setColumns(10);
		txtCredit.setBounds(113, 140, 137, 19);
		contentPane_1.add(txtCredit);
		
		btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnSearch.setBounds(276, 40, 85, 29);
		contentPane_1.add(btnSearch);
		btnSearch.addActionListener(this);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSearch)
		{
			if(txtCode.getText() != "")
			{
				Search(0, txtCode.getText());
				txtName.setText(name);
				txtCredit.setText(credit);
			}
			if(txtName.getText() != "")
			{
				Search(1, txtName.getText());
				txtCode.setText(code);
				txtCredit.setText(credit);
			}
			if(txtCredit.getText() != "")
			{
				Search(2, txtCredit.getText());
				txtCode.setText(code);
				txtName.setText(name);
			}
		}
		
	}
}
