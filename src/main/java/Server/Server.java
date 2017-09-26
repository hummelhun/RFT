package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	int portNumber;
	ServerSocket serverSocket;
	Socket clientSocket1;
	Socket clientSocket2;
	
	public Server(int p){
		this.portNumber = p;
	}
	
	public void createSocket(){
		try {
			ServerSocket serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
