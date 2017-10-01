package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientMain {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Usage: java Client <host name> <port number>");
			System.exit(1);
		}
		Client client = new Client(args[0], Integer.parseInt(args[1]));
		try {
			client.createClientSocket();
			client.clientInOut();
			client.clientStdIn();
			while ((client.userInput = client.stdIn.readLine()) != null) {
				client.out.println(client.userInput);
				System.out.println("echo: " + client.in.readLine());
			}
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + client.getHostname());
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " + client.getHostname());
			System.exit(1);
		}

	}

}
