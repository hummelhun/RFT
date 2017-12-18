package Server.multithread;

import java.io.*;
import java.net.*;
 
public class ClientListener extends Thread
{
    private ServerDispatcher mServerDispatcher;
    private ClientInfo mClientInfo;
    private BufferedReader mIn;
 
    public ClientListener(ClientInfo aClientInfo, ServerDispatcher aServerDispatcher)
    throws IOException
    {
        mClientInfo = aClientInfo;
        mServerDispatcher = aServerDispatcher;
        Socket socket = aClientInfo.mSocket;
        mIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
 
    public void run()
    {
        try {
           while (!isInterrupted()) {
               String message = mIn.readLine();
               if (message == null)
                   break;
               mServerDispatcher.dispatchMessage(mClientInfo, message);
               if(ServerMain.connection==0){
            	   System.out.println("ANYAD!");
            	  throw new IOException();
               }
           }
           
        } catch (IOException ioex) {
          System.out.println("shit happened!"); // Problem reading from socket (communication is broken)
        } 
 
        // Communication is broken. Interrupt both listener and sender threads
        ServerMain.connection=0;
        ServerMain.playerN--;
        System.out.println("Online players:" + ServerMain.playerN);
        System.out.println("Server connection state:" + ServerMain.connection);
        mClientInfo.mClientSender.interrupt();
        mServerDispatcher.deleteClient(mClientInfo);
    }
 
}