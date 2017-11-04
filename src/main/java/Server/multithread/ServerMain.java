package Server.multithread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import cardGame.Core;
 
public class ServerMain {
	
	static int playerN=1;
	
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
	                clientInfo.player = playerN;
	                clientListener.start();
	                clientSender.start();
	                serverDispatcher.addClient(clientInfo);
	                if(playerN <2){
	                playerN++;
	                
	                }
	                System.out.println("player num: " + playerN);
	                System.out.println("Client connected on socket!");
	                
	                ///////////////////////////
	                //here comes the logic!
	                ///////////////////////////
	                
	                Core core = new Core(); 
	                
	            } catch (IOException ioe) {
	                ioe.printStackTrace();
	            }
	         }

	}
}
