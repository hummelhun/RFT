package view;

import Client.Client;
import Client.ClientMain;
import Client.Sender;
import Server.multithread.ClientListener;
import Client.Listener;
import java.io.IOException;


import Client.Sender;
import cardGame.Core;
import cardGame.Main;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import things.Player;

public class GameTableController {
	
	private Stage stage;
	public Image hatlap = new Image("cardback_0.png");
	public int choose1;
	public volatile static String player;
	int min =0 ;

	Core c = new Core();
	public static Client client = new Client();
	Sender sender = new Sender();
	Listener listener = new Listener();
	

	private void removeDeadMinionsFromTheBoard(Player player) {
		for (int i = 0; i < player.getBoard().size(); i++) {
			if (player.getBoard().get(i).getHealthPoint()<=0) {
				player.getBoard().remove(i);				
			}
		}
	}	
	private void clickOnOwnHandWithIndex(Player player, int index) {
		if (player.getHand().get(index) != null && player.getActualMana()>=player.getHand().get(index).getManaCost()) {
			player.getBoard().add(player.getHand().get(index));
			Image img = new Image(player.getHand().get(index).getFileName());
			boardImgs[player.getBoard().size() - 1].setImage(img);
			
			player.setActualMana(player.getActualMana()-player.getHand().get(index).getManaCost());
			manaBar1.setText("Mana: "+player.getActualMana()+"/"+player.getMana());
			player.getHand().remove(index);
			
			refreshTheHandImages(player);
			refreshBoardImages(player, boardImgs);
			refreshMinionBars(player, ownHandMinionBars);			
			refreshBoardRectangles(player);
			
		}
	}
	private void clickOnOpponentBoardWithIndex(Player player1, Player player2, int boardIndex) throws InterruptedException {
		if (player1.getBoard().get(choose1).getAttackNow() == 1) {
			player1.getBoard().get(choose1).setHealthPoint(player1.getBoard().get(choose1).getHealthPoint()- player2.getBoard().get(boardIndex).getAttackPower());
			player2.getBoard().get(boardIndex).setHealthPoint(player2.getBoard().get(boardIndex).getHealthPoint()- player1.getBoard().get(choose1).getAttackPower());
			player1.getBoard().get(choose1).setAttackNow(0);
			
			
			removeDeadMinionsFromTheBoard(player1);
			removeDeadMinionsFromTheBoard(player2);

			refreshBoardImages(player1, boardImgs);
			refreshBoardImages(player2, boardImgsOpponent);
			

			refreshMinionBars(player1, ownHandMinionBars);
			refreshMinionBars(player2, opponentHandMinionBars);
			
			refreshBoardRectangles(player1);
		}
	}
	public void refreshTheHandImages(Player player) {
	    for (int i = 0; i < player.getHand().size(); i++) {		    	
	    	Image img = new Image(player.getHand().get(i).getFileName()); 
			handImgs[i].setImage(img);
			handImgs[i].setVisible(true);
			
		}
	    for (int i = player.getHand().size(); i < 5; i++) {
	    	handImgs[i].setImage(hatlap);
	    	handImgs[i].setVisible(false);
		}
	}	
	public void refreshBoardImages(Player player, ImageView[] imagearray) {
		for (int i = 0; i < player.getBoard().size(); i++) {
			Image img = new Image(player.getBoard().get(i).getFileName());
			imagearray[i].setImage(img);
			imagearray[i].setVisible(true);
			
		}
		for (int i = player.getBoard().size(); i < 6; i++) {			
//			imagearray[i].setImage(hatlap);
			imagearray[i].setVisible(false);
		}
	}	
	private void refreshMinionBars(Player player, Text[] textArray) {
		for (int i = 0; i < player.getBoard().size(); i++) {
			textArray[i].setText("    "+player.getBoard().get(i).getAttackPower()+"       "+player.getBoard().get(i).getHealthPoint());			
		}
		for (int i = player.getBoard().size(); i < 6; i++) {			
			textArray[i].setText("");
		}
	}	
	private void refreshBoardRectangles(Player player) {		
		for (int i = 0; i < player.getBoard().size(); i++) {
			if (player.getBoard().get(i).getAttackNow()==1) {
				boardRectangles[i].setStroke(Color.GREEN);
			}else {
				boardRectangles[i].setStroke(Color.RED);
				
			}
		}
//		for (int i = player.getBoard().size(); i < 6; i++) {
//			boardRectangles[i].setVisible(false);
//		}
	}
	
