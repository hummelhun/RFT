package view;

import Client.Client;
import Client.ClientMain;
import Client.Sender;
import Client.Listener;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Client.Sender;
import cardGame.Core;
import cardGame.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import things.MinionCard;
import things.Player;

public class GameTableController {
	
	private Stage stage;
	public Image hatlap = new Image("cardback_0.png");
	public int choose1;

	
	Core c = new Core();
	public static Client client = new Client();
	Sender sender = new Sender();
	Listener listener = new Listener();
	
	public void refreshTheHandImages() {
	    for (int i = 0; i < c.getPlayer1().getHand().size(); i++) {		    	
	    	Image img2 = new Image(c.getPlayer1().getHand().get(i).getFileName()); 
			handImgs[i].setImage(img2);
		}
	    for (int i = c.getPlayer1().getHand().size(); i < 5; i++) {
	    	handImgs[i].setImage(hatlap);
//	    	handImgs[i].setOpacity(0);
		}
	}
	
	public void refreshBoardImages(Player player, ImageView[] imagearray) {
		for (int i = 0; i < player.getBoard().size(); i++) {
			Image img = new Image(player.getBoard().get(i).getFileName());
			boardImgsOpponent[i].setImage(img);
		}
		for (int i = player.getBoard().size(); i < 6; i++) {
			
			imagearray[i].setImage(hatlap);
		}
	}
	
	@FXML
	public void startGame() {
		System.out.println("ÉN VAGYOK A " + client.getPlayer() + " JÁTÉKOS!");
		c.startGame();
		startButton.setVisible(false);
		player1HealtText.setText(""+c.getPlayer1().getHealtPoint());
		player2HealtText.setText(""+c.getPlayer2().getHealtPoint());
		manaBar1.setText("Mana: "+c.getPlayer1().getActualMana()+"/ "+c.getPlayer1().getMana());
		manaBar2.setText("Mana: "+c.getPlayer2().getActualMana()+"/ "+c.getPlayer2().getMana());
		c.getPlayer1().getHand().get(0).getFileName();
		Image image = new Image(c.getPlayer1().getHand().get(0).getFileName());
		ownHand1.setImage(image);
		Image image2 = new Image(c.getPlayer1().getHand().get(1).getFileName());
		ownHand2.setImage(image2);
		Image image3 = new Image(c.getPlayer1().getHand().get(2).getFileName());
		ownHand3.setImage(image3);
		
		
		ownDeckCounter.setText("Cards left: "+c.getPlayer1().getDeck().size());
		ownDeckCounter2.setText("Cards left: "+c.getPlayer2().getDeck().size());
		handImgs[0]=ownHand1;
		handImgs[1]=ownHand2;
		handImgs[2]=ownHand3;
		handImgs[3]=ownHand4;
		handImgs[4]=ownHand5;
		
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
		
		
		refreshBoardImages(c.getPlayer2(), boardImgsOpponent);
		sender.setMassage("asd");
	
	}
	
	
	@FXML
	public void endTurnButton() {
		if (c.getActualPlayer()==0) {
			c.setActualPlayer(1);
			c.getPlayer2().setMana(c.getPlayer2().getMana()+1);
			c.getPlayer2().setActualMana(c.getPlayer2().getMana());
			manaBar2.setText("Mana: "+c.getPlayer2().getActualMana()+" /"+c.getPlayer2().getMana());
			System.out.println(c.getActualPlayer());
			//sender.mOut.println("ENTURNBIATCH");
			
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
				
				refreshTheHandImages();
			}	
		}
		
		
		
	}
	
	@FXML
	public void clickOnOwnHand1() {

		if (c.getPlayer1().getHand().get(0) != null && c.getPlayer1().getActualMana()>=c.getPlayer1().getHand().get(0).getManaCost()) {
			c.getPlayer1().getBoard().add(c.getPlayer1().getHand().get(0));
			for (MinionCard card : c.getPlayer1().getHand()) {
				System.out.println(card.getCardName());
			}
			System.out.println("----------");
			Image img = new Image(c.getPlayer1().getHand().get(0).getFileName());
			boardImgs[c.getPlayer1().getBoard().size() - 1].setImage(img);
			
			c.getPlayer1().setActualMana(c.getPlayer1().getActualMana()-c.getPlayer1().getHand().get(0).getManaCost());
			manaBar1.setText(""+c.getPlayer1().getActualMana()+"/"+c.getPlayer1().getMana());
			c.getPlayer1().getHand().remove(0);
			refreshTheHandImages();
			

		}
	}
	
	@FXML
	public void clickOnOwnHand2() {

		if (c.getPlayer1().getHand().get(1) != null && c.getPlayer1().getActualMana()>=c.getPlayer1().getHand().get(1).getManaCost()) {
			c.getPlayer1().getBoard().add(c.getPlayer1().getHand().get(1));
			for (MinionCard card : c.getPlayer1().getHand()) {
				System.out.println(card.getCardName());
			}
			System.out.println("----------");
			Image img = new Image(c.getPlayer1().getHand().get(1).getFileName());
			boardImgs[c.getPlayer1().getBoard().size() - 1].setImage(img);
			
			c.getPlayer1().setActualMana(c.getPlayer1().getActualMana()-c.getPlayer1().getHand().get(1).getManaCost());
			manaBar1.setText("Mana: "+c.getPlayer1().getActualMana()+"/ "+c.getPlayer1().getMana());
			
			c.getPlayer1().getHand().remove(1);
			refreshTheHandImages();
		}
	}
	@FXML
	public void clickOnOwnHand3() {
		
		if (c.getPlayer1().getHand().get(2) != null && c.getPlayer1().getActualMana()>=c.getPlayer1().getHand().get(2).getManaCost()) {
			c.getPlayer1().getBoard().add(c.getPlayer1().getHand().get(2));
			for (MinionCard card : c.getPlayer1().getHand()) {
				System.out.println(card.getCardName());
			}
			System.out.println("----------");			
			Image img = new Image(c.getPlayer1().getHand().get(2).getFileName());			
			boardImgs[c.getPlayer1().getBoard().size()-1].setImage(img);
			
			c.getPlayer1().setActualMana(c.getPlayer1().getActualMana()-c.getPlayer1().getHand().get(2).getManaCost());
			manaBar1.setText(""+c.getPlayer1().getActualMana()+"/"+c.getPlayer1().getMana());
			
			c.getPlayer1().getHand().remove(2);		    
			refreshTheHandImages();
		}
	}
	
	@FXML
	public void clickOnOwnHand4() {
		
		if (c.getPlayer1().getHand().get(3) != null && c.getPlayer1().getActualMana()>=c.getPlayer1().getHand().get(3).getManaCost()) {
			c.getPlayer1().getBoard().add(c.getPlayer1().getHand().get(3));
			for (MinionCard card : c.getPlayer1().getHand()) {
				System.out.println(card.getCardName());
			}
			System.out.println("----------");			
			Image img = new Image(c.getPlayer1().getHand().get(3).getFileName());			
			boardImgs[c.getPlayer1().getBoard().size()-1].setImage(img);
			
			c.getPlayer1().setActualMana(c.getPlayer1().getActualMana()-c.getPlayer1().getHand().get(3).getManaCost());
			manaBar1.setText(""+c.getPlayer1().getActualMana()+"/"+c.getPlayer1().getMana());
			
			c.getPlayer1().getHand().remove(3);		    
			refreshTheHandImages();
		}
	}
	
	@FXML
	public void clickOnOwnHand5() {
		
		if (c.getPlayer1().getHand().get(4) != null && c.getPlayer1().getActualMana()>=c.getPlayer1().getHand().get(4).getManaCost()) {
			c.getPlayer1().getBoard().add(c.getPlayer1().getHand().get(4));
			for (MinionCard card : c.getPlayer1().getHand()) {
				System.out.println(card.getCardName());
			}
			System.out.println("----------");			
			Image img = new Image(c.getPlayer1().getHand().get(4).getFileName());			
			boardImgs[c.getPlayer1().getBoard().size()-1].setImage(img);
			
			c.getPlayer1().setActualMana(c.getPlayer1().getActualMana()-c.getPlayer1().getHand().get(4).getManaCost());
			manaBar1.setText(""+c.getPlayer1().getActualMana()+"/"+c.getPlayer1().getMana());
			
			c.getPlayer1().getHand().remove(4);		    
			refreshTheHandImages();
			
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
	public void clickOnOpponentBoard1() {
		c.getPlayer1().getBoard().get(choose1).setHealthPoint(c.getPlayer1().getBoard().get(choose1).getHealthPoint()-c.getPlayer2().getBoard().get(0).getAttackPower());
		c.getPlayer2().getBoard().get(0).setHealthPoint(c.getPlayer2().getBoard().get(0).getHealthPoint()-c.getPlayer1().getBoard().get(choose1).getAttackPower());
		for (int i = 0; i < c.getPlayer1().getBoard().size(); i++) {
			if (c.getPlayer1().getBoard().get(i).getHealthPoint()<=0) {
				c.getPlayer1().getBoard().remove(i);
				
			}
		}
		for (int i = 0; i < c.getPlayer2().getBoard().size(); i++) {
			if (c.getPlayer2().getBoard().get(i).getHealthPoint()<=0) {
				c.getPlayer2().getBoard().remove(i);
				
			}
		}
		refreshBoardImages(c.getPlayer1(),boardImgs);
		refreshBoardImages(c.getPlayer2(),boardImgsOpponent);
		
		
	}
	@FXML
	public void clickOnOpponentFace() {
		c.getPlayer2().setHealtPoint(c.getPlayer2().getHealtPoint()-c.getPlayer1().getBoard().get(choose1).getAttackPower());
		player2HealtText.setText(""+c.getPlayer2().getHealtPoint());
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
	
	ImageView handImgs[]=new ImageView[5];
	ImageView boardImgs[]=new ImageView[6];
	ImageView boardImgsOpponent[]=new ImageView[6];
	
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
