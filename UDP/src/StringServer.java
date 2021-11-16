import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

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
	
	public static void main(String[] args) throws Exception {
		DatagramSocket serverSocket = new DatagramSocket(1234);
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[10000];
		while(true) {
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivePacket);
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			String request = new String(receivePacket.getData());
			System.out.print(request);
			if(!request.trim().equals(""))
			{
				String upperstr = "Viet hoa:" + upperCase(request) + "\n";
				String lowupstr = "Vua hoa vua thuong: " + low_upCase(request) + "\n";
				String countWord = "So tu: " + DemTu(request) + "\n";
				String countNA = "So nguyen am: " + CountNA(request) + "\n";
				String msg = upperstr + lowupstr + countWord + countNA;
				sendData = msg.toString().getBytes();
				System.out.print(msg);
			}
			else sendData = "Server not know what you want?".getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			serverSocket.send(sendPacket);
		}
	}
}