	@FXML
	public void startGame() {
		System.out.println("ÉN VAGYOK A " + client.getPlayer() + " JÁTÉKOS!");
		c.startGame();
		startButton.setVisible(false);
		IntegerProperty i = new SimpleIntegerProperty(0);	
		Timeline timeline = new Timeline(
		    new KeyFrame(Duration.seconds(1),
		        event -> {		        	
		            i.set(i.get() + 1);
		            if(i.get()==60) {
		            	min++;
		            	i.set(0);
		            }
		            if(i.get()<10) {
		            	timeElapse.setText("0"+min+":0" + i.get());
		            }else
		            	timeElapse.setText("0"+min+":" + i.get());
		        } 
		    )
		);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		
		
		if(Integer.parseInt(client.getPlayer())==1) {

			playerCode.setText(""+client.getPlayer());
			player1HealtText.setText(""+c.getPlayer1().getHealtPoint());
			player2HealtText.setText(""+c.getPlayer2().getHealtPoint());
			manaBar1.setText("Mana: "+c.getPlayer1().getActualMana()+"/ "+c.getPlayer1().getMana());
			manaBar2.setText("Mana: "+c.getPlayer2().getActualMana()+"/ "+c.getPlayer2().getMana());
			
			Image image = new Image(c.getPlayer1().getHand().get(0).getFileName());
			ownHand1.setImage(image);
			image = new Image(c.getPlayer1().getHand().get(1).getFileName());
			ownHand2.setImage(image);
			image = new Image(c.getPlayer1().getHand().get(2).getFileName());
			ownHand3.setImage(image);
			
			image = new Image(c.getPlayer1().getHeroFileName());
			heroFace.setImage(image);
			image = new Image(c.getPlayer2().getHeroFileName());
			opponentHeroFace.setImage(image);
			
			ownDeckCounter.setText("Cards left: "+c.getPlayer1().getDeck().size());
			ownDeckCounter2.setText("Cards left: "+c.getPlayer2().getDeck().size());
			
			handImgs[0]=ownHand1;
			handImgs[1]=ownHand2;
			handImgs[2]=ownHand3;
			handImgs[3]=ownHand4;
			handImgs[4]=ownHand5;
			
//			refreshTheHandImages(c.getPlayer2());
			refreshTheHandImages(c.getPlayer1());
			
			boardImgs[0]=ownBoard1;
			boardImgs[1]=ownBoard2;
			boardImgs[2]=ownBoard3;
			boardImgs[3]=ownBoard4;
			boardImgs[4]=ownBoard5;
			boardImgs[5]=ownBoard6;
			
			boardImgsOpponent[0]=opponentBoard1;
			boardImgsOpponent[1]=opponentBoard2;
			boardImgsOpponent[2]=opponentBoard3;
			boardImgsOpponent[3]=opponentBoard4;
			boardImgsOpponent[4]=opponentBoard5;
			boardImgsOpponent[5]=opponentBoard6;
			
			refreshBoardImages(c.getPlayer1(), boardImgs);
			refreshBoardImages(c.getPlayer2(), boardImgsOpponent);
			
			ownHandMinionBars[0]=ownHandMinionBar1;
			ownHandMinionBars[1]=ownHandMinionBar2;
			ownHandMinionBars[2]=ownHandMinionBar3;
			ownHandMinionBars[3]=ownHandMinionBar4;
			ownHandMinionBars[4]=ownHandMinionBar5;
			ownHandMinionBars[5]=ownHandMinionBar6;
			
			opponentHandMinionBars[0]=opponentHandMinionBar1;
			opponentHandMinionBars[1]=opponentHandMinionBar2;
			opponentHandMinionBars[2]=opponentHandMinionBar3;
			opponentHandMinionBars[3]=opponentHandMinionBar4;
			opponentHandMinionBars[4]=opponentHandMinionBar5;
			opponentHandMinionBars[5]=opponentHandMinionBar6;
			
			refreshMinionBars(c.getPlayer1(), ownHandMinionBars);
			refreshMinionBars(c.getPlayer2(), opponentHandMinionBars);
			
			boardRectangles[0]=boardRectangle1;
			boardRectangles[1]=boardRectangle2;
			boardRectangles[2]=boardRectangle3;
			boardRectangles[3]=boardRectangle4;
			boardRectangles[4]=boardRectangle5;
			boardRectangles[5]=boardRectangle6;
			refreshBoardRectangles(c.getPlayer1());
			
		
			
		}

		if(Integer.parseInt(client.getPlayer())==2) {

			playerCode.setText(""+client.getPlayer());
			player1HealtText.setText(""+c.getPlayer2().getHealtPoint());
			player2HealtText.setText(""+c.getPlayer1().getHealtPoint());
			manaBar1.setText("Mana: "+c.getPlayer2().getActualMana()+"/ "+c.getPlayer2().getMana());
			manaBar2.setText("Mana: "+c.getPlayer1().getActualMana()+"/ "+c.getPlayer1().getMana());
			
			Image image = new Image(c.getPlayer2().getHand().get(0).getFileName());
			ownHand1.setImage(image);
			Image image2 = new Image(c.getPlayer2().getHand().get(1).getFileName());
			ownHand2.setImage(image2);
			Image image3 = new Image(c.getPlayer2().getHand().get(2).getFileName());
			ownHand3.setImage(image3);
			
			Image image4 = new Image(c.getPlayer2().getHeroFileName());
			heroFace.setImage(image4);
			Image image5 = new Image(c.getPlayer1().getHeroFileName());
			opponentHeroFace.setImage(image5);
			
			ownDeckCounter.setText("Cards left: "+c.getPlayer2().getDeck().size());
			ownDeckCounter2.setText("Cards left: "+c.getPlayer1().getDeck().size());
			
			handImgs[0]=ownHand1;
			handImgs[1]=ownHand2;
			handImgs[2]=ownHand3;
			handImgs[3]=ownHand4;
			handImgs[4]=ownHand5;
			
//			refreshTheHandImages(c.getPlayer1());
			refreshTheHandImages(c.getPlayer2());
			
			boardImgs[0]=ownBoard1;
			boardImgs[1]=ownBoard2;
			boardImgs[2]=ownBoard3;
			boardImgs[3]=ownBoard4;
			boardImgs[4]=ownBoard5;
			boardImgs[5]=ownBoard6;
			
			boardImgsOpponent[0]=opponentBoard1;
			boardImgsOpponent[1]=opponentBoard2;
			boardImgsOpponent[2]=opponentBoard3;
			boardImgsOpponent[3]=opponentBoard4;
			boardImgsOpponent[4]=opponentBoard5;
			boardImgsOpponent[5]=opponentBoard6;
			
			refreshBoardImages(c.getPlayer1(), boardImgsOpponent);
			refreshBoardImages(c.getPlayer2(), boardImgs);
			
			ownHandMinionBars[0]=ownHandMinionBar1;
			ownHandMinionBars[1]=ownHandMinionBar2;
			ownHandMinionBars[2]=ownHandMinionBar3;
			ownHandMinionBars[3]=ownHandMinionBar4;
			ownHandMinionBars[4]=ownHandMinionBar5;
			ownHandMinionBars[5]=ownHandMinionBar6;
			
			opponentHandMinionBars[0]=opponentHandMinionBar1;
			opponentHandMinionBars[1]=opponentHandMinionBar2;
			opponentHandMinionBars[2]=opponentHandMinionBar3;
			opponentHandMinionBars[3]=opponentHandMinionBar4;
			opponentHandMinionBars[4]=opponentHandMinionBar5;
			opponentHandMinionBars[5]=opponentHandMinionBar6;
			
			refreshMinionBars(c.getPlayer2(), ownHandMinionBars);
			refreshMinionBars(c.getPlayer1(), opponentHandMinionBars);
			
			boardRectangles[0]=boardRectangle1;
			boardRectangles[1]=boardRectangle2;
			boardRectangles[2]=boardRectangle3;
			boardRectangles[3]=boardRectangle4;
			boardRectangles[4]=boardRectangle5;
			boardRectangles[5]=boardRectangle6;
			
			refreshBoardRectangles(c.getPlayer2());
		}
	}
	
	
	@FXML
	public void endTurnButton() {
//		if(Integer.parseInt(client.getPlayer())==1) {//&& c.getActualPlayer()==0
//			c.setActualPlayer(1);
//			
//		}
//		if(Integer.parseInt(client.getPlayer())==2) {// && c.getActualPlayer()==1
//			c.setActualPlayer(0);		
//			
//		}
		
		if (c.getActualPlayer()==0) {
			c.setActualPlayer(1);
			c.getPlayer2().setMana(c.getPlayer2().getMana()+1);
			c.getPlayer2().setActualMana(c.getPlayer2().getMana());
			manaBar2.setText("Mana: "+c.getPlayer2().getActualMana()+" /"+c.getPlayer2().getMana());
			System.out.println(c.getActualPlayer());
			c.getPlayer2().getHand().add(c.getPlayer2().getDeck().get(0));
			c.getPlayer2().getDeck().remove(0);
			refreshTheHandImages(c.getPlayer2());
			//sender.mOut.println("ENTURNBIATCH");
			for (int i = 0; i < c.getPlayer2().getBoard().size(); i++) {
				c.getPlayer2().getBoard().get(i).setAttackNow(1);
			}
			refreshBoardRectangles(c.getPlayer2());
			
			
		} else {
			if (c.getActualPlayer()==1) {
				c.setActualPlayer(0);
				c.getPlayer1().setMana(c.getPlayer1().getMana()+1);
				c.getPlayer1().setActualMana(c.getPlayer1().getMana());
				manaBar1.setText("Mana: "+c.getPlayer1().getActualMana()+" /"+c.getPlayer1().getMana());
				System.out.println(c.getActualPlayer());
				c.getPlayer1().getHand().add(c.getPlayer1().getDeck().get(0));
				c.getPlayer1().getDeck().remove(0);
				ownDeckCounter.setText("Cards left: "+c.getPlayer1().getDeck().size());
				
				refreshTheHandImages(c.getPlayer1());
				
				for (int i = 0; i < c.getPlayer1().getBoard().size(); i++) {
					c.getPlayer1().getBoard().get(i).setAttackNow(1);
				}
				refreshBoardRectangles(c.getPlayer1());
			}	
		}
		
		
		
	}
	
