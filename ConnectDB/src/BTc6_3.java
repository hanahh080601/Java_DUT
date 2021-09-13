import java.awt.Button;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.BoxLayout;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class BTc6_3 extends Frame implements ActionListener {

	Label input, sql,id,name,address,total;
	TextField txtInput,txtSQL;
	Button submit , reset , cancel;
	TextArea area;
	
	
	Panel pn , pn1  ,pn2,pn3,pn4,pn5;
	
	
	public void GUI()
	{
		input = new Label("Input Information");
		sql = new Label("SQL");
		id = new Label("ID ");
		id.setAlignment((int) Component.RIGHT_ALIGNMENT);
		name = new Label("Name");
		name.setAlignment((int) Component.RIGHT_ALIGNMENT);
		address= new Label("Address");
		address.setAlignment((int) Component.RIGHT_ALIGNMENT);
		total = new Label("Total");
		total.setAlignment((int) Component.RIGHT_ALIGNMENT);
		
		txtInput = new TextField(10);
	
		txtSQL = new TextField(10);
		
		submit = new Button("Submit");
		reset = new Button("Reset");
		cancel = new Button("Cancel");
		
		submit.addActionListener(this);
		reset.addActionListener(this);
		cancel.addActionListener(this);
		
		area = new TextArea();
		
		
		pn = new Panel(new GridLayout(4,1));
		pn1 = new Panel(new GridLayout(2,2));
		pn2 = new Panel(new FlowLayout());
		pn2.setLayout(new BoxLayout(pn2, BoxLayout.LINE_AXIS));
		pn3 = new Panel(new FlowLayout());
		
		pn4 = new Panel(new FlowLayout());
		
		pn1.add(input);
		pn1.add(txtInput);
		pn1.add(sql);
		pn1.add(txtSQL);
		pn2.add(id);
		
		pn2.add(name);
		pn2.add(address);
		pn2.add(total);
		pn3.add(area);
		pn4.add(submit);
		pn4.add(reset);
		pn4.add(cancel);
		pn.add(pn1);
		pn.add(pn2);
		pn.add(pn3);
		pn.add(pn4);
		add(pn);
		setSize(520,350);
		show();
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	new BTc6_3("Database Programing");
	
	
	}

	/**
	 * Create the application.
	 */
	public BTc6_3(String st) {
		super(st);
		GUI();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == cancel)
		{
			System.exit(0);
		}
		if(e.getSource() == reset)
		{
			area.setText("");
		}
		if(e.getSource()==submit)
		{
			System.out.println("Ket Noi CSDL");
			
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				//String url = "jdbc:mysql://127.0.0.1:3306/test";
				String url = txtInput.getText();
				Connection con = (Connection) DriverManager.getConnection(url, "root", "");
				Statement stmt = (Statement) con.createStatement();
				//String sqlInsert = "INSERT INTO table1(ID , Name , Address, Total ) VALUES('102190','Ngoc Han' , 'Da Nang' , '40000')";
				//String sql = "SELECT * FROM data1";
				String sql = txtSQL.getText();
				//stmt.executeUpdate(sqlInsert);
				ResultSet rs = stmt.executeQuery(sql);
				
				while(rs.next())
				{
					int id = rs.getInt("ID");
					String name = rs.getString("Name");
					String address = rs.getString("Address");
					double total = rs.getDouble("Total");
					area.appendText("\t" + id + "\t"+" \t" + name+"\t"+" \t" + address +"\t"+ "\t "+"\t" + total + "\n");
					System.out.println("ID= " + id + " Name= "+ name + " Address =" +address + " Total=" + total);				
				}
				rs.close();
				stmt.close();			
			}
			catch(Exception a)
			{
				System.out.println("Error " + a);
			}		
			}
			
		}
		
	}



