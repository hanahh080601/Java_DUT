import java.awt.*;
import java.awt.event.*;

public class BT4_13 extends Frame implements ItemListener, ActionListener {
	CheckboxGroup cbg;
	Checkbox male , female;
	List list;
	Choice choice;
	Label lb;
	Button exit;
	Panel pn, pn1, pn2,pn3,pn4;
	
	public void GUI()
	{
		cbg = new CheckboxGroup();
		male = new Checkbox("Male");
		female = new Checkbox("Female");
		male.addItemListener(this);
		female.addItemListener(this);
		male.setCheckboxGroup(cbg);
		female.setCheckboxGroup(cbg);
		exit = new Button("Exit");
		exit.addActionListener(this);
		list = new List(3);
		list.add("MS DOS");
		list.add("WINDOWS");
		list.add("LINUX");
		
		list.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				lb.setText("You selected " + list.getSelectedItem());			
			}
			
		});
		
		choice = new Choice();
		choice.add("Tiger");
		choice.add("Lion");
		choice.add("Elephant");
		
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
		
		pn2 = new Panel(new FlowLayout());
		pn2.add(list);
		pn3 = new Panel(new FlowLayout());
		pn3.add(choice);
		
		pn4 = new Panel(new FlowLayout());
		pn4.add(lb);
		pn4.add(exit);
		
		pn = new Panel(new GridLayout(4,2));
		pn.add(pn1);
		pn.add(pn2);
		pn.add(pn3);
		pn.add(pn4);
		add(pn);
		
		
		setSize(400,300);
		show();
		
		
	}
	public static void main(String[] args) {
		new BT4_13("ITEM EVENT TEST");
	}

	/**
	 * Create the application.
	 */
	public BT4_13(String st) {
		super(st);
		GUI();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == male)
		{
			lb.setText("You selected Checkbox Male");
		}
		if(e.getSource() == female)
		{
			lb.setText("You selected Checkbox Female");
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exit)
		{
			System.exit(0);
		}
	}
}
