package ChatUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ChatServerUDP {
	private int port;
	private InetAddress clientIP;
	private int clientPort;
	public static Map<DatagramPacket, Integer> listSK;

	public ChatServerUDP(int port) {
		this.port = port;
	}

	private void execute() throws IOException {

		DatagramSocket server = new DatagramSocket(port);
		WriteServer write = new WriteServer(server);
		write.start();

		System.out.println("Server is listening....");
		while (true) {
			String sms = recieveData(server);
			for (DatagramPacket item : listSK.keySet()) {
				if (!(item.getAddress().equals(clientIP) && item.getPort() == clientPort)) {
					sendData(sms, server, item.getAddress(), item.getPort());
				}
			}
			System.out.println(sms);
		}
	}

	public static void main(String[] args) throws IOException {
		ChatServerUDP.listSK = new HashMap<DatagramPacket, Integer>();
		ChatServerUDP server = new ChatServerUDP(15797);
		server.execute();
	}

	private String recieveData(DatagramSocket server) throws IOException {
		byte[] temp = new byte[1024];
		DatagramPacket recieve_Packet = new DatagramPacket(temp, temp.length);
		server.receive(recieve_Packet);
		clientIP = recieve_Packet.getAddress();
		clientPort = recieve_Packet.getPort();
		checkDuplicate(recieve_Packet); // Kiểm tra trùng Packet trong mảng Map
		return new String(recieve_Packet.getData()).trim();
	}

	private void checkDuplicate(DatagramPacket packet) {
		for (DatagramPacket item : listSK.keySet()) {
			if (item.getAddress().equals(packet.getAddress()) && item.getPort() == packet.getPort()) {
				listSK.replace(item, 0);
				return; // Tìm thấy trùng thoát ra ngay
			}
		}
		listSK.put(packet, 0);
	}

	private void sendData(String value, DatagramSocket server, InetAddress clientIP, int clientPort)
			throws IOException {
		byte[] temp = new byte[1024];
		temp = value.getBytes();
		DatagramPacket send_result_Packet = new DatagramPacket(temp, temp.length, clientIP, clientPort);
		server.send(send_result_Packet);
	}
}

class WriteServer extends Thread {
	private DatagramSocket server;

	public WriteServer(DatagramSocket server) {
		this.server = server;
	}

	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			String sms = sc.nextLine();
			try {
				for (DatagramPacket item : ChatServerUDP.listSK.keySet()) {
					sendData("Server: " + sms, server, item.getAddress(), item.getPort());
				}
			} catch (Exception e) {
			}
		}
	}

	private void sendData(String value, DatagramSocket server, InetAddress clientIP, int clientPort)
			throws IOException {
		byte[] temp = new byte[1024];
		temp = value.getBytes();
		DatagramPacket send_result_Packet = new DatagramPacket(temp, temp.length, clientIP, clientPort);
		server.send(send_result_Packet);
	}
}