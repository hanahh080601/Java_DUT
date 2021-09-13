import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class Bai1 extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JComboBox cbb;
	private JTextArea textArea;
	private JButton btnView, btnReset, btnExit;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bai1 frame = new Bai1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Bai1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 653, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nh\u00E2\u0323p chu\u00F4\u0303i:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel.setBounds(51, 44, 93, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblThcHin = new JLabel("Th\u01B0\u0323c hi\u00EA\u0323n:");
		lblThcHin.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblThcHin.setBounds(51, 105, 93, 28);
		contentPane.add(lblThcHin);
		
		JLabel lblKtQua = new JLabel("K\u00EA\u0301t qua\u0309:");
		lblKtQua.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblKtQua.setBounds(51, 163, 93, 28);
		contentPane.add(lblKtQua);
		
		textField = new JTextField();
		textField.setBounds(154, 44, 439, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		cbb = new JComboBox();
		cbb.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cbb.setBounds(154, 106, 237, 28);
		contentPane.add(cbb);
		
		textArea = new JTextArea();
		textArea.setBounds(154, 167, 439, 235);
		contentPane.add(textArea);
		
		btnView = new JButton("View");
		btnView.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnView.setBounds(120, 431, 85, 32);
		contentPane.add(btnView);
		btnView.addActionListener(this);
		
		btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnExit.setBounds(508, 431, 85, 32);
		contentPane.add(btnExit);
		btnExit.addActionListener(this);
		
		btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnReset.setBounds(321, 431, 85, 32);
		contentPane.add(btnReset);
		btnReset.addActionListener(this);
		
		SetCBB();
	}
	
	private void SetCBB()
	{
		List<String> list = new ArrayList<>(List.of("Đếm từ","Đếm từ trùng lặp","Đảo chuỗi"));
		for (String s : list) 
		{
			cbb.addItem(s);
		}
	}
	
	public static Map<String, Integer> countWords(String input) 
	{
        Map<String, Integer> wordMap = new TreeMap<String, Integer>();
        if (input == null) {
            return wordMap;
        }
        int size = input.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (input.charAt(i) != ' ') 
            {
                sb.append(input.charAt(i));
            } 
            else 
            {
                addWord(wordMap, sb);
                sb = new StringBuilder();
            }
        }
        addWord(wordMap, sb);
        return wordMap;
    }
     
    public static void addWord(Map<String, Integer> wordMap, StringBuilder sb) {
        String word = sb.toString();
        if (word.length() == 0) {
            return;
        }
        if (wordMap.containsKey(word)) {
            int count = wordMap.get(word) + 1;
            wordMap.put(word, count);
        } else {
            wordMap.put(word, 1);
        }
    }
    
    public static String ReverseString(String s)
    {
    	String[] list = s.split(" ");
    	String out = "";
    	int len = list.length;
    	for(int i = len - 1; i >= 0; i--)
    	{
    		out += list[i] + " ";
    	}
    	return out;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnView)
		{
			String input = textField.getText().toString();
			if(cbb.getSelectedIndex() == 0)
			{
				int count = 0;
				String[] list = input.split(" ");
				for (String str : list) {
					if(!str.equals("")) count += 1;
				}
				String output = "Tổng số từ trong chuỗi là: " + Integer.toString(count);
				textArea.setText(output);
			}	
			if(cbb.getSelectedIndex() == 1)
			{
				String s = "";
				Map<String, Integer> wordMap = countWords(input);
		        for (String key : wordMap.keySet()) 
		        {
		            s += ("Từ " + key + " xuất hiện " + wordMap.get(key) + " lần.\n");
		        }
		        textArea.setText(s);
			}
			if(cbb.getSelectedIndex() == 2)
			{
				textArea.setText(ReverseString(input));
			}
			
		}
		if (e.getSource() == btnReset)
		{
			textField.setText("");
			textArea.setText("");
		}
		if (e.getSource() == btnExit)
		{
			System.exit(0);
		}
		
	}
}
