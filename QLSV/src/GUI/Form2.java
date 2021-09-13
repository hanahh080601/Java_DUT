package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import BLL.BLL;
import DTO.CBBItem;
import DTO.LSH;
import DTO.SV;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Properties;

import javax.swing.*;
import java.awt.*;

public class Form2 extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtMSSV;
	private JTextField txtNameSV;
	public JRadioButton rdbMale, rdbFemale;
	public JComboBox cbbLSH;
	public String MSSV;
	public JButton btnOK, btnCancel;
	public JDatePickerImpl datePicker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form2 frame = new Form2("");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public void GUI()
	{
		setBackground(new Color(211, 211, 211));
		setForeground(Color.GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 250);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 228, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbLSH = new JLabel("Class:");
		lbLSH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbLSH.setBounds(70, 34, 50, 13);
		contentPane.add(lbLSH);

		cbbLSH = new JComboBox();
		cbbLSH.setBounds(122, 32, 156, 21);
		contentPane.add(cbbLSH);

		JLabel lbMSSV = new JLabel("StudentID:");
		lbMSSV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbMSSV.setBounds(39, 76, 72, 13);
		contentPane.add(lbMSSV);

		JLabel lblFullname = new JLabel("Fullname:");
		lblFullname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFullname.setBounds(50, 115, 61, 13);
		contentPane.add(lblFullname);

		JLabel lblDateOfBirth = new JLabel("Date of birth:");
		lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDateOfBirth.setBounds(363, 34, 85, 13);
		contentPane.add(lblDateOfBirth);

		UtilDateModel model = new UtilDateModel();
		model.setSelected(true);
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.getJFormattedTextField().setBackground(Color.LIGHT_GRAY);
		datePicker.getJFormattedTextField().setFont(new Font("Tahoma", Font.PLAIN, 12));
		datePicker.setLocation(363, 57);
		datePicker.setSize(209, 22);
		contentPane.add(datePicker);

		txtMSSV = new JTextField();
		txtMSSV.setBounds(122, 75, 156, 19);
		contentPane.add(txtMSSV);
		txtMSSV.setColumns(10);

		txtNameSV = new JTextField();
		txtNameSV.setBounds(122, 114, 156, 19);
		contentPane.add(txtNameSV);
		txtNameSV.setColumns(10);

		rdbMale = new JRadioButton("Male");
		rdbMale.setBackground(new Color(255, 228, 225));
		rdbMale.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbMale.setBounds(434, 88, 103, 21);
		contentPane.add(rdbMale);

		rdbFemale = new JRadioButton("Female");
		rdbFemale.setBackground(new Color(255, 228, 225));
		rdbFemale.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbFemale.setBounds(434, 111, 103, 21);
		contentPane.add(rdbFemale);

		JLabel lblNewLabel = new JLabel("Gender:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(363, 92, 50, 13);
		contentPane.add(lblNewLabel);

		btnOK = new JButton("OK");
		btnOK.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnOK.setBounds(172, 162, 85, 21);
		contentPane.add(btnOK);
		btnOK.addActionListener(this);

		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancel.setBounds(363, 162, 85, 21);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(this);

		SetCBBLSH(cbbLSH);
	}
	
	public Form2(String mssv) {
		MSSV = mssv;
		GUI();
		if(MSSV != "")
		{
			SV sv = BLL.getInstance().getSVByMSSV(mssv);
			System .out.println(sv.MSSV + sv.NameSV + sv.NS);
			txtMSSV.setText(mssv);
			txtMSSV.setEditable(false); 
			
			txtNameSV.setText(sv.NameSV);
			cbbLSH.setSelectedIndex(sv.ID_Lop - 1);
			if(sv.Gender == true) rdbMale.setSelected(true);
			else rdbFemale.setSelected(true);
			datePicker.getModel().setDate(SV.getYear(sv.NS), SV.getMonth(sv.NS), SV.getDay(sv.NS));
		}
	}

	private void SetCBBLSH(JComboBox cbb) {
		for (LSH i : BLL.getInstance().GetListLSH()) {
			CBBItem temp = new CBBItem();
			temp.Text = i.NameLop;
			temp.Value = i.ID_Lop;
			cbb.addItem(temp);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnOK) {
			// validate
			if (txtMSSV.getText() == "" || txtNameSV.getText() == ""
					|| (!rdbMale.isSelected() && !rdbFemale.isSelected()) || datePicker.getModel().getValue() == "") 
			{
				System.out.println("Invalid input!!!!!");
				return;
			}
			SV s = new SV();
			s.MSSV = txtMSSV.getText();
			s.NameSV = txtNameSV.getText();
			if(rdbMale.isSelected())
				s.Gender = true;
			else 
				s.Gender = false;
	        s.NS = (java.util.Date)datePicker.getModel().getValue(); 
			s.ID_Lop = ((CBBItem)cbbLSH.getSelectedItem()).Value; 
			System.out.println(s.MSSV + s.NameSV + s.Gender + s.NS + s.ID_Lop);
			BLL.getInstance().Execute_DB(s);
            setVisible(false);
            Form1 f = new Form1();
            f.ShowTable();
			f.setVisible(true);	
		}
	}

}
