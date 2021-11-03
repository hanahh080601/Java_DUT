package StringTCP;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ClientStringTCP extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnSend;
	private static JTextArea textArea;
	
	static Socket socket;

	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientStringTCP frame = new ClientStringTCP();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		socket = new Socket("localhost", 1000);
		textArea = new JTextArea();
		textArea.setText("Kết nối thành công!\n");
	}
	
	public ClientStringTCP() {
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
		
		textArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textArea.setBounds(51, 135, 390, 235);
		contentPane.add(textArea);
	}

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
			} catch (Exception e1) {
				System.out.print("Error");
			}
		}	
	}
}
