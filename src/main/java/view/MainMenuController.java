package view;

import cardGame.Core;

import java.io.IOException;
import java.net.UnknownHostException;

import Client.Client;
import Client.ClientMain;
import cardGame.Main;
import javafx.fxml.FXML;

public class MainMenuController {
	private Main main;

	public MainMenuController() {
		
	}

	public MainMenuController(Main main) {
		this.main = main;
	}

	public void setMain(Main main) {
		this.main = main;
	}
	
	Core c = new Core();
	
	public void initData(Core core)	{
		this.c = core;		
	}
	
	@FXML
	private void handleNewGame() {
		System.out.println("New GAme Started");
		this.main.createGameTable(c);
		main.connectionAttempt();
		 
	}
	
	@FXML
	private void handleExitGame() {
		System.exit(0);
	}
		

}
