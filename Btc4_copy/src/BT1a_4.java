import java.awt.*;
import java.awt.event.*;

public class BT1a_4 extends Frame implements ItemListener,ActionListener {
	Checkbox male , female;
	List list;
	Choice choice;
	Label lb;
	Button exit;
	Panel pn, pn1, pn2, pn3;
	
	public void GUI()
	{
		male = new Checkbox("Male");
		female = new Checkbox("Female");
		male.addItemListener(this);
		female.addItemListener(this);
		exit = new Button("Exit");
		exit.addActionListener(this);
		list = new List(3);
		list.add("Tiger");
		list.add("Lion");
		list.add("Elephant");
		
		list.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				lb.setText("You selected " + list.getSelectedItem());			
			}
		});
		
		choice = new Choice();
		choice.add("MS DOS");
		choice.add("WINDOWS");
		choice.add("LINUX");
		
		choice.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				lb.setText("You selected " + choice.getSelectedItem());				
			}
		});
		
		lb = new Label("The event is displayed here");
				
		pn1 = new Panel(new FlowLayout());
		pn1.add(male);
		pn1.add(female);
		pn1.add(choice);
		
		pn2 = new Panel(new FlowLayout());
		pn2.add(list);
		
		pn3 = new Panel(new FlowLayout());
		pn3.add(lb);
		pn3.add(exit);
		
		pn = new Panel(new GridLayout(4,2));
		pn.add(pn1);
		pn.add(pn2);
		pn.add(pn3);
		add(pn);
		
		
		setSize(400,300);
		show();
		
		
	}
	public static void main(String[] args) {
		new BT1a_4("ITEM EVENT TEST");
	}

	/**
	 * Create the application.
	 */
	public BT1a_4(String st) {
		super(st);
		GUI();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == male || e.getSource() == female)
		{
			lb.setText("You selected Checkbox");
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == exit)
		{
			System.exit(0);
		}
	}
}
