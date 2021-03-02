package searchstore.service;

import javafx.scene.Parent;
import javafx.scene.web.WebView;
import searchstore.view.StoreDB;

public interface ICellService {

	void setStore(StoreDB sltStore, Parent form);

	void getStoreInfo(Parent pane);
	
	void setStoreInfo(Parent pane, WebView webView);

}