	@FXML
	public void clickOnOwnHand1() throws InterruptedException {		
		if(Integer.parseInt(client.getPlayer())==1) {
			clickOnOwnHandWithIndex(c.getPlayer1(), 0);
		
		}
		
		if(Integer.parseInt(client.getPlayer())==2) {
			clickOnOwnHandWithIndex(c.getPlayer2(), 0);
		}
	}
	
	@FXML
	public void clickOnOwnHand2() {
		if(Integer.parseInt(client.getPlayer())==1) {
			clickOnOwnHandWithIndex(c.getPlayer1(), 1);			
		}
		
		if(Integer.parseInt(client.getPlayer())==2) {
			clickOnOwnHandWithIndex(c.getPlayer2(), 1);
		}
	}
	@FXML
	public void clickOnOwnHand3() {		
		if(Integer.parseInt(client.getPlayer())==1) {
			clickOnOwnHandWithIndex(c.getPlayer1(), 2);		
		}
		
		if(Integer.parseInt(client.getPlayer())==2) {
			clickOnOwnHandWithIndex(c.getPlayer2(), 2);
		}
	}
	
	@FXML
	public void clickOnOwnHand4() {		
		if(Integer.parseInt(client.getPlayer())==1) {
			clickOnOwnHandWithIndex(c.getPlayer1(), 3);			
		}
		
		if(Integer.parseInt(client.getPlayer())==2) {
			clickOnOwnHandWithIndex(c.getPlayer2(), 3);
		}
	}
	
