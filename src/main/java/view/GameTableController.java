package view;

import cardGame.Core;
import cardGame.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class GameTableController {
	
	private Stage stage;
//	public Image hatlap = new Image("cardback.png");
	@FXML
	public ImageView kartya1;
	public Button gomb;
	
	
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
		gomb.setText("LOL");
//		kartya1.setImage(hatlap);

		
	}
}
