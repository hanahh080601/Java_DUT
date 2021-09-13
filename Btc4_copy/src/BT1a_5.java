import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BT1a_5 extends Frame implements KeyListener{
	Label lb;
	void KeyEventTest(String title){
		super(title);
		lb = new Label("A", Label.CENTER);
		add(lb);
		requestFocus();
		addKeyListener(this);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				dispose();
				System.exit(0);
			}
		});
	}
	
	public void keyTyped(KeyEvent e) {
		lb.setText(String.valueOf(e.getKeyChar()));
	}
	
	public void keyPressed(KeyEvent e) {
		
	}
	
	public void keyRealeased(KeyEvent e) {
		
	}
	
	public static void main(String args[]) {
		BT1a_5 bt = new BT1a_5("Key Event Tets");
		bt.setBounds(100, 100, 300, 300);
		bt.setVisible(true);
	}
}
