package Server.multithread;

import java.net.Socket;
import java.util.Vector;

public class ServerDispatcher extends Thread {
	
	private Vector mMessageQueue = new Vector();
    private Vector mClients = new Vector();
    public int playerN = 0;
    
    public synchronized void addClient(ClientInfo aClientInfo)
    {
    	String message ="";
        mClients.add(aClientInfo);
        message = "PLAYER | " + (playerN); 
        ClientInfo clientInfo = (ClientInfo) mClients.get(playerN);
        sendMessageToPlayer(message, clientInfo);
        playerN++;
    }
 

    public synchronized void deleteClient(ClientInfo aClientInfo)
    {
        int clientIndex = mClients.indexOf(aClientInfo);
        if (clientIndex != -1)
           mClients.removeElementAt(clientIndex);
        
        System.out.println("Client Disconnected");
    }
 
    public synchronized void dispatchMessage(ClientInfo aClientInfo, String aMessage)
    {
        Socket socket = aClientInfo.mSocket;
        String senderIP = socket.getInetAddress().getHostAddress();
        String senderPort = "" + socket.getPort();
        //aMessage = senderIP + ":" + senderPort + " : " + aMessage;
        aMessage = aClientInfo.player + " | " + aMessage;
        mMessageQueue.add(aMessage);
        System.out.println(aMessage);
        notify();
    }
 
    private synchronized String getNextMessageFromQueue()
    throws InterruptedException
    {
        while (mMessageQueue.size()==0)
           wait();
        String message = (String) mMessageQueue.get(0);
        mMessageQueue.removeElementAt(0);
        return message;
    }
 
    private synchronized void sendMessageToAllClients(String aMessage)
    {
        for (int i=0; i<mClients.size(); i++) {
           ClientInfo clientInfo = (ClientInfo) mClients.get(i);
           clientInfo.mClientSender.sendMessage(aMessage);
        }
    }
    
    private synchronized void sendMessageToPlayer(String aMessage, ClientInfo clientInfo)
    {
           clientInfo.mClientSender.sendMessage(aMessage);
    }
 
    public void run()
    {
        try {
      
        	while (true) {
        		if(playerN > 1) {
        	String message = getNextMessageFromQueue();
        	String[] result = message.split("|");
            System.out.println("result 0. element: "+result[0]);
        	if(result[0].contentEquals("1")){
        		ClientInfo clientInfo = (ClientInfo) mClients.get(1);
                sendMessageToPlayer(message,clientInfo);        	}
        	if(result[0].contentEquals("2")){
        		ClientInfo clientInfo = (ClientInfo) mClients.get(0);
        		 sendMessageToPlayer(message,clientInfo);
        	}
        	}
        	}
        } catch (InterruptedException ie) {
           // Thread interrupted. Stop its execution
        }
    }
 
}