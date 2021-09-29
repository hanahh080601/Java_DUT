import java.io.*;
import java.util.Map;
import java.util.TreeMap;

import com.sun.tools.classfile.StackMapTable_attribute.same_frame;

public class IO_String {
	public String nhapchuoi() throws IOException
	{
		InputStreamReader lv = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(lv);
		String s = br.readLine();
		return s;
	}
	
	public String ReverseString(String s)
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
	
	private String upperCase(String st)
	{
		char c;
		String st1 = "";
		for(int i = 0; i < st.length(); i++)
		{
			c = st.charAt(i);
			if(c >= 'a' && c <= 'z')
			{
				c = (char) (c-32);
			}
			st1 += c;
		}
		return st1;
	}
	
	private String lowerCase(String st)
	{
		char c;
		String st1 = "";
		for(int i = 0; i < st.length(); i++)
		{
			c = st.charAt(i);
			if(c >= 'A' && c <= 'Z')
			{
				c = (char) (c+32);
			}
			st1 += c;
		}
		return st1;
	}
	
	private String low_upCase(String st)
	{
		char c;
		String st1 = "";
		for(int i = 0; i < st.length(); i++)
		{
			c = st.charAt(i);
			if(c >= 'A' && c <= 'Z')
			{
				c = (char) (c+32);
			}
			else if(c >= 'a' && c <= 'z')
			{
				c = (char) (c-32);
			}
			st1 += c;
		}
		return st1;
	}
	
	private int DemTu(String s) {
		int count = 0;
		String[] list = s.split(" ");
		for (String str : list) {
			if(!str.equals("")) count += 1;
		}
		return count;
	}
	
	public Map<String, Integer> countWords(String input) 
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
     
    public void addWord(Map<String, Integer> wordMap, StringBuilder sb) {
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
    
    public String WordList(String input) {
    	String s = "";
		Map<String, Integer> wordMap = countWords(input);
        for (String key : wordMap.keySet()) 
        {
            s += ("Tu '" + key + "' xuat hien " + wordMap.get(key) + " lan.\n");
        }
        return s;
    }
    
    public static void main(String args[]) {
    	IO_String str = new IO_String();
    	String st = "";
    	try
		{
			System.out.print("Nhap chuoi ki tu: ");
			st = str.nhapchuoi();
		}
		catch(Exception e) {}
		System.out.println("\na. Chuoi duoc dao la: " + str.ReverseString(st));
		System.out.println("\nb. Chuoi duoc uppercase la: " + str.upperCase(st));
		System.out.println("\nc. Chuoi duoc lowercase la: " + str.lowerCase(st));
		System.out.println("\nd. Chuoi duoc low_upcase la: " + str.low_upCase(st));
		System.out.println("\ne. So tu trong chuoi la: " + str.DemTu(st));
		System.out.println("\nf. Tan so xuat hien cac tu trong chuoi la: \n" + str.WordList(st));
		
    }
    
}
