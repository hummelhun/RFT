package Server.multithread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import cardGame.Core;
 
public class ServerMain {
	
	volatile static int playerN=1;
	volatile static int connection=1;
	
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
	            	if(playerN == 0){
	            		System.out.println("set to basic");
	            		connection = 1;
	            		playerN = 1;
	            	}
	            	while(playerN == 2){ if (playerN<=1)break; System.out.println("que");};
	            	System.out.println("Waiting for players!");
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
	                System.out.println("player num: " + playerN);
	                System.out.println("Client connected on socket!");
	                
	                if(playerN <2){
	                playerN++;
	                }
	            	
	                
	            } catch (IOException ioe) {
	                ioe.printStackTrace();
	            }
	         }

	}
	synchronized public static void change(){
		ServerMain.playerN = playerN--;
	}
}
