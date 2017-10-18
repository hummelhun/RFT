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
import javafx.stage.Stage;
import things.MinionCard;

public class GameTableController {
	
	private Stage stage;
//	public Image hatlap = new Image("cardback_0.png");
	public ObservableList<MinionCard> pakli1 = FXCollections.observableArrayList();
	public MinionCard card1 = new MinionCard("Bloodfen Raptor", 2, "", "Bloodfen_Raptor.png",3, 2);
	
	public void startGame() {
		pakli1.add(card1);
		pakli1.add(card1);
		pakli1.add(card1);
	}
	
	public void 
	
	
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
	
	public Image bloodfen = new Image("Bloodfen_Raptor.png");
	
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