	@FXML
	public void clickOnOwnHand5() {		
		if(Integer.parseInt(client.getPlayer())==1) {
			clickOnOwnHandWithIndex(c.getPlayer1(), 4);			
		}
		
		if(Integer.parseInt(client.getPlayer())==2) {
			clickOnOwnHandWithIndex(c.getPlayer2(), 4);
		}
	}
	
	@FXML
	public void clickOnBoard1() {
		choose1=0;
	}
	@FXML
	public void clickOnBoard2() {
		choose1=1;
	}
	@FXML
	public void clickOnBoard3() {
		choose1=2;
	}
	@FXML
	public void clickOnBoard4() {
		choose1=3;
	}
	@FXML
	public void clickOnBoard5() {
		choose1=4;
	}
	@FXML
	public void clickOnBoard6() {
		choose1=5;
	}
	
	@FXML
	public void clickOnOpponentBoard1() throws InterruptedException {		
		if(Integer.parseInt(client.getPlayer())==1) {
			clickOnOpponentBoardWithIndex(c.getPlayer1(), c.getPlayer2(), 0);
			
		}		
		if(Integer.parseInt(client.getPlayer())==2) {
			clickOnOpponentBoardWithIndex(c.getPlayer2(), c.getPlayer1(), 0);
		}	
			
	}
	@FXML
	public void clickOnOpponentBoard2() throws InterruptedException {		
		if(Integer.parseInt(client.getPlayer())==1) {
			clickOnOpponentBoardWithIndex(c.getPlayer1(), c.getPlayer2(), 1);
		}		
		if(Integer.parseInt(client.getPlayer())==2) {
			clickOnOpponentBoardWithIndex(c.getPlayer2(), c.getPlayer1(), 1);
		}	
		
	}
	@FXML
	public void clickOnOpponentBoard3() throws InterruptedException {		
		if(Integer.parseInt(client.getPlayer())==1) {
			clickOnOpponentBoardWithIndex(c.getPlayer1(), c.getPlayer2(), 2);
		}		
		if(Integer.parseInt(client.getPlayer())==2) {
			clickOnOpponentBoardWithIndex(c.getPlayer2(), c.getPlayer1(), 2);
		}		
	}
	@FXML
	public void clickOnOpponentBoard4() throws InterruptedException {		
		if(Integer.parseInt(client.getPlayer())==1) {
			clickOnOpponentBoardWithIndex(c.getPlayer1(), c.getPlayer2(), 3);
		}		
		if(Integer.parseInt(client.getPlayer())==2) {
			clickOnOpponentBoardWithIndex(c.getPlayer2(), c.getPlayer1(), 3);
		}		
	}
	@FXML
	public void clickOnOpponentBoard5() throws InterruptedException {		
		if(Integer.parseInt(client.getPlayer())==1) {
			clickOnOpponentBoardWithIndex(c.getPlayer1(), c.getPlayer2(), 4);
		}		
		if(Integer.parseInt(client.getPlayer())==2) {
			clickOnOpponentBoardWithIndex(c.getPlayer2(), c.getPlayer1(), 4);
		}		
	}
	@FXML
	public void clickOnOpponentBoard6() throws InterruptedException {		
		if(Integer.parseInt(client.getPlayer())==1) {
			clickOnOpponentBoardWithIndex(c.getPlayer1(), c.getPlayer2(), 5);
		}		
		if(Integer.parseInt(client.getPlayer())==2) {
			clickOnOpponentBoardWithIndex(c.getPlayer2(), c.getPlayer1(), 5);
		}		
	}
	@FXML
	public void clickOnOpponentFace() {
		if(Integer.parseInt(client.getPlayer())==1) {
			if(c.getPlayer1().getBoard().get(choose1).getAttackNow()==1) {
			c.getPlayer2().setHealtPoint(c.getPlayer2().getHealtPoint()-c.getPlayer1().getBoard().get(choose1).getAttackPower());
			c.getPlayer1().getBoard().get(choose1).setAttackNow(0);
			player2HealtText.setText(""+c.getPlayer2().getHealtPoint());
			refreshBoardRectangles(c.getPlayer1());

			}
			
		}
		
		if(Integer.parseInt(client.getPlayer())==2) {
			if(c.getPlayer2().getBoard().get(choose1).getAttackNow()==1) {
			c.getPlayer1().setHealtPoint(c.getPlayer1().getHealtPoint()-c.getPlayer2().getBoard().get(choose1).getAttackPower());
			c.getPlayer2().getBoard().get(choose1).setAttackNow(0);
			player2HealtText.setText(""+c.getPlayer1().getHealtPoint());
			refreshBoardRectangles(c.getPlayer1());
			

			}
		}

	}
	
