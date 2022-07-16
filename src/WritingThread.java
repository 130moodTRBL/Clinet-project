import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class WritingThread extends Thread{
	Socket socket = null;
	Scanner sc = new Scanner(System.in);
	
	public WritingThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			OutputStream out = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(out, true);
			while(true) {
				writer.println(sc.nextLine());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
