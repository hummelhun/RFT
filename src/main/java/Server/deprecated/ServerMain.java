package Server.deprecated;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

	public static void main(String[] args) throws IOException {
		System.out.println("Succesfull");

		if (args.length < 1) {
			System.err.println("Usage: java EchoServer <port number>");
			System.exit(1);
		}

		Server server = new Server(Integer.parseInt(args[0]));

		System.out.println("Server started. Listening on Port 8005");
		
		server.createServerSocket();
		server.createClientSocket1();
		server.Client1InOut();
			System.out.println("Client 1 connected on port " + server.getPortNumber() + ". Servicing requests.");
		server.createClientSocket2();
		server.Client2InOut();
		    System.out.println("Client 2 connected on port " + server.getPortNumber() + ". Servicing requests.");
		
		    String inputLine;			
			while ((inputLine = server.in1.readLine()) != null) {
				System.out.println("Received message: " + inputLine + " from " + server.clientSocket1.toString());
				server.out1.println(inputLine);
			}
		

	}

}
