package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	int portNumber;
	ServerSocket serverSocket;
	Socket clientSocket1;
	Socket clientSocket2;
	PrintWriter out1;
	PrintWriter out2;
	BufferedReader in1;
	BufferedReader in2;
	
	public Server(int p){
		this.portNumber = p;
	}
	
	public void Client1InOut() throws IOException{
		out1 = new PrintWriter(clientSocket1.getOutputStream(), true);
		in1 = new BufferedReader(new InputStreamReader(clientSocket1.getInputStream()));
	}
	public void Client2InOut() throws IOException{
		out2 = new PrintWriter(clientSocket2.getOutputStream(), true);
		in2 = new BufferedReader(new InputStreamReader(clientSocket2.getInputStream()));
	}
	
	public void createServerSocket(){
		try {
			this.serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void createClientSocket1(){
		try {
			this.clientSocket1 = this.serverSocket.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createClientSocket2(){
		try {
			this.clientSocket2 = this.serverSocket.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public int getPortNumber() {
		return portNumber;
	}

	public Socket getClientSocket1() {
		return clientSocket1;
	}

	public Socket getClientSocket2() {
		return clientSocket2;
	}

	public PrintWriter getOut1() {
		return out1;
	}

	public PrintWriter getOut2() {
		return out2;
	}

	public BufferedReader getIn1() {
		return in1;
	}

	public BufferedReader getIn2() {
		return in2;
	}
	
}
