package ChatUDP;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ChatClientUDP extends JFrame implements ActionListener{
	
	class ReadClient extends Thread{
		private DatagramSocket client;

		public ReadClient(DatagramSocket client) {
			this.client = client;
		}
		
		public void run() {
			try {
				while(true) {
					String sms = recieveData(client);
					textArea.append(sms + "\n");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		private String recieveData(DatagramSocket client) throws IOException {
			byte[] temp = new byte[1024];
			DatagramPacket recieve_Packet = new DatagramPacket(temp, temp.length);
			client.receive(recieve_Packet);
			return new String(recieve_Packet.getData()).trim();
		}
	}

	class WriteClient extends Thread{
		private DatagramSocket client;
		private InetAddress host;
		private int port;
		private String name;
		
		public WriteClient(DatagramSocket client, InetAddress host, int port, String name) {
			this.client = client;
			this.host = host;
			this.port = port;
			this.name = name;
		}
		
		public void run() {
			Scanner sc = new Scanner(System.in);
			try {
				DatagramPacket DP = createPacket();
				client.send(DP);
			} catch (Exception e) {
				
			}
		}
	}
	
	private InetAddress host;
	private int port;
	String name;

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private JButton btnSend;
	private JButton btnName;
	private static DatagramSocket client;

	public static void main(String[] args) throws Exception {
		client = new DatagramSocket();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatClientUDP frame = new ChatClientUDP(InetAddress.getLocalHost(), 15797);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ChatClientUDP(InetAddress host, int port) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 495, 487);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 418, 295, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 461, 372);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		textArea.setText("Nhập vào tên của bạn: ");
		
		btnSend = new JButton("Send");
		btnSend.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnSend.setBounds(315, 417, 72, 21);
		contentPane.add(btnSend);
		btnSend.addActionListener(this);
		btnSend.setEnabled(false);
		
		JLabel lblNewLabel = new JLabel("CHAT CLIENT");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(182, 10, 122, 16);
		contentPane.add(lblNewLabel);
		
		btnName = new JButton("Name");
		btnName.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnName.setBounds(392, 418, 79, 21);
		contentPane.add(btnName);
		btnName.addActionListener(this);
		textField.addActionListener(this);
		
		this.host = host;
		this.port = port;
	}
	
	private void execute() throws IOException {
		client.send(createPacket(name + " đã tham gia vào phòng chat!\n"));
		ReadClient read = new ReadClient(client);
		read.start();	
	}
	
	private DatagramPacket createPacket(String value) {
		byte[] arrData = value.getBytes();
		return new DatagramPacket(arrData, arrData.length, host, port);
	}
	
	public DatagramPacket createPacket() {
		byte[] arrData = (name + ": " + textField.getText()).getBytes();
		textField.setText("");
		return new DatagramPacket(arrData, arrData.length, host, port);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnName) {
			name = textField.getText();
			textArea.append(name + "\n");
			textField.setText("");
			try {
				execute();
			} catch (IOException e1) {
				textArea.setText("Error");
			}
			btnName.setEnabled(false);
			btnSend.setEnabled(true);
		}
		if(e.getSource() == btnSend || e.getSource() == textField) {
			textArea.append("Tôi: " + textField.getText() + "\n");
			WriteClient write = new WriteClient(client, host, port, name);
			write.start();
		}
	}
}


