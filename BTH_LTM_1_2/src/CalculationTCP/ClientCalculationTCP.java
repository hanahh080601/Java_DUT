package CalculationTCP;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class ClientCalculationTCP extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtPort;
	private JTextField textField;
	private static JTextArea textArea;
	private JButton btnConnect, btnClose, btnSend;

	static Socket socket;
	
	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientCalculationTCP frame = new ClientCalculationTCP();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ClientCalculationTCP() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nhap so hieu cong:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel.setBounds(112, 30, 110, 17);
		contentPane.add(lblNewLabel);
		
		txtPort = new JTextField();
		txtPort.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtPort.setBounds(232, 30, 96, 19);
		contentPane.add(txtPort);
		txtPort.setColumns(10);
		
		btnConnect = new JButton("Connect");
		btnConnect.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnConnect.setBounds(338, 28, 85, 21);
		contentPane.add(btnConnect);
		btnConnect.addActionListener(this);
		
		btnClose = new JButton("Close");
		btnClose.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnClose.setBounds(433, 28, 72, 21);
		contentPane.add(btnClose);
		btnClose.addActionListener(this);
		btnClose.setEnabled(false);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField.setBounds(112, 59, 311, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnSend = new JButton("Send");
		btnSend.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnSend.setBounds(433, 59, 72, 21);
		contentPane.add(btnSend);
		btnSend.setEnabled(false);
		btnSend.addActionListener(this);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 94, 637, 270);
		contentPane.add(textArea);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSend) {
			String input = textField.getText().toString();
			try {
				DataInputStream din = new DataInputStream(socket.getInputStream());
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				dos.writeUTF(input);
				dos.flush();
				String st = din.readUTF();
				textArea.setText(textArea.getText() + st);
				textField.setText("");
			} catch (Exception e1) {
				System.out.print("Error");
			}
		}	
		if(e.getSource() == btnConnect) {
			int port = Integer.parseInt(txtPort.getText());
			try {
				socket = new Socket("localhost", port);
				textArea.setText("Kết nối thành công!\n");
				textArea.append("Đã kết nối đến: " + Integer.toString(socket.getPort()) + socket.getInetAddress());
				
				btnSend.setEnabled(true);
				btnClose.setEnabled(true);
				btnConnect.setEnabled(false);
			}
			catch (Exception ex) {
				System.out.println("Error");
			}
		}
		if(e.getSource() == btnClose) {
			try {
				socket.close();
			} 
			catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
