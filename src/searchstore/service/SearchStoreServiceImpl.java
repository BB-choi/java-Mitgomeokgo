package searchstore.service;

import java.io.IOException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import javafx.collections.ObservableList;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import searchstore.dao.ISearchDAO;
import searchstore.dao.SearchStoreDaoImpl;
import searchstore.view.StoreCellController;
import searchstore.view.StoreDB;

public class SearchStoreServiceImpl implements ISearchStoreService{
	ISearchDAO iDao;
	ObservableList<StoreDB> sList;
	ArrayList<JFXToggleButton> toggleBtns;
	ArrayList<String> bothTblNameLst;
	ArrayList<String> bothTblAddrList;
	JFXToggleButton toggleAllBtn;
	JFXToggleButton toggleHygieneBtn;
	JFXToggleButton toggleSafetyBtn;
	JFXToggleButton toggleBothBtn;
	
	public SearchStoreServiceImpl() {
		iDao = new SearchStoreDaoImpl();
	}
	
	@Override
	public void setResultForm(Parent form) {
		JFXTextField searchIpt = (JFXTextField) form.lookup("#searchIpt");
		Label wordLbl = (Label) form.lookup("#word");
		Label sizeLbl = (Label) form.lookup("#resultNum");
		
		String searchWord = searchIpt.getText();
		wordLbl.setText(searchWord);
		
		int lstSizeInt = sList.size();
		sizeLbl.setText(Integer.toString(lstSizeInt)+"건");
	}
	
	@Override
	public void setResultList(Parent form) {
		JFXTextField searchIpt = (JFXTextField) form.lookup("#searchIpt");
		String searchWord = searchIpt.getText();
		sList = iDao.setAllList(searchWord);
	}
	
	@Override
	public void getAll(Parent form) {
		Label searchWord = (Label) form.lookup("#word");
		String word = searchWord.getText();
		sList = iDao.setAllList(word);
		form = searchWord.getParent();
	}
	@Override
	public void getAllOrderByView(Parent form) {
		Label searchWord = (Label) form.lookup("#word");
		String word = searchWord.getText();
		sList = iDao.setAllListOrderByView(word);
		form = searchWord.getParent();
	}
	
