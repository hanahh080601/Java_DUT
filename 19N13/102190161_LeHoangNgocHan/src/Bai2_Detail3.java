import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Printable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;

public class Bai2_Detail3 extends JFrame{

	private static JPanel contentPane;
	private static JTextArea textArea;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bai2_Detail3 frame = new Bai2_Detail3();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public static Connection con;
	public static List<String> list = new ArrayList<String>();
	
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
	
	public static void ListAll()
	{
		try {
			
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM Course order by Credit";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				String code = rs.getString("Code");
				String name = rs.getString("Name");
				String credit = rs.getString("Credit");
				list.add(code + " | " + name + " | "+ credit + "\n");
				//System.out.println(code + " | " + name + " | " + credit);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println("Error!");
		}
	}
	
	public Bai2_Detail3() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("List of all courses (order by Credit)");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(80, 27, 294, 13);
		contentPane.add(lblNewLabel);
		
		textArea = new JTextArea();
		textArea.setBounds(38, 65, 359, 161);
		contentPane.add(textArea);

		Display();
	}

	public static void Display() {
		Connect();
		ListAll();
		String out = "";
		for(int i = 0; i < list.size(); i++)
		{
			out += list.get(i);
		}
		textArea.setText(out);
	}
	
}
