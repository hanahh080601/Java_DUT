package ChatTCP;
import java.net.*;
import java.io.*;
import java.util.*;

public class Client  {

	// Nhập xuất
	private ObjectInputStream sInput;		// đọc từ socket
	private ObjectOutputStream sOutput;		// ghi vào socket
	private Socket socket;

	// Có thể sử dùng Client có GUI hoặc không
	private ClientGUI cg;
	
	// Khai báo server, port và username
	private String server, username;
	private int port;

	// Hàm dựng này được gọi nếu Client chỉ sử dụng console
	Client(String server, int port, String username) {
		// Gọi hàm dựng ở dưới và set ClientGUI bằng null
		this(server, port, username, null);
	}

	// Hàm dựng này được gọi nếu Client sử dụng GUI
	Client(String server, int port, String username, ClientGUI cg) {
		this.server = server;
		this.port = port;
		this.username = username;
		// Xác định là có sử dụng GUI
		this.cg = cg;
	}
	
	// Bắt đầu hộp thoại trò chuyện
	public boolean start() {
		// Kết nối tới server
		try {
			socket = new Socket(server, port);
		} 
		catch(Exception ec) {
			display("Loi ket noi toi server:" + ec);
			return false;
		}
		
		String msg = "Connection accepted " + socket.getInetAddress() + ":" + socket.getPort();
		display(msg);
	
		// Tạo 2 luồng nhập xuất
		try
		{
			sInput  = new ObjectInputStream(socket.getInputStream());
			sOutput = new ObjectOutputStream(socket.getOutputStream());
		}
		catch (IOException eIO) {
			display("Exception creating new Input/output Streams: " + eIO);
			return false;
		}

		// Tạo thread để lắng nghe từ Server 
		new ListenFromServer().start();
		// Gửi username tới server, đây là message duy nhất gửi ở dạng String
		// Các message còn lại đều ở dạng ChatMessage objects
		try
		{
			sOutput.writeObject(username);
		}
		catch (IOException eIO) {
			display("Loi dang nhap: " + eIO);
			disconnect();
			return false;
		}
		// Thông báo thàng công
		return true;
	}

	// Xử lý hiển thị tin nhắn
	private void display(String msg) {
		if(cg == null)
			System.out.println(msg);      // In ra console nếu không dùng GUI
		else
			cg.append(msg + "\n");		  // Thêm chuỗi vào ClientGUI JTextArea
	}
	
	// Gửi tin nhắn đến Server
	void sendMessage(ChatMessage msg) {
		try {
			sOutput.writeObject(msg);
		}
		catch(IOException e) {
			display("Loi gui tin nhan den server: " + e);
		}
	}

	// Có lỗi thì đóng Stream và ngắt kết nối
	private void disconnect() {
		try { 
			if(sInput != null) sInput.close();
		}
		catch(Exception e) {}
		try {
			if(sOutput != null) sOutput.close();
		}
		catch(Exception e) {} 
        try{
			if(socket != null) socket.close();
		}
		catch(Exception e) {}
		
		// Thông báo lỗi trên GUI
		if(cg != null)
			cg.connectionFailed();
			
	}

	/*
	 * Để chạy chương trình Client không dùng GUI thì nhập vào terminal:
	 * > java Client
	 * > java Client username
	 * > java Client username portNumber
	 * > java Client username portNumber serverAddress
	 * "> java Client" tương đương với "> java Client Anonymous 1500 localhost" 
	 * Trong mode Console, nếu fail thì chương trình sẽ dừng
	 * Trong mode GUI, nếu fail thì sẽ thông báo ngắt kết nối
	 */
	public static void main(String[] args) {
		// Khởi tạo giá trị mặc định
		int portNumber = 1500;
		String serverAddress = "localhost";
		String userName = "Anonymous";

		// Tùy thuộc vào số đối số khi gọi hàm dựng mà chia ra các trường hợp sau
		switch(args.length) {
			// > javac Client username portNumber serverAddr
			case 3:
				serverAddress = args[2];
			// > javac Client username portNumber
			case 2:
				try {
					portNumber = Integer.parseInt(args[1]);
				}
				catch(Exception e) {
					System.out.println("Invalid port number.");
					System.out.println("Usage is: > java Client [username] [portNumber] [serverAddress]");
					return;
				}
			// > javac Client username
			case 1: 
				userName = args[0];
			// > java Client
			case 0:
				break;
			// invalid number of arguments
			default:
				System.out.println("Usage is: > java Client [username] [portNumber] {serverAddress]");
			return;
		}
		// Tạo đối tượng Client
		Client client = new Client(serverAddress, portNumber, userName);
		// Kiểm tra có thể kết nối tới server được không
		// Nếu không thì dừng chương trình
		if(!client.start())
			return;
		
		// Đợi tin nhắn từ người dùng
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			System.out.print("> ");
			// đọc tin nhắn từ người dùng
			String msg = scan.nextLine();
			// Đăng xuất nên tin nhắn là "LOGOUT"
			if(msg.equalsIgnoreCase("LOGOUT")) {
				client.sendMessage(new ChatMessage(ChatMessage.LOGOUT, ""));
				// ngắt để thực hiện ngắt kết nối
				break;
			}
			else {				// Mặc định là tin nhắn bình thường
				client.sendMessage(new ChatMessage(ChatMessage.MESSAGE, msg));
			}
		}
		// Ngắt kết nối 
		client.disconnect();	
	}

	// ListenFromServer đợi tin nhắn từ server rồi thêm vào JTextArea (GUI) hoặc in ra (console)
	class ListenFromServer extends Thread {

		public void run() {
			while(true) {
				try {
					String msg = (String) sInput.readObject();
					// không dùng GUI, thì sẽ in ra console
					if(cg == null) {
						System.out.println(msg);
						System.out.print("> ");
					}
					// dùng GUI, thì nối chuỗi vào
					else {
						cg.append(msg);
					}
				}
				catch(IOException e) {
					display("Server has close the connection: " + e);
					if(cg != null) 
						cg.connectionFailed();
					break;
				}
				catch(ClassNotFoundException e2) {
				}
			}
		}
	}
}
