package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {

	String hostname;
	int portNumber;
	PrintWriter out;
	public BufferedReader in;
	BufferedReader stdIn;
	Socket clientSocket;
	String userInput;
	String Player;

	public Client(String h, int p) {
		this.hostname = h;
		this.portNumber = p;
	}
	public Client() {
	
	}
	public void createClientSocket() {
		try {
			this.clientSocket = new Socket(hostname,portNumber);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void clientInOut() throws IOException{
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}
	public void clientStdIn(){
		stdIn = new BufferedReader(new InputStreamReader(System.in));
	}

	public String getHostname() {
		return hostname;
	}

	public int getPortNumber() {
		return portNumber;
	}

	public PrintWriter getOut() {
		return out;
	}

	public BufferedReader getIn() {
		return in;
	}

	public BufferedReader getStdIn() {
		return stdIn;
	}

	public Socket getClientSocket() {
		return clientSocket;
	}
	
	public String getUserInput() {
		return userInput;
	}

	public void setUserInput(String userInput) {
		this.userInput = userInput;
	}
	
	public String getPlayer() {
		return Player;
	}

	public void setPlayer(String player) {
		Player = player;
	}



}
