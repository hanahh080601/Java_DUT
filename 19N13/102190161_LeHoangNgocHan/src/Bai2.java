import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI.Form2;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Bai2 extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnAdd, btnDisplay, btnSearch, btnExit;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bai2 frame = new Bai2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Bai2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Courses Management ");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(215, 25, 236, 20);
		contentPane.add(lblNewLabel);
		
		btnAdd = new JButton("Add a new course");
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnAdd.setBounds(158, 67, 293, 33);
		contentPane.add(btnAdd);
		btnAdd.addActionListener(this);
		
		btnDisplay = new JButton("Display all courses");
		btnDisplay.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnDisplay.setBounds(158, 110, 293, 34);
		contentPane.add(btnDisplay);
		btnDisplay.addActionListener(this);
		
		btnSearch = new JButton("Search Course by Course Code");
		btnSearch.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnSearch.setBounds(158, 154, 293, 36);
		contentPane.add(btnSearch);
		btnSearch.addActionListener(this);
		
		btnExit = new JButton("Exit application");
		btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnExit.setBounds(158, 200, 293, 34);
		contentPane.add(btnExit);
		btnExit.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAdd)
		{
			Bai2_Detail1 f21 = new Bai2_Detail1();
			f21.setVisible(true); 
			setVisible(false);
		}

		if(e.getSource() == btnSearch)
		{
			Bai2_Detail2 f22 = new Bai2_Detail2();
			f22.setVisible(true); 
			setVisible(false);
		}
		
		if(e.getSource() == btnDisplay)
		{
			Bai2_Detail3 f23 = new Bai2_Detail3();
			f23.setVisible(true); 
			setVisible(false);
		}
		
		if(e.getSource() == btnExit)
		{
			System.exit(0);
		}
	}
}
