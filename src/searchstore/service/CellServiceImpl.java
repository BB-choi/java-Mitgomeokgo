package searchstore.service;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import searchstore.dao.ISearchDAO;
import searchstore.dao.SearchStoreDaoImpl;
import searchstore.view.StoreDB;
import storeinfo.main.view.StoreInfoPopup;

public class CellServiceImpl implements ICellService{
	ISearchDAO iDao;
	Label nameLbl;
	Label telLbl;
	Label locationLbl;
	String adrStr;
	String nameStr;
	String telNum;
	Pane pane;
	int safety_idxNo;
	int hygiene_idxNo;
	
	public CellServiceImpl() {
		iDao = new SearchStoreDaoImpl();
	}

	@Override
	public void setStore(StoreDB sltStore, Parent form) {
		nameLbl = (Label) form.lookup("#name");
		telLbl = (Label) form.lookup("#tel");
		locationLbl = (Label) form.lookup("#location");
		
		nameLbl.setText(sltStore.getStoreName());
		telLbl.setText(sltStore.getStoreTel());
		locationLbl.setText(sltStore.getStoreAddr());
		
	}

	@Override
	public void getStoreInfo(Parent pane) {
		pane.setOnMouseEntered(event->{
			adrStr = locationLbl.getText();
			nameStr = nameLbl.getText();
			telNum = telLbl.getText();
		});
		
	}

	@Override
	public void setStoreInfo(Parent pane, WebView webView) {
		AnchorPane webParent = (AnchorPane) webView.getParent();
		WebEngine engine = webView.getEngine();
//		SearchStoreDaoImpl dao = new SearchStoreDaoImpl();
		
		String tableSafety = "safety";
		String tableHygiene = "hygiene";
		String tableView="_view";
		
		pane.setOnMouseClicked(event -> {
			engine.executeScript("setAddr('"+adrStr+"','"+nameStr+"')");
			webParent.setOpacity(1); 
			javaEvt(engine, nameStr, adrStr, telNum);
			
			safety_idxNo = iDao.getIdxNum(nameStr, telNum, tableSafety);
//			System.out.println(safety_idxNo);
			hygiene_idxNo = iDao.getIdxNum(nameStr, telNum, tableHygiene);
//			System.out.println(hygiene_idxNo);
			
			Boolean isSafety = iDao.isExistIdx(safety_idxNo, tableSafety);
			Boolean isHygiene = iDao.isExistIdx(hygiene_idxNo, tableHygiene);
			
			if (isSafety) {
				iDao.increaseViewNum(safety_idxNo, tableSafety+tableView);
			}
			if (isHygiene) {
				iDao.increaseViewNum(hygiene_idxNo, tableHygiene+tableView);
			}
		});
		
	}
	
	public void javaEvt(WebEngine engine, String txt, String adr, String telNum) {
		StoreInfoPopup storePopup = new StoreInfoPopup();
		storePopup.setName(txt, adr, telNum);
		
		JSObject win = (JSObject) engine.executeScript("window");
		win.setMember("app", storePopup);
	}
}
