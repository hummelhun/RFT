package Server.multithread;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	int portNumber;
	ServerSocket serverSocket;
	Socket clientSocket;

	public Server(int p) {
		this.portNumber = p;
	}

	public Server() {

	}

	public void createServerSocket() throws IOException {
		this.serverSocket = new ServerSocket(portNumber);
	}

	public void createClientSocket() {
		try {
			this.clientSocket = this.serverSocket.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getPortNumber() {
		return portNumber;
	}

	public Socket getClientSocket() {
		return clientSocket;
	}

}
