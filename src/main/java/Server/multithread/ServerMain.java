package Server.multithread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

 
public class ServerMain {
	public static void main(String[] args) throws IOException {
		System.out.println("Start of main");
		if (args.length < 1) {
			System.err.println("Usage: java EchoServer <port number>");
			System.exit(1);
		}

		ServerSocket serverSocket = null;
		try {
			Server server = new Server(Integer.parseInt(args[0]));
			server.createServerSocket();
			serverSocket = server.getServerSocket();
		}catch (IOException e) {
			System.out.println(
					"Exception caught when trying to listen on port " + args[0] + " or listening for a connection");
			System.out.println(e.getMessage());
		}
		   ServerDispatcher serverDispatcher = new ServerDispatcher();
	        serverDispatcher.start();
	        
	        while (true) {
	            try {
	                Socket socket = serverSocket.accept();
	                ClientInfo clientInfo = new ClientInfo();
	                clientInfo.mSocket = socket;
	                ClientListener clientListener =
	                    new ClientListener(clientInfo, serverDispatcher);
	                ClientSender clientSender =
	                    new ClientSender(clientInfo, serverDispatcher);
	                clientInfo.mClientListener = clientListener;
	                clientInfo.mClientSender = clientSender;
	               
	                clientListener.start();
	                clientSender.start();
	                serverDispatcher.addClient(clientInfo);
	                System.out.println("Client connected on socket!");
	                ///////////////////////////
	                //here comes the logic!
	                ///////////////////////////
	                
	            } catch (IOException ioe) {
	                ioe.printStackTrace();
	            }
	         }
		/*ExecutorService executor = null;
		try {
			server.createServerSocket();
			executor = Executors.newFixedThreadPool(5);
			System.out.println("Waiting for clients");
			while (true) {
				server.createClientSocket();
				Runnable worker = new RequestHandler(server.getClientSocket(), server, message);
				executor.execute(worker);
				
				
			}
		} catch (IOException e) {
			System.out.println(
					"Exception caught when trying to listen on port " + args[0] + " or listening for a connection");
			System.out.println(e.getMessage());
		} finally {
			if (executor != null) {
				executor.shutdown();
			}
		}*/

	}
}
