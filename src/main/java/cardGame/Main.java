package cardGame;

import java.io.IOException;
import java.net.UnknownHostException;

import Client.Client;
import Client.Sender;
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
//		MinionCard card1 = new MinionCard("Bloodfen Raptor", 2, "", "Bloodfen_Raptor.png",3, 2);
//		System.out.println(card1.getCardName());
//		SpellCard card2 = new SpellCard("Arcane Intellect", 3, "Draw 2 card", "arcaneintellect.png");
//		System.out.println(card2.getCardName());
		System.out.println();

		launch(args);
		
	}
	
	public void connectionAttempt(){
		Client client = new Client("127.0.0.1", 8005);
		try {
			client.createClientSocket();
			client.clientInOut();
			client.clientStdIn();
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + client.getHostname());
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " + client.getHostname());
			System.exit(1);
		}
		 Sender sender = new Sender(client.getOut());
	        sender.setDaemon(true);
	        sender.start();
	}

}
