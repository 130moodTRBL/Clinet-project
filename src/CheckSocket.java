import java.io.IOException;
import java.net.Socket;

public class CheckSocket {

	public static void main(String[] args) {
		String host = "192.168.56.1";
		for(int i = 0; i <= 4000; i++) {
			try {
				Socket socket = new Socket(host, i);
				System.out.println(i+"로 연결성공");
			} catch (IOException e) {
				System.out.println("실패");
			}
		}

	}

}
