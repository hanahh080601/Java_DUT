package StringTCP;
import java.io.*;
import java.net.*;

class ServerThreadStringTCP implements Runnable {
	private Thread t;
	Socket socket = null;
	public ServerThreadStringTCP(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			DataInputStream din = new DataInputStream(socket.getInputStream());
			String st = din.readUTF();
			System.out.println(st);
			String upperstr = "Viet hoa:" + upperCase(st) + "\n";
			String lowerstr = "Viet thuong: " + lowerCase(st) + "\n";
			String lowupstr = "Vua hoa vua thuong: " + low_upCase(st) + "\n";
			String countWord = "So tu: " + DemTu(st) + "\n";
			String msg = upperstr + lowerstr + lowupstr + countWord;
			dos.writeUTF(msg);
			dos.flush();
		} catch (Exception ex) {
			System.out.println("Error");
		}
	}
	
	public String upperCase(String st)
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
	
	public String lowerCase(String st)
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
	
	public String low_upCase(String st)
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
	
	public int DemTu(String s) {
		int count = 0;
		String[] list = s.split(" ");
		for (String str : list) {
			if(!str.equals("")) count += 1;
		}
		return count;
	}


	public void start() {
		if (t == null) {
            t = new Thread(this);
            t.start();
        }
	}
}

public class ServerStringTCP {
	public static void main(String args[]) throws Exception {
		ServerSocket server = new ServerSocket(1000);
		System.out.print("Server is started!");
		while (true) {
			new ServerThreadStringTCP(server.accept()).start();
		}
	}
}
