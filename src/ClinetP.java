import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClinetP {

	public static void main(String[] args) {
		String name = null;
		try {
			Socket socket = new Socket("10.21.20.6", 1234);  
			System.out.println("��Ʈ��ȣ: 1234�� ���� ���� ����");
			
			ListeningThread li = new ListeningThread(socket);
			WritingThread wr = new WritingThread(socket);
			
			li.start();
			wr.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}