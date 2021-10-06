import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {
	public static void main(String args[]) throws Exception {
		ServerSocket server = new ServerSocket(8877);
		System.out.print("Server is started!");
		Socket socket = server.accept();
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		DataInputStream din = new DataInputStream(socket.getInputStream());
		
		Scanner kb = new Scanner(System.in);
		while(true) {
			String st = din.readUTF();
			System.out.print(st);
			System.out.print("Server: ");
			String msg = kb.nextLine();
			dos.writeUTF("Server: " + msg);
			dos.flush();
			kb = kb.reset();
		}
	}
}
