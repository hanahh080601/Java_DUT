package StringUDP;
import java.io.*;
import java.net.*;

public class ServerStringUDP {
	public static void main(String args[]) throws Exception {
		DatagramSocket serverSocket = new DatagramSocket(1500);
		System.out.print("Server is started!");
		while (true) {
			new ServerThreadStringUDP(serverSocket).start();
		}
	}
}

class ServerThreadStringUDP implements Runnable {
	private Thread t;
	DatagramSocket serverSocket = null;
	public ServerThreadStringUDP(DatagramSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	
	public void run() {
		try {
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[10000];
			while(true) {
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);
				InetAddress IPAddress = receivePacket.getAddress();
				int port = receivePacket.getPort();
				String request = new String(receivePacket.getData(), receivePacket.getOffset(), receivePacket.getLength());
				System.out.print(request);
				if(!request.trim().equals("")) {
					String upperstr = "Viết hoa:" + upperCase(request) + "\n";
					String lowerstr = "Viết thường: " + lowerCase(request) + "\n";
					String lowupstr = "Vừa hoa vừa thường: " + low_upCase(request) + "\n";
					String countWord = "Số từ: " + DemTu(request) + "\n";
					String msg = upperstr + lowerstr + lowupstr + countWord;
					sendData = msg.toString().getBytes();
					System.out.print(msg);
				}
				else sendData = "Server not know what you want?".getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
				serverSocket.send(sendPacket);
			}
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


