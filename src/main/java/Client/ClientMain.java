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
			/*while ((client.userInput = client.stdIn.readLine()) != null) {
				client.out.println(client.userInput);
				System.out.println("echo: " + client.in.readLine());
			}*/
		
			
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + client.getHostname());
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " + client.getHostname());
			System.exit(1);
		}
	
        Sender sender = new Sender(client.getOut());
        Listener listener = new Listener(client.getIn(),client);
        sender.setDaemon(true);
        sender.start();
        System.out.println("is this real life?");
        listener.setDaemon(true);
        listener.start();
        System.out.println("or its just FANTA sea?");
        while(true){ //this is a while cycle.. infinite
        	}
        
        /*try {
           // Read messages from the server and print them
            String message;
           while ((message=client.in.readLine()) != null) {
               System.out.println(message);
           }
        } catch (IOException ioe) {
           System.err.println("Connection to server broken.");
           ioe.printStackTrace();
        }*/

	}

}
