package StringUDP;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ClientStringUDP extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnSend;
	private static JTextArea textArea;
	
	static DatagramSocket clientSocket;
	static InetAddress IP;

	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientStringUDP frame = new ClientStringUDP();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		clientSocket = new DatagramSocket();
		IP = InetAddress.getByName("localhost");
		textArea = new JTextArea();
		textArea.setText("Kết nối thành công!\n");
	}
	
	public ClientStringUDP() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 515, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField.setBounds(51, 29, 390, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnSend = new JButton("Send");
		btnSend.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnSend.setBounds(207, 84, 85, 21);
		contentPane.add(btnSend);
		btnSend.addActionListener(this);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textArea.setBounds(10, 125, 481, 268);
		contentPane.add(textArea);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSend) {
			try {
				byte[] sendData = new byte[1024];
				byte[] receiveData = new byte[1024];
				sendData = textField.getText().getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IP, 1500);
				clientSocket.send(sendPacket);
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				clientSocket.receive(receivePacket);
				String st = new String(receivePacket.getData(), receivePacket.getOffset(), receivePacket.getLength());
				textArea.setText(st);
			} 
			catch (Exception e2) {
				System.out.print("Error");
			}
		}	
	}
}
