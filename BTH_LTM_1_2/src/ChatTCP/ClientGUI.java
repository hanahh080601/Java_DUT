package ChatTCP;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	// Ban đầu là "Username:", sau đó là "Enter message"
	private JLabel label;
	
	// Ban đầu để nhập Username sau đó là nhập messages
	private JTextField tf;
	
	// Hiển thị server address và port number
	private JTextField tfServer, tfPort;
	
	// Đăng nhập, đăng xuất và xem danh sách người dùng
	private JButton login, logout, btnSend;
	
	// Khung hiển thị đoạn chat
	private JTextArea ta;
	
	// Kiểm tra đã kết nối chưa
	private boolean connected;
	
	private Client client;
	private int defaultPort;
	private String defaultHost;

	// Hàm dựng nhận số host, port
	ClientGUI(String host, int port) {

		super("Chat Client");
		defaultPort = port;
		defaultHost = host;
		
		// Phần trên
		JPanel northPanel = new JPanel(new GridLayout(3,1));
		JPanel serverAndPort = new JPanel(new GridLayout(1,5, 1, 3));
		// Hiển thị số host và port mặc định
		tfServer = new JTextField(host);
		tfPort = new JTextField("" + port);
		tfPort.setHorizontalAlignment(SwingConstants.RIGHT);

		serverAndPort.add(new JLabel("Server Address:  "));
		serverAndPort.add(tfServer);
		serverAndPort.add(new JLabel("Port Number:  "));
		serverAndPort.add(tfPort);
		serverAndPort.add(new JLabel(""));
		northPanel.add(serverAndPort);

		// Thêm Label và textField để nhập username
		label = new JLabel("Enter your username below", SwingConstants.CENTER);
		northPanel.add(label);
		tf = new JTextField("Anonymous");
		tf.setBackground(Color.WHITE);
		northPanel.add(tf);
		getContentPane().add(northPanel, BorderLayout.NORTH);

		// CenterPanel là khung hiển thị đoạn chat
		ta = new JTextArea("Welcome to the Chat room\n", 80, 80);
		JPanel centerPanel = new JPanel(new GridLayout(1,1));
		centerPanel.add(new JScrollPane(ta));
		ta.setEditable(false);
		getContentPane().add(centerPanel, BorderLayout.CENTER);

		// 3 nút bấm
		login = new JButton("Connect");
		login.addActionListener(this);
		logout = new JButton("Disconnect");
		logout.addActionListener(this);
		logout.setEnabled(false);

		JPanel southPanel = new JPanel();
		
		btnSend = new JButton("Send");
		btnSend.setEnabled(false);
		southPanel.add(btnSend);
		southPanel.add(login);
		southPanel.add(logout);
		getContentPane().add(southPanel, BorderLayout.SOUTH);
		btnSend.addActionListener(this);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 600);
		setVisible(true);
		tf.requestFocus();

	}

	// Được gọi bởi Client để nối chuỗi vào textArea
	void append(String str) {
		ta.append(str);
		ta.setCaretPosition(ta.getText().length() - 1);
	}
	// Được gọi bởi GUI nếu kết nối fail thì reset các Button, Label, textField
	void connectionFailed() {
		login.setEnabled(true);
		logout.setEnabled(false);
		btnSend.setEnabled(false);
		label.setText("Enter your username below");
		tf.setText("Anonymous");
		// reset port number và host name như lúc khởi tạo
		tfPort.setText("" + defaultPort);
		tfServer.setText(defaultHost);
		// không cho thay đổi 
		tfServer.setEditable(false);
		tfPort.setEditable(false);
		tf.removeActionListener(this);
		connected = false;
	}
	
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		// Logout button
		if(o == logout) {
			client.sendMessage(new ChatMessage(ChatMessage.LOGOUT, ""));
			return;
		}

		// Tin nhắn bình thường
		if(connected && o == btnSend) {
			client.sendMessage(new ChatMessage(ChatMessage.MESSAGE, tf.getText()));				
			tf.setText("");
			return;
		}
		

		if(o == login) {
			// Đây là yêu cầu kết nối
			String username = tf.getText().trim();
			if(username.length() == 0)
				return;
			String server = tfServer.getText().trim();
			if(server.length() == 0)
				return;
			String portNumber = tfPort.getText().trim();
			if(portNumber.length() == 0)
				return;
			int port = 0;
			try {
				port = Integer.parseInt(portNumber);
			}
			catch(Exception en) {
				return;  
			}

			// Tạo Client với GUI
			client = new Client(server, port, username, this);
			// test if we can start the Client
			if(!client.start()) 
				return;
			tf.setText("");
			label.setText("Enter your message below");
			connected = true;
			
			// disable nút login
			login.setEnabled(false);
			// enable 2 nút logout và whoIsIn
			logout.setEnabled(true);
			btnSend.setEnabled(true);
			// Không cho thay đổi Server và Port JTextField
			tfServer.setEditable(false);
			tfPort.setEditable(false);
			// Lắng nghe sự kiện khi nhấn enter gửi tin nhắn trong textField
			tf.addActionListener(this);
		}

	}

	public static void main(String[] args) {
		new ClientGUI("localhost", 1500);
	}

}
