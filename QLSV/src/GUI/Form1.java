package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTable;

import DTO.CBBItem;
import DTO.LSH;
import DTO.SV;
import BLL.BLL;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Form1 extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtSearch;
	public JTable table;
	private JButton btnShow;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnSort;
	private JButton btnSearch;
	private JLabel lbLSH;
	private JComboBox cbbLSH;
	private JComboBox cbbSort;
	
	// Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form1 frame = new Form1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void GUI()
	{
		setTitle("Students Management ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 787, 453);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 228, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnShow = new JButton("Show");
		btnShow.setForeground(new Color(0, 0, 0));
		btnShow.setBackground(new Color(255, 192, 203));
		btnShow.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnShow.setBounds(21, 376, 85, 21);
		contentPane.add(btnShow);
		btnShow.addActionListener(this);
		
		btnAdd = new JButton("Add");
		btnAdd.setBackground(new Color(255, 192, 203));
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdd.setBounds(145, 376, 85, 21);
		contentPane.add(btnAdd);
		btnAdd.addActionListener(this);
		
		btnEdit = new JButton("Edit");
		btnEdit.setBackground(new Color(255, 192, 203));
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEdit.setBounds(268, 376, 85, 21);
		contentPane.add(btnEdit);
		btnEdit.addActionListener(this);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBackground(new Color(255, 192, 203));
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.setBounds(391, 376, 85, 21);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(this);
		
		btnSort = new JButton("Sort");
		btnSort.setBackground(new Color(255, 192, 203));
		btnSort.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSort.setBounds(512, 376, 85, 21);
		contentPane.add(btnSort);
		btnSort.addActionListener(this);
		
		lbLSH = new JLabel("Class");
		lbLSH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbLSH.setBounds(21, 23, 32, 13);
		contentPane.add(lbLSH);
		
		cbbLSH = new JComboBox();
		cbbLSH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbbLSH.setBounds(59, 21, 110, 21);
		contentPane.add(cbbLSH);
		
		txtSearch = new JTextField();
		txtSearch.setBackground(new Color(255, 250, 250));
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSearch.setBounds(607, 22, 144, 18);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		cbbSort = new JComboBox();
		cbbSort.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbbSort.setBounds(618, 376, 133, 21);
		contentPane.add(cbbSort);
		
		table = new JTable();
		table.setBackground(new Color(255, 250, 250));
		table.setBounds(21, 52, 730, 299);
		contentPane.add(table);	
		
		btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSearch.setBackground(new Color(255, 192, 203));
		btnSearch.setBounds(512, 19, 85, 21);
		contentPane.add(btnSearch);
		btnSearch.addActionListener(this);

		SetCBBLSH(cbbLSH);
		SetCBBSort(cbbSort);
		
	}

	// Create the frame.
	public Form1() {
		GUI();
		Show(BLL.getInstance().GetListSV());	
	}
	
	private static void SetCBBLSH(JComboBox<CBBItem> cbb)
	{
		cbb.addItem(new CBBItem("All",0));
		for (LSH i : BLL.getInstance().GetListLSH()) 
		{
			CBBItem temp = new CBBItem();
			temp.Text = i.NameLop;
			temp.Value = i.ID_Lop;
			cbb.addItem(temp);
		}
		cbb.setSelectedIndex(0);
	}
	
	private static void SetCBBSort(JComboBox<CBBItem> cbb)
	{
		int index = 0;
		for (String i : BLL.getInstance().GetNameCol()) 
		{
			CBBItem temp = new CBBItem();
			temp.Text = i;
			temp.Value = index++;
			cbb.addItem(temp);
		}
	}
	
	public void Show(Vector<SV> list)
	{
		Vector<String> cols = BLL.getInstance().GetNameCol();
		Vector<Vector<Object>> data = SVToObject(list);
		DefaultTableModel tblModel = new DefaultTableModel(data, cols);
	    tblModel.insertRow(0, cols);
	    table.setModel(tblModel);
	}
	
	public Vector<Vector<Object>> SVToObject(Vector<SV> list)
	{
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		for (SV s : list) 
		{
			Vector<Object> dr = new Vector<Object>();
			dr.add(s.MSSV);
			dr.add(s.NameSV);
			dr.add(s.Gender);
			dr.add(s.NS);
			dr.add(s.ID_Lop);
			data.add(dr);
		}
		return data;
	}
	
	public void ShowTable()
	{
		if(((CBBItem)cbbLSH.getSelectedItem()).Value == 0)
		{
			Show(BLL.getInstance().GetListSV());
		}
		else 
		{
			Show(BLL.getInstance().GetSVByNameAndIDLop("", ((CBBItem)cbbLSH.getSelectedItem()).Value));
		}
	}
	public void DeleteSV()
	{
		String mssv = (String)table.getValueAt(table.getSelectedRow(), 0);
		BLL.getInstance().DeleteSV(mssv);
		ShowTable();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnShow) 
		{
			ShowTable();
		}
		if (e.getSource() == btnAdd)
		{
			Form2 f2 = new Form2("");
			f2.setVisible(true); 
			setVisible(false);
		}
		if (e.getSource() == btnEdit)
		{
			if (table.getSelectedRow() != -1) {
				String mssv = (String) table.getValueAt(table.getSelectedRow(), 0);
				System.out.println(mssv);
				Form2 f = new Form2(mssv);
				f.setVisible(true);
				setVisible(false);
			}
		}
		if (e.getSource() == btnDelete)
		{
			DeleteSV();
		}
		if(e.getSource() == btnSort)
		{
			Vector<String> lmssv = new Vector<String>();
			for(int i = 1; i < table.getRowCount(); i++)
			{
				lmssv.add((String) table.getValueAt(i, 0));
			}
			Vector<SV> list = BLL.getInstance().SortSV(lmssv, ((CBBItem)cbbSort.getSelectedItem()).Value);
			Show(list);
		}
		if(e.getSource() == btnSearch)
		{
			Show(BLL.getInstance().GetSVByNameAndIDLop(txtSearch.getText(), ((CBBItem)cbbLSH.getSelectedItem()).Value));
		}
	}
}
