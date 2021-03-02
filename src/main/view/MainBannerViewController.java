package main.view;

//import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import common.CommonView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;

public class MainBannerViewController implements Initializable{
@FXML JFXButton ban1;
@FXML JFXButton ban2;
CommonView cv;
String infoFxml = "../main/info/view/info.fxml";

	public MainBannerViewController() {
		cv = new CommonView();
	}


	public void OnBanner1() {
		cv.PopupHandleUrl(infoFxml, "위생등급제란?", "info1.html");
	}
	public void OnBanner2() {
		cv.PopupHandleUrl(infoFxml, "코로나 안심식당이란?","https://blog.naver.com/PostPrint.nhn?blogId=mifaffgov&logNo=222153266936");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ban1.cursorProperty().set(Cursor.HAND);
		ban2.cursorProperty().set(Cursor.HAND);
		
	}
}
