import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class Final_2021 extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtInput;
	private JTextField txtKeyword;
	private JButton btnImport, btnTT, btnSL, btnHint;
	private JTextArea textArea;
	
	private static String url;
	private static Connection con;
	private static File f = new File("input.txt");
	private static List<String> data = new ArrayList<>();
	private static int count = 0;
	
	public static void ConnectDB() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			url = "jdbc:sqlserver://DESKTOP-1QK42IB\\\\SQLEXPRESS:1433; DatabaseName=Final_2021";
			con = DriverManager.getConnection(url, "sa", "123456");
			System.out.println("Ket noi csdl thanh cong.");
		}
		catch(Exception ex) {
			System.out.print("Connect fail: " + ex);
		}
	}
	
	public static void ReadFile(File f) {
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				data.add(line);
			}
			fr.close();
		    br.close();
			System.out.println("Doc file thanh cong.");
		} 
		catch (Exception ex) {
			System.out.print("Loi doc file: " + ex);
		}
	}
	
	public static String PrintTextArea()
	{
		String out = "";
		for(String i: data)
		{
			out += i + "\n";
		}
		return out;
	}
	
	public static void InsertToDB() {
		try {
			Statement stmt = con.createStatement();
			for (int i = 0; i < data.size(); i++) {
				String query = "insert into DonHang(TenMatHang, SoTien, TenNguoiMua) values ('";
				String[] info = data.get(i).trim().split(",");
				query += info[0] + "', " + info[1] + ", '" + info[2]+ "')";
				System.out.print(query);
				stmt.executeUpdate(query);
			}
			stmt.close();
			System.out.println("Insert into database successfully.");
		}
		catch(Exception e) {
			System.out.println("Error: " + e);
		}
	}
	
	public static int QueryDB(int choice, String input) {
		if(choice == 0)
		{
			String query = "Select COUNT(id) as COUNT from DonHang where TenMatHang = '" + input + "'";
			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next())
				{
					count = rs.getInt("COUNT");
				}
				rs.close();
				stmt.close();
			}
			catch(Exception e)
			{
				System.out.println("Error!");
			}
		}
		if(choice == 1)
		{
			String query = "Select SUM(SoTien) as SUM from DonHang where TenNguoiMua = '" + input + "'";
			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next())
				{
					count = rs.getInt("SUM");
				}
				rs.close();
				stmt.close();
			}
			catch(Exception e)
			{
				System.out.println("Error!");
			}
		}
		return count;
	}
	
	public static String QueryHintDB(String input)
	{
		String list = "";
		String query = "select TenMatHang, SUM(SoTien) as TT from DonHang where TenNguoiMua "
				+ "in (select TenNguoiMua from DonHang where TenMatHang = '" + input + "') "
				+ "group by TenMatHang having TenMatHang <> '" + input +"' order by TT desc";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				String tmh = rs.getString("TenMatHang");
				int tt = rs.getInt("TT");
				list += (tmh + ", " + Integer.toString(tt) + "\n");
			}
			rs.close();
			stmt.close();
		}
		catch(Exception e)
		{
			System.out.println("Error!");
		}
		return list;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Final_2021 frame = new Final_2021();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Final_2021() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Import Data");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel.setBounds(33, 45, 88, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblKeyword = new JLabel("Keyword");
		lblKeyword.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblKeyword.setBounds(33, 87, 88, 20);
		contentPane.add(lblKeyword);
		
		txtInput = new JTextField();
		txtInput.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtInput.setBounds(131, 45, 160, 21);
		contentPane.add(txtInput);
		txtInput.setColumns(10);
		
		txtKeyword = new JTextField();
		txtKeyword.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtKeyword.setColumns(10);
		txtKeyword.setBounds(131, 87, 273, 20);
		contentPane.add(txtKeyword);
		
		btnImport = new JButton("Import File");
		btnImport.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnImport.setBounds(301, 45, 103, 21);
		contentPane.add(btnImport);
		btnImport.addActionListener(this);
		
		btnSL = new JButton("So Luong");
		btnSL.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnSL.setBounds(33, 117, 119, 29);
		contentPane.add(btnSL);
		btnSL.addActionListener(this);
		
		btnTT = new JButton("Tong tien");
		btnTT.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnTT.setBounds(162, 118, 119, 28);
		contentPane.add(btnTT);
		btnTT.addActionListener(this);
		
		btnHint = new JButton("Goi Y");
		btnHint.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnHint.setBounds(291, 118, 113, 28);
		contentPane.add(btnHint);
		btnHint.addActionListener(this);
		
		textArea = new JTextArea();
		textArea.setBounds(33, 156, 372, 154);
		contentPane.add(textArea);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnImport)
		{
			if(txtInput.getText().toString().equals("input.txt")) {
				ConnectDB();
				ReadFile(f);
				InsertToDB();
				textArea.setText(PrintTextArea());
			}
			else textArea.setText("Invalid filename");
		}
		
		if(e.getSource() == btnSL)
		{
			String input = txtKeyword.getText().trim();
			ConnectDB();
			int result = QueryDB(0, input);
			textArea.setText(Integer.toString(result));
		}
		if(e.getSource() == btnTT)
		{
			String input = txtKeyword.getText().trim();
			ConnectDB();
			int result = QueryDB(1, input);
			textArea.setText(Integer.toString(result));
		}
		if(e.getSource() == btnHint)
		{
			String input = txtKeyword.getText().trim();
			ConnectDB();
			textArea.setText(QueryHintDB(input));
		}
	}
}
