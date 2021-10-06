import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
	public static void main(String args[]) throws Exception {
		Socket socket = new Socket("localhost", 8877);
		DataInputStream din = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		
		Scanner kb = new Scanner(System.in);
		while(true) {
			System.out.print("\nClient: ");
			String msg = kb.nextLine();
			dos.writeUTF("\nClient: " + msg);
			dos.flush();
			
			String st = din.readUTF();
			System.out.print(st);
			kb = kb.reset();
		}
	}
}
