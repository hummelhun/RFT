package Server.multithread;

import java.net.Socket;
import java.util.Vector;

public class ServerDispatcher extends Thread {
	String who;
	
	private Vector mMessageQueue = new Vector();
    private Vector mClients = new Vector();
 
 
    public synchronized void addClient(ClientInfo aClientInfo)
    {
        mClients.add(aClientInfo);
    }
 

    public synchronized void deleteClient(ClientInfo aClientInfo)
    {
        int clientIndex = mClients.indexOf(aClientInfo);
        if (clientIndex != -1)
           mClients.removeElementAt(clientIndex);
    }
 
    public synchronized void dispatchMessage(ClientInfo aClientInfo, String aMessage)
    {
        Socket socket = aClientInfo.mSocket;
        String senderIP = socket.getInetAddress().getHostAddress();
        String senderPort = "" + socket.getPort();
        //aMessage = senderIP + ":" + senderPort + " : " + aMessage;
        aMessage = aMessage;
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
 
    public void run()
    {
        try {
           while (true) {
               String message = getNextMessageFromQueue();
               if(message.contentEquals("LERAK")){
            	   System.out.println("Lerakva");
               }
               System.out.println("Message:"+message);
               sendMessageToAllClients(message);
           }
        } catch (InterruptedException ie) {
           // Thread interrupted. Stop its execution
        }
    }
 
}