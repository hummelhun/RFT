package Client;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;

import cardGame.Main;
import things.Player;
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
		               Player player=null;
		               //System.out.println(result[0]);
		               switch(result[0]){
		               case "PLAYER": {GameTableController.client.setPlayer(result[2]);} break;
		               }
		               
		               if(result.length>=4){
		               System.out.println("result[2]: "+result[2]);
		               System.out.println("result[4]: "+result[4]);
		               
		               if(Integer.parseInt(result[4]) == 1){
		            	   player = GameTableController.c.getPlayer1();
		               }else{player = GameTableController.c.getPlayer2();}
		               }
		          
		               switch(result[2]){
		               case "ENDTURN": {/*if(GameTableController.c.setActualPlayer();*/} break;
		               case "ATTACK": break;
		               case "ATTACKHERO": break;
		               case "PUT": {Main.controller.clickOnOwnHandWithIndex(player, Integer.parseInt(result[4]));
		               Main.controller.putCardToTheBoard(player, Integer.parseInt(result[4]));
		               }break;
		               }
		           }
				}
			
			}catch (IOException ioe) {
				System.out.println("duck");
			}
	}
}
