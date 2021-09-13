import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.*;
public class BT4_3 {
	public String nhapchuoi() throws IOException
	{
		InputStreamReader lv = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(lv);
		String s = br.readLine();
		return s;
	}
	
	private String ChuoiDao(String st)
	{
		 char c;
		 String st1 = "";
		 for(int i = st.length() - 1; i >= 0; i--)
		 {
			 c = st.charAt(i);
			 st1 += c;
		 }
		 return st1;
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
	
	public static void main(String args[])
	{
		String st = "";
		BT4_3 str = new BT4_3();
		try
		{
			System.out.print("Nhap chuoi ki tu: ");
			st = str.nhapchuoi();
		}
		catch(Exception e) {}
		System.out.println("4.3a. Chuoi duoc dao la: " + str.ChuoiDao(st));
		System.out.println("4.3b. Chuoi duoc uppercase la: " + str.upperCase(st));
		System.out.println("4.3c. Chuoi duoc lowercase la: " + str.lowerCase(st));
		System.out.println("4.3d. Chuoi duoc low_upcase la: " + str.low_upCase(st));
	}
}
