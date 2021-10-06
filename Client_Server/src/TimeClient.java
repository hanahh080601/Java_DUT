import java.io.DataInputStream;
import java.net.Socket;

public class TimeClient {
	public static void main(String args[]) throws Exception {
		Socket socket = new Socket("localhost", 7788);
		DataInputStream din = new DataInputStream(socket.getInputStream());
		String time = din.readUTF();
		System.out.print(time);
		socket.close();
	}
}
