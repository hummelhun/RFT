package view;

import java.util.ArrayList;
import java.util.List;

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

public class GameTableController {
	
	private Stage stage;
//	public Image hatlap = new Image("cardback_0.png");
//	public ObservableList<MinionCard> pakli1 = FXCollections.observableArrayList();
//	public ObservableList<MinionCard> hand1 = FXCollections.observableArrayList();
//	public MinionCard bloodfenRaptor = new MinionCard("Bloodfen Raptor", 2, "", "Bloodfen_Raptor.png",3, 2, 0);
//	public MinionCard murlocRaider = new MinionCard("Murloc Raider", 1, "", "Murloc_Raider.png", 2, 1, 0);
	
	@FXML
	public void startGame() {
		c.startGame();
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
		
		startButton.setVisible(false);
		ownDeckCounter.setText("Cards left: "+c.getPlayer1().getDeck().size());
		ownDeckCounter2.setText("Cards left: "+c.getPlayer2().getDeck().size());
		
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
	
	Core c = new Core();
	
	
	public void initData(Core core) {
		this.c=core;
		
		
	}
	
	
}
