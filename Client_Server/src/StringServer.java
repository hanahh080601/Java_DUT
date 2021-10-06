import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class StringServer {
	
	public static String upperCase(String st)
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
	
	public static String lowerCase(String st)
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
	
	public static String low_upCase(String st)
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
	
	public static int DemTu(String s) {
		int count = 0;
		String[] list = s.split(" ");
		for (String str : list) {
			if(!str.equals("")) count += 1;
		}
		return count;
	}
	
	public static int CountNA(String s) {
		int count = 0;
	    char[] c = {'a', 'e', 'u', 'o', 'i'};
		for(int i = 0; i < s.length(); i++) {
			for (char d : c) {
				if(s.charAt(i) == d) {
					count += 1;
				}
			}
		}
		return count;
	}
	
	public static void main(String args[]) throws Exception {
		ServerSocket server = new ServerSocket(1000);
		System.out.print("Server is started!");
		Socket socket = server.accept();
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		DataInputStream din = new DataInputStream(socket.getInputStream());
		
		while(true) {
			String st = din.readUTF();
			System.out.print(st);
			String upperstr = "Viet hoa:" + upperCase(st) + "\n";
			String lowerstr = "Viet thuong: " + lowerCase(st) + "\n";
			String lowupstr = "Vua hoa vua thuong: " + low_upCase(st) + "\n";
			String countWord = "So tu: " + DemTu(st) + "\n";
			String countNA = "So nguyen am: " + CountNA(st) + "\n";
			String msg = upperstr + lowerstr + lowupstr + countWord + countNA;
			dos.writeUTF(msg);
			dos.flush();
		}
	}
}