	@FXML
	public ImageView ownHand1;
	@FXML
	public ImageView ownHand2;
	@FXML
	public ImageView ownHand3;
	@FXML
	public ImageView ownHand4;
	@FXML
	public ImageView ownHand5;
	
	@FXML
	public Text ownHandMinionBar1;
	@FXML
	public Text ownHandMinionBar2;
	@FXML
	public Text ownHandMinionBar3;
	@FXML
	public Text ownHandMinionBar4;
	@FXML
	public Text ownHandMinionBar5;
	@FXML
	public Text ownHandMinionBar6;
	@FXML
	public Text opponentHandMinionBar1;
	@FXML
	public Text opponentHandMinionBar2;
	@FXML
	public Text opponentHandMinionBar3;
	@FXML
	public Text opponentHandMinionBar4;
	@FXML
	public Text opponentHandMinionBar5;
	@FXML
	public Text opponentHandMinionBar6;
	@FXML
	public Text timeElapse;
	@FXML
	public Rectangle boardRectangle1;
	@FXML
	public Rectangle boardRectangle2;
	@FXML
	public Rectangle boardRectangle3;
	@FXML
	public Rectangle boardRectangle4;
	@FXML
	public Rectangle boardRectangle5;
	@FXML
	public Rectangle boardRectangle6;
	
	
	public ImageView handImgs[]=new ImageView[5];
	public ImageView boardImgs[]=new ImageView[6];
	public ImageView boardImgsOpponent[]=new ImageView[6];
	
