package Server.multithread;

import java.net.Socket;

import javax.swing.event.CaretListener;

import Client.Client;

public class ClientInfo
	{
	    public Socket mSocket = null;
	    public ClientListener mClientListener = null;
	    public ClientSender mClientSender = null;
	}
