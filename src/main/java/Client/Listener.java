package Client;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;

import cardGame.Main;
import view.GameTableController;

public class Listener extends Thread {
	public BufferedReader mIn;
	public Client mClient = new Client();
	public Listener(BufferedReader aIn, Client aClient) {
		mIn = aIn;
		mClient = aClient;
	}
	public Listener(){
		
	}

	public void run() {
		 try{

				while (!isInterrupted()) {	
					// Read messages from the server and print them
		            String message;
		           while ((message=mClient.in.readLine()) != null) {
		        	   
		        	   String[] result = message.split(" | ");
		               System.out.println(message);
		               System.out.println(result[0]);
		               switch(result[0]){
		               case "PLAYER": {GameTableController.client.setPlayer(result[2]);} break;
		               }
		           }
				}
			
			}catch (IOException ioe) {
				System.out.println("duck");
			}
	}
}