	public Text ownHandMinionBars[] = new Text[6];
	public Text opponentHandMinionBars[] = new Text[6];
	public Rectangle boardRectangles[] = new Rectangle[6];
	
	
	@FXML
	public ImageView ownBoard1;
	@FXML
	public ImageView ownBoard2;
	@FXML
	public ImageView ownBoard3;
	@FXML
	public ImageView ownBoard4;
	@FXML
	public ImageView ownBoard5;
	@FXML
	public ImageView ownBoard6;
		
	@FXML
	public ImageView heroFace;
	@FXML
	public ImageView opponentHeroFace;
	
	@FXML
	public ImageView opponentBoard1;
	@FXML
	public ImageView opponentBoard2;
	@FXML
	public ImageView opponentBoard3;
	@FXML
	public ImageView opponentBoard4;
	@FXML
	public ImageView opponentBoard5;
	@FXML
	public ImageView opponentBoard6;
	
	@FXML 
	public Text ownDeckCounter; 
	@FXML 
	public Text ownDeckCounter2;
	
	public Image bloodfen = new Image("Bloodfen_Raptor.png");
	
	@FXML
	Button startButton = new Button();
	
	@FXML
	Text player1HealtText;
	
	@FXML
	Text player2HealtText;
	
	@FXML
	Text manaBar1;
	
