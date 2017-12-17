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
		               Player player2=null;
		               //System.out.println(result[0]);
		               switch(result[0]){
		               case "PLAYER": {GameTableController.client.setPlayer(result[2]);} break;
		               }
		               
		               if(result.length>=4){
//		               System.out.println("result[2]: "+result[2]);
//		               System.out.println("result[6]: "+result[6]);
		               
		               if(Integer.parseInt(result[4]) == 1){
		            	   player = GameTableController.c.getPlayer1();
		               }else{player = GameTableController.c.getPlayer2();}
		               }
		          
		               switch(result[2]){
		               case "ENDTURN": {
		            	   if(Integer.parseInt(result[4]) == 1){
			            	   player = GameTableController.c.getPlayer2();
			               	}
		            	   else{
		            		   player = GameTableController.c.getPlayer1();
		            		   
		            	   }			               
		            	   Main.controller.endTurnButtonOtherSide(player, Integer.parseInt(result[4]));} break;
		            	   
		               case "ATTACK": {
		            	   if(Integer.parseInt(result[4]) == 1){
			            	   player = GameTableController.c.getPlayer2();
			            	   player2 = GameTableController.c.getPlayer1();
			               	}
		            	   else{
		            		   player = GameTableController.c.getPlayer1();
		            		   player2 = GameTableController.c.getPlayer2();
		            	   }		            	   
		            	   Main.controller.tradeOnOtherSide(player, player2, Integer.parseInt(result[6]), Integer.parseInt(result[8]));}break;
		               
		               case "HEROATTACK": {
		            	   if(Integer.parseInt(result[4]) == 1){
			            	   player = GameTableController.c.getPlayer2();
			               	}
		            	   else{
		            		   player = GameTableController.c.getPlayer1();
		            	   }
		            	   Main.controller.clickOnOpponentFaceOtherSide(player, Integer.parseInt(result[6]));}break;
		            	   
		               case "PUT": {Main.controller.putCardToTheBoard(player, Integer.parseInt(result[6]));}break;
		               }
		           }
				}
			
			}catch (IOException ioe) {
				System.out.println("duck");
			}
	}
}
