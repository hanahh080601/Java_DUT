import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;
import java.util.Scanner;

public class ServerChat {
	public static void main(String[] args) throws Exception {
		DatagramSocket serverSocket = new DatagramSocket(1000);
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		Scanner kb = new Scanner(System.in);
		while(true) {
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivePacket);
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			String receive = new String(receivePacket.getData(), receivePacket.getOffset(), receivePacket.getLength());
			System.out.println(receive);
			System.out.print("Server: ");
			String msg = kb.nextLine();
			sendData = ("Server: " + msg).getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			serverSocket.send(sendPacket);
			kb = kb.reset();		
		}
	}
}
