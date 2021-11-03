package ChatTCP;
import java.io.*;

public class ChatMessage implements Serializable {

	protected static final long serialVersionUID = 1112122200L;

	// Các kiểu tin nhắn khác nhau gửi từ Client
	// WHOISIN: nhận list người dùng đã kết nối
	// MESSAGE: tin nhắn bình thường
	// LOGOUT : để ngắt kết nối với server 
	static final int MESSAGE = 1, LOGOUT = 2;
	
	private int type;
	private String message;
	
	ChatMessage(int type, String message) {
		this.type = type;
		this.message = message;
	}
	
	int getType() {
		return type;
	}
	String getMessage() {
		return message;
	}
}
