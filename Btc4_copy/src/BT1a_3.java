import java.awt.*;
import java.awt.event.*;

public class BT1a_3 extends Frame implements ActionListener, TextListener {
	TextField txt;
	List list;
	Label lb;
	Button exit, ok;
	Panel pn, pn1, pn2, pn3;
	
	public void GUI()
	{
		exit = new Button("Exit");
		exit.addActionListener(this);
		
		ok = new Button("OK");
		ok.addActionListener(this);
		
		list = new List(3);
		list.add("Tiger");
		list.add("Lion");
		list.add("Elephant");
		
		txt = new TextField();
		txt.setSize(500, 10);
		txt.addTextListener(this);
		
		list.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				lb.setText("You selected " + list.getSelectedItem());			
			}
		});
		
		lb = new Label("The event is displayed here");
				
		pn1 = new Panel(new FlowLayout());
		pn1.add(ok);
		pn1.add(txt);
		pn1.add(list);
		
		pn2 = new Panel(new FlowLayout());
		pn2.add(lb);
		
		pn3 = new Panel(new FlowLayout());
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
		new BT1a_3("ACTION EVENT TEST");
	}

	/**
	 * Create the application.
	 */
	public BT1a_3(String st) {
		super(st);
		GUI();
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == ok)
		{
			lb.setText("You clicked the button");
		}
		if(e.getSource() == exit)
		{
			System.exit(0);
		}
	}

	@Override
	public void textValueChanged(TextEvent e) {
		lb.setText("You entered in the TextField");
	}

}
