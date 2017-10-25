package Server.multithread;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.net.ServerSocket;
import java.net.Socket;

public class RequestHandler implements Runnable {
	private Socket client;
	ServerSocket serverSocket = null;
	Server server = new Server();
	ServerDispatcher message;
	BufferedReader in;
	BufferedWriter writer;
	String userInput;

	public RequestHandler(Socket client, Server server, ServerDispatcher message) {
		this.client = client;
		this.server = server;
		this.message = message;
	}

	@Override
	public void run() {
		try {
			writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			System.out.println("Thread started with name:" + Thread.currentThread().getName());
			while ((userInput = in.readLine()) != null) {
				userInput = userInput.replaceAll("[^A-Za-z0-9 ]", "");
				System.out.println("Received message from " + Thread.currentThread().getName() + " : " + userInput);
				//writer.write("message from:"+ message.getWho() + "message:" + message.getMessage());
				writer.newLine();
				//messageChange();
				writer.write("You entered : " + userInput);
				writer.newLine();
				writer.flush();
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*public ServerDispatcher messageChange(){
		message.setWho(client.toString());
		message.setMessage(userInput);
		return message;
	}*/

}
