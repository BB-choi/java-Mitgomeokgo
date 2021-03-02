package searchstore.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import searchstore.service.CellServiceImpl;
import searchstore.service.ICellService;

public class StoreCellController implements Initializable{
	@FXML public Pane pane;
	@FXML AnchorPane aPane;
	
	ICellService iCellServ;
	Parent form;
	
	public StoreCellController() {
		iCellServ = new CellServiceImpl();
	}
	
	public void setStore(StoreDB sltStore) {
		iCellServ.setStore(sltStore, form);
	}
	
	public void setForm(Parent root) {
		this.form = root;
	}
	
	private void getStoreInfo() {
		iCellServ.getStoreInfo(pane);
	}
	
	private void setStoreInfo(WebView webView) {
		iCellServ.setStoreInfo(pane, webView);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		SearchStoreViewController parentCtrler = new SearchStoreViewController();
		WebView webView = parentCtrler.getWebView();
		getStoreInfo();
		setStoreInfo(webView);
		aPane.setCursor(Cursor.HAND);
	}

}
