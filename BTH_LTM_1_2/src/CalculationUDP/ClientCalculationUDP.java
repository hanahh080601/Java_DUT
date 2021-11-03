package CalculationUDP;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class ClientCalculationUDP extends JFrame implements ActionListener {
	
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
					ClientCalculationUDP frame = new ClientCalculationUDP();
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

	public ClientCalculationUDP() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 473, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField.setBounds(10, 40, 439, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("CALCULATION CLIENT");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(147, 17, 165, 13);
		contentPane.add(lblNewLabel);
		
		btnSend = new JButton("Send");
		btnSend.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnSend.setBounds(194, 69, 69, 25);
		contentPane.add(btnSend);
		btnSend.addActionListener(this);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 104, 439, 290);
		contentPane.add(textArea);
	}

	@Override
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
				textArea.append("Biểu thức: " + textField.getText());
				textArea.append(st);
				textField.setText("");
			} 
			catch (Exception e2) {
				System.out.print("Error");
			}
		}	
	}
}