	@FXML
	Text manaBar2;
	
	@FXML
	Text playerCode;
	
	@FXML
	public void megvaltoztat() {
		ownHand1.setImage(bloodfen);	
	}
	@FXML
	public void feltolOH1() {
		ownHand1.setLayoutY(ownHand1.getLayoutY()-150);
		ownHand1.setFitHeight(ownHand1.getFitHeight()+100);
		ownHand1.setFitWidth(ownHand1.getFitWidth()+100);
	}
	@FXML
	public void letolOH1() {
		ownHand1.setLayoutY(ownHand1.getLayoutY()+150);
		ownHand1.setFitHeight(ownHand1.getFitHeight()-100);
		ownHand1.setFitWidth(ownHand1.getFitWidth()-100);
	}
	@FXML
	public void feltolOH2() {
		ownHand2.setLayoutY(ownHand2.getLayoutY()-150);
		ownHand2.setFitHeight(ownHand2.getFitHeight()+100);
		ownHand2.setFitWidth(ownHand2.getFitWidth()+100);
	}
	@FXML
	public void letolOH2() {
		ownHand2.setLayoutY(ownHand2.getLayoutY()+150);
		ownHand2.setFitHeight(ownHand2.getFitHeight()-100);
		ownHand2.setFitWidth(ownHand2.getFitWidth()-100);
	}
	@FXML
	public void feltolOH3() {
		ownHand3.setLayoutY(ownHand3.getLayoutY()-150);
		ownHand3.setFitHeight(ownHand3.getFitHeight()+100);
		ownHand3.setFitWidth(ownHand3.getFitWidth()+100);
	}
	@FXML
	public void letolOH3() {
		ownHand3.setLayoutY(ownHand3.getLayoutY()+150);
		ownHand3.setFitHeight(ownHand3.getFitHeight()-100);
		ownHand3.setFitWidth(ownHand3.getFitWidth()-100);
	}
	@FXML
	public void feltolOH4() {
		ownHand4.setLayoutY(ownHand4.getLayoutY()-150);
		ownHand4.setFitHeight(ownHand4.getFitHeight()+100);
		ownHand4.setFitWidth(ownHand4.getFitWidth()+100);
	}
	@FXML
	public void letolOH4() {
		ownHand4.setLayoutY(ownHand4.getLayoutY()+150);
		ownHand4.setFitHeight(ownHand4.getFitHeight()-100);
		ownHand4.setFitWidth(ownHand4.getFitWidth()-100);
	}
	@FXML
	public void feltolOH5() {
		ownHand5.setLayoutY(ownHand5.getLayoutY()-150);
		ownHand5.setFitHeight(ownHand5.getFitHeight()+100);
		ownHand5.setFitWidth(ownHand5.getFitWidth()+100);
	}
	@FXML
	public void letolOH5() {
		ownHand5.setLayoutY(ownHand5.getLayoutY()+150);
		ownHand5.setFitHeight(ownHand5.getFitHeight()-100);
		ownHand5.setFitWidth(ownHand5.getFitWidth()-100);
	}
	
	public void SetMain(Main main) {
		
	}
	public void setMain(Main main) {
	}
	public Stage getStage() {
		return this.stage;
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void initData(Core core,Client client, Sender sender) {
		this.c=core;
		this.sender=sender;
		this.client=client;	        
	}	
}
