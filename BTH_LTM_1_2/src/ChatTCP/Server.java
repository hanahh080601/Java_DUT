package ChatTCP;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Server {
	
	// ID duy nhất cho mỗi kết nối
	private static int uniqueId;
	
	// ArrayList lưu danh sách các Client
	private ArrayList<ClientThread> al;
	
	// Xác định có sử dụng GUI hay không
	private ServerGUI sg;
	
	// Hiển thị thời gian gửi nhận
	private SimpleDateFormat sdf;
	
	private int port;
	
	// kiểm tra có ngắt kết nối không
	private boolean keepGoing;
	

	// Hàm dựng không sử dụng GUI
	public Server(int port) {
		this(port, null);
	}
	
	// Hàm dựng sử dụng GUI
	public Server(int port, ServerGUI sg) {
		this.sg = sg;
		this.port = port;
		sdf = new SimpleDateFormat("HH:mm:ss");
		al = new ArrayList<ClientThread>();
	}
	
	public void start() {
		keepGoing = true;
		// Tạo server socket và đợi yêu cầu kết nối
		try 
		{
			ServerSocket serverSocket = new ServerSocket(port);
			while(keepGoing) 
			{
				display("Server waiting for Clients on port " + port + ".");
				Socket socket = serverSocket.accept();  
				
				if(!keepGoing)
					break;
				ClientThread t = new ClientThread(socket); 
				al.add(t);									
				t.start();
			}
			// Khi được yêu cầu ngắt kết nối
			try {
				serverSocket.close();
				for(int i = 0; i < al.size(); ++i) {
					ClientThread tc = al.get(i);
					try {
					tc.sInput.close();
					tc.sOutput.close();
					tc.socket.close();
					}
					catch(IOException ioE) {
					}
				}
			}
			catch(Exception e) {
				display("Exception closing the server and clients: " + e);
			}
		}
		catch (IOException e) {
            String msg = sdf.format(new Date()) + " Exception on new ServerSocket: " + e + "\n";
			display(msg);
		}
	}		
    
	protected void stop() {
		keepGoing = false;
		// connect to myself as Client to exit statement 
		// Socket socket = serverSocket.accept();
		try {
			new Socket("localhost", port);
		}
		catch(Exception e) {}
	}


	// Hiển thị 1 sự kiện (không phải tin nhắn bình thường)
	private void display(String msg) {
		String time = sdf.format(new Date()) + " " + msg;
		if(sg == null)
			System.out.println(time);
		else
			sg.appendEvent(time + "\n");
	}
	
	// Broadcast tin ngắn đến tất cả Client
	private synchronized void broadcast(String message) {
		String time = sdf.format(new Date());
		String messageLf = time + " " + message + "\n";
		if(sg == null)
			System.out.print(messageLf);
		else
			sg.appendRoom(messageLf);  
		
		for(int i = al.size(); --i >= 0;) {
			ClientThread ct = al.get(i);
			// Kiểm tra hàm ghi của Client nếu false thì xóa khỏi list
			if(!ct.writeMsg(messageLf)) {
				al.remove(i);
				display("Disconnected Client " + ct.username + " removed from list.");
			}
		}
	}

	// Xóa client ra khỏi list khi logout
	synchronized void remove(int id) {
		for(int i = 0; i < al.size(); ++i) {
			ClientThread ct = al.get(i);
			if(ct.id == id) {
				al.remove(i);
				return;
			}
		}
	}
	
	/*
	 *  Nhập 1 trong 2 lệnh sau để run console: 
	 * > java Server
	 * > java Server portNumber
	 * Nếu port không được nhập thì mặc định là 1500
	 */ 
	public static void main(String[] args) {
		int portNumber = 1500;
		switch(args.length) {
			case 1:
				try {
					portNumber = Integer.parseInt(args[0]);
				}
				catch(Exception e) {
					System.out.println("Invalid port number.");
					System.out.println("Usage is: > java Server [portNumber]");
					return;
				}
			case 0:
				break;
			default:
				System.out.println("Usage is: > java Server [portNumber]");
				return;
				
		}
		Server server = new Server(portNumber);
		server.start();
	}

	// Một instance của lớp ClientThread sẽ đại diện cho 1 client
	class ClientThread extends Thread {
		Socket socket;
		ObjectInputStream sInput;
		ObjectOutputStream sOutput;
		
		// ID duy nhất (dễ hơn cho việc ngắt kết nối)
		int id;
		
		String username;
		ChatMessage cm;
		String date;

		ClientThread(Socket socket) {
			id = ++uniqueId;
			this.socket = socket;
			System.out.println("Thread trying to create Object Input/Output Streams");
			try
			{
				sOutput = new ObjectOutputStream(socket.getOutputStream());
				sInput  = new ObjectInputStream(socket.getInputStream());
				username = (String) sInput.readObject();
				display(username + " just connected.");
			}
			catch (IOException e) {
				display("Exception creating new Input/output Streams: " + e);
				return;
			}
			catch (ClassNotFoundException e) {
			}
            date = new Date().toString() + "\n";
		}

		public void run() {
			// Lặp đến khi Logout
			boolean keepGoing = true;
			while(keepGoing) {
				try {
					cm = (ChatMessage) sInput.readObject();
				}
				catch (IOException e) {
					display(username + " Exception reading Streams: " + e);
					break;				
				}
				catch(ClassNotFoundException e2) {
					break;
				}
				// Phần message của đối tượng ChatMessage
				String message = cm.getMessage();

				// Switch type của message 
				switch(cm.getType()) {
					case ChatMessage.MESSAGE:
						broadcast(username + ": " + message);
						break;
					case ChatMessage.LOGOUT:
						display(username + " disconnected with a LOGOUT message.");
						keepGoing = false;
						break;
				}
			}
			// Xóa server ra khỏi array list mà chứa client đã kết nối
			remove(id);
			close();
		}
		
		private void close() {
			try {
				if(sOutput != null) sOutput.close();
			}
			catch(Exception e) {}
			try {
				if(sInput != null) sInput.close();
			}
			catch(Exception e) {};
			try {
				if(socket != null) socket.close();
			}
			catch (Exception e) {}
		}

		// Viết một chuỗi vào output stream của Client
		private boolean writeMsg(String msg) {
			// Nếu Client còn kết nối thì gửi tin nhắn đến
			if(!socket.isConnected()) {
				close();
				return false;
			}
			// Ghi message vào stream
			try {
				sOutput.writeObject(msg);
			}
			// Nếu có lỗi thì không hủy mà thông báo cho người dùng
			catch(IOException e) {
				display("Error sending message to " + username);
				display(e.toString());
			}
			return true;
		}
	}
}

