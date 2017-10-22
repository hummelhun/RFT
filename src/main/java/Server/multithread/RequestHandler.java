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
	BufferedReader in;
	BufferedWriter writer;

	public RequestHandler(Socket client, Server server) {
		this.client = client;
		this.server = server;
	}

	@Override
	public void run() {
		try {
			writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			System.out.println("Thread started with name:" + Thread.currentThread().getName());
			String userInput;
			while ((userInput = in.readLine()) != null) {
				userInput = userInput.replaceAll("[^A-Za-z0-9 ]", "");
				System.out.println("Received message from " + Thread.currentThread().getName() + " : " + userInput);
				writer.write("You entered : " + userInput);
				writer.newLine();
				writer.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