	@Override
	public void setCells(Parent form) {
		ScrollPane sPane = (ScrollPane) form.lookup("#scrollPane");
		FlowPane cellContainer = (FlowPane) sPane.getContent();
		cellContainer.getChildren().clear();
		
		for(int i=0;i<sList.size();i++) {
			FXMLLoader loader= new FXMLLoader(getClass().getResource("../view/storeCell.fxml"));
			Parent cell;
			try {
				cell = loader.load();
				StoreDB sdb = sList.get(i);
				cellContainer.getChildren().add(cell);
				StoreCellController ctrler = loader.getController();
				ctrler.setForm(cell);
				ctrler.setStore(sdb);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public void setMap(Parent form) {
		AnchorPane anchorMain = (AnchorPane) form.lookup("#anchorMain");
		AnchorPane parentAnchor = (AnchorPane) anchorMain.getParent();
		VBox box = (VBox) parentAnchor.lookup("#vBox");
		WebView webView = (WebView) form.lookup("#webView");
		WebEngine engine = webView.getEngine();
		
		AnchorPane webParent = (AnchorPane) webView.getParent();
		webParent.setOpacity(0);
		
		engine.load(getClass().getResource("../../searchstore/view/map/html/map.html").toString());
		
		engine.getLoadWorker().stateProperty().addListener((ov,oldState,newState)->{
            if(newState==State.SUCCEEDED){
//				System.out.println("지도 로딩 완료");
				anchorMain.setVisible(true);
				parentAnchor.getChildren().remove(box);
            }
        });
	}
	
	@Override
	public void setStars(Parent form) {
		ScrollPane sPane = (ScrollPane) form.lookup("#scrollPane");
		FlowPane cellContainer = (FlowPane) sPane.getContent();
		
		for(int i=0;i<sList.size();i++) {
			FXMLLoader greenStarLoad = new FXMLLoader(getClass().getResource("../../searchstore/view/greenStar.fxml"));
			FXMLLoader yellowStarLoad = new FXMLLoader(getClass().getResource("../../searchstore/view/yellowStar.fxml"));	
			
			try {
				Parent green = greenStarLoad.load();
				Parent yellow = yellowStarLoad.load();
				AnchorPane chk = (AnchorPane) cellContainer.getChildren().get(i);
				
				addStar(form,chk, green, yellow);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	private void addStar(Parent form, AnchorPane chk, Parent green, Parent yellow) {
		Label chkNameLbl = (Label) chk.lookup("#name");
		Label chkAddrLbl = (Label) chk.lookup("#location");
		HBox nameBox = (HBox) chkNameLbl.getParent();
		AnchorPane pane = (AnchorPane) nameBox.getParent();
		String location = chkAddrLbl.getText();
		String chkName = chkNameLbl.getText();
		String chkLocation = location.substring(6, 9);
		
		if(iDao.chkName(chkName, chkLocation, "safety")) {
			pane.getChildren().add(green);
			green.setLayoutY(23);
			green.setLayoutX(14);
			nameBox.setLayoutX(40);
		}
		
		if(iDao.chkName(chkName, chkLocation, "hygiene")) {
			pane.getChildren().add(yellow);
			yellow.setLayoutY(23);
			yellow.setLayoutX(14);
			nameBox.setLayoutX(40);
		}
		if(pane.getChildren().size() >=7) {
			green.setLayoutX(40);
			nameBox.setLayoutX(65);
			}
}

	@Override
	public void getOneTable(String table, Parent form) {
		Label searchWord = (Label) form.lookup("#word");
		sList = iDao.setList(table, searchWord);
	}

	@Override
	public void getOneOrderByView(String table, Parent form) {
		Label searchWord = (Label) form.lookup("#word");
		sList = iDao.setListOrderByView(table, searchWord);
		
	}
	
	@Override
	public void setToggleBtns(ArrayList<JFXToggleButton> btns) {
		toggleBtns = btns;
		
		toggleAllBtn = toggleBtns.get(0);
		toggleHygieneBtn = toggleBtns.get(1);
		toggleSafetyBtn = toggleBtns.get(2);
		toggleBothBtn = toggleBtns.get(3);
		
	}

	@Override
	public void showToggleButton(String status) {
		switch(status) {
			case "init" : {
				toggleHygieneBtn.setVisible(false);
				toggleSafetyBtn.setVisible(false);
				toggleBothBtn.setVisible(false);
				break;
			}
			case "allResult" : {
				toggleAllBtn.setVisible(true);
				toggleAllBtn.setSelected(false);
				toggleHygieneBtn.setVisible(false);
				toggleSafetyBtn.setVisible(false);
				toggleBothBtn.setVisible(false);
				break;
			}
			case "hygiene" : {
				toggleAllBtn.setVisible(false);
				toggleHygieneBtn.setVisible(true);
				toggleHygieneBtn.setSelected(false);
				toggleSafetyBtn.setVisible(false);
				toggleBothBtn.setVisible(false);
				break;
			}
			case "safety" : {
				toggleAllBtn.setVisible(false);
				toggleHygieneBtn.setVisible(false);
				toggleSafetyBtn.setVisible(true);
				toggleSafetyBtn.setSelected(false);
				toggleBothBtn.setVisible(false);
				break;
			}
			case "both" : {
				toggleBothBtn.setSelected(false);
				toggleAllBtn.setVisible(false);
				toggleHygieneBtn.setVisible(false);
				toggleSafetyBtn.setVisible(false);
				toggleBothBtn.setVisible(true);
			}
			default : ;
		}
		
	}

	@Override
	public void toggleAllBtnListener(Parent form) {
		if(toggleAllBtn.isSelected()) { 
					getAllOrderByView(form);
					setCells(form);
					setStars(form);
		} else {
			getAll(form);
			setCells(form);
			setStars(form);
		}
	}

	@Override
	public void toggleHygieneBtnListener(String table, Parent form) {
		if(toggleHygieneBtn.isSelected()) { 
			getOneOrderByView(table, form);
			setCells(form);
			setStars(form);}
		
		else {
			getOneTable(table, form);
			setCells(form);
			setStars(form);
		}
		
	}

	@Override
	public void toggleSafetyBtnListener(String table, Parent form) {
		if(toggleSafetyBtn.isSelected()) { 
			getOneOrderByView(table, form);
			setCells(form);
			setStars(form);}
		
		else {
			getOneTable(table, form);
			setCells(form);
			setStars(form);
		}
		
	}

	@Override
	public void setMapInvisible(WebView webView) {
		AnchorPane webParent = (AnchorPane) webView.getParent();
		webParent.setOpacity(0);
	}

	@Override
	public void getBothStarStore(Parent form) {
		ScrollPane scrollPane = (ScrollPane) form.lookup("#scrollPane");
		FlowPane fPane = (FlowPane) scrollPane.getContent();
		bothTblNameLst= new ArrayList<String>();
		bothTblAddrList= new ArrayList<String>();
		Label storeNameLbl, storeAddrLbl;
		String storeName, storeAddr;
		for(int i=0;i<fPane.getChildren().size();i++) {
		AnchorPane cell = (AnchorPane) fPane.getChildren().get(i);
		AnchorPane cellChildPane= (AnchorPane) cell.getChildren().get(0);
		
		int cellLstSize = cellChildPane.getChildren().size();
			
			if (cellLstSize>6) {
				storeNameLbl = (Label) cellChildPane.lookup("#name");
				storeAddrLbl = (Label) cellChildPane.lookup("#location");
				storeName = storeNameLbl.getText();
				storeAddr = storeAddrLbl.getText();
				
				bothTblNameLst.add(storeName);
				bothTblAddrList.add(storeAddr);
			}
		
		}
		
		
	}
	@Override
	public void getBothStarList() {
		sList = iDao.setBothTableList(bothTblNameLst, bothTblAddrList);
	}

	@Override
	public void toggleBothBtnListener(Parent form) {
		if(toggleBothBtn.isSelected()) { 
			getBothOrderByView(bothTblNameLst, bothTblAddrList);
			setCells(form);
			setStars(form);
		} else {
			getBothStarList();
			setCells(form);
			setStars(form);
		}
		
	}
	
	public void getBothOrderByView(ArrayList<String> nameLst, ArrayList<String> addrLst) {
		sList = iDao.setBothListOrderByView(nameLst, addrLst);
	}
	

}
