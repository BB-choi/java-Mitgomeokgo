package main.info.view;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class InfoController{
	@FXML WebView wView;

	public void setView(String url) {
		WebEngine engine = wView.getEngine();
		if (url.contains(".html"))
		engine.load(getClass().getResource(url).toString());
		else engine.load(url);
		
	}

}
