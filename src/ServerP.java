import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerP extends Thread{
	static ArrayList<Socket> list = new ArrayList<Socket>();
	static Socket socket = null;
	
	public ServerP(Socket socket) {
		this.socket = socket; 
		list.add(socket); 
	}
	
	public void run() {
		try {
			System.out.println("���� : " + socket.getInetAddress() +" IP�� Ŭ���̾�Ʈ�� ����Ǿ����ϴ�");
			
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			
			OutputStream out = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(out, true);
			
			writer.println("������ ����Ǿ����ϴ�. �г����� �Է��Ͻʽÿ�.");
			
			String readValue;
			String name = null;
			boolean identity = false;
			
			while((readValue = reader.readLine()) != null) {
				if(!identity) {
					name = readValue;
					identity = true;
					writer.println("[ "+name+" ] ���� ������");
					continue;
				}
				
				for(int i = 0; i < list.size(); i++) {
					out = list.get(i).getOutputStream();
					writer = new PrintWriter(out, true);
					writer.println("[ "+name+" ]: "+readValue);
				}
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			int socketPort = 1234;
			ServerSocket serverSocket = new ServerSocket(socketPort);
			System.out.println("��Ʈ��ȣ: "+socketPort+"�� ������ ���Ƚ��ϴ�");
			
			while(true) {
				Socket socketUser = serverSocket.accept();
				Thread th = new ServerP(socketUser);
				th.start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}