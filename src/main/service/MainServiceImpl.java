package main.service;

import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import common.CommonAlert;
import common.CommonCheck;
import common.CommonView;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import searchstore.dao.ISearchDAO;
import searchstore.dao.SearchStoreDaoImpl;
import searchstore.view.StoreDB;

public class MainServiceImpl implements IMainService{
	ISearchDAO iDao;
	ObservableList<StoreDB> sList;
	
	public MainServiceImpl() {
		iDao = new SearchStoreDaoImpl();
	}
	
	@Override
	public void btnHover(ArrayList<JFXButton> Btnlst) {
		for(JFXButton btn : Btnlst) {
			btn.cursorProperty().set(Cursor.HAND);
		}
	}

	@Override
	public void showTooltip(Label locationLbl) {
		Tooltip tt = new Tooltip();
		tt.setText("IP에서 가져온 주소입니다. 정확도가 떨어질 수 있습니다.");
		tt.setStyle("-fx-background-color:#fff;"
				+ "-fx-text-fill:#000;");
		locationLbl.setTooltip(tt);
		
	}
	
	@Override
	public void resultFormLoad(Parent form) {
		AnchorPane anchorMain = (AnchorPane) form.lookup("#anchorMain");
		CommonView cv = new CommonView();
		cv.searchLoad(form, anchorMain, "../searchstore/view/searchStoreView.fxml");
	}
	
	@Override
	public void resetTxtField(Parent form) {
		JFXTextField searchIpt = (JFXTextField) form.lookup("#searchIpt");
		searchIpt.setText("");
		searchIpt.requestFocus();
	}
	
	
	@Override
	public boolean searchCheck(Parent form) {
		CommonAlert ca = new CommonAlert();
		CommonCheck cc = new CommonCheck();
		JFXTextField searchIpt = (JFXTextField) form.lookup("#searchIpt");
		String searchTxt = searchIpt.getText();
		
		if(searchTxt.isEmpty()) {
			ca.AlertInfoMsg("안내", "검색어를 입력해주세요. \n"
					+ "지명이나 업체명으로 검색 가능합니다.");
			searchIpt.requestFocus();
			return false;
		}
		
		if(searchTxt.length()<2) {
			ca.AlertInfoMsg("안내", "검색어가 너무 짧습니다.");
			searchIpt.requestFocus();
			return false;
		}
		
		if(!cc.HanEng(searchTxt)) {
			ca.AlertInfoMsg("안내", "한글,영문자, 숫자로만 작성해주세요.");
			searchIpt.requestFocus();
			return false;
		}
		
		return true;
	}

	@Override
	public void setProgressIndicator(Parent form) {
		AnchorPane anchorMain = (AnchorPane) form.lookup("#anchorMain");
		ProgressIndicator pi = new ProgressIndicator();
        VBox box = new VBox(pi);
        AnchorPane parentAnchor = (AnchorPane) anchorMain.getParent();
        double width =parentAnchor.getWidth();
        double height =parentAnchor.getHeight();
        box.setId("vBox");
        box.setLayoutX(width/2);
        box.setLayoutY((height/2)-30);
        parentAnchor.getChildren().add(box);
		
	}


}
