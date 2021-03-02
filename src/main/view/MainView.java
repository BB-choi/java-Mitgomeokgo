package main.view;

import common.CommonLocation;
import common.CommonView;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainView extends Application{
static CommonView cv;
static CommonLocation cl;

	public static void main(String[] args) throws Exception {
		cl = new CommonLocation();
		cl.getCity();

		launch(args);
	}
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		cv = new CommonView();
		cv.FormLoad(primaryStage, "../main/view/mainView.fxml", "¹Ï°í¸Ô°í");
		
		setCity(primaryStage);
	}

	private void setCity(Stage primaryStage) {
		AnchorPane aPane = (AnchorPane) primaryStage.getScene().getRoot();
		Label locationLbl = (Label) aPane.lookup("#locationLbl");
		cl.setCity(locationLbl);
		
	}

}
