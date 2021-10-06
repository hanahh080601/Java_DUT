import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class StringClient {
	
	public static void main(String args[]) throws Exception {
		Socket socket = new Socket("localhost", 1000);
		DataInputStream din = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		
		Scanner kb = new Scanner(System.in);
		while(true) {
			String msg = kb.nextLine();
			dos.writeUTF(msg);
			dos.flush();
			
			String st = din.readUTF();
			System.out.print(st);
			kb = kb.reset();
		}
	}
}
