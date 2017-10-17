package cardGame;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import things.MinionCard;
import things.Player;
import things.SpellCard;
import view.GameTableController;
import view.MainMenuController;


public class Main extends Application{
	
	public Stage primaryStage;
	public BorderPane mainMenu;
	
	public Main() {
	}
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	Core c = new Core();
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Game RFT");
		createMainMenu(c);


	}
	
	public void createMainMenu(Core core) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/fxml/MainMenu.fxml"));
			mainMenu = (BorderPane) loader.load();

			Scene scene = new Scene(mainMenu);

			MainMenuController controller = loader.getController();
			controller.initData(core);
			controller.setMain(this);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createGameTable(Core core) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/fxml/GameTable.fxml"));
			AnchorPane pane;
			pane = (AnchorPane) loader.load();

			mainMenu.setCenter(pane);

			GameTableController controller = loader.getController();
			controller.initData(core);
			controller.setMain(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		MinionCard card1 = new MinionCard("Bloodfen Raptor", 2, "", "bloodfenraptor.png",3, 2);
		System.out.println(card1.getCardName());
		SpellCard card2 = new SpellCard("Arcane Intellect", 3, "Draw 2 card", "arcaneintellect.png");
		System.out.println(card2.getCardName());
		MinionCard[] deck1 = new MinionCard[2];
		deck1[0]= card1;
		deck1[1]= card1;
		
		Player player1 = new Player("player1", deck1, 0) ;
		launch(args);
		
	}

}
