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
		tt.setText("IP���� ������ �ּ��Դϴ�. ��Ȯ���� ������ �� �ֽ��ϴ�.");
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
			ca.AlertInfoMsg("�ȳ�", "�˻�� �Է����ּ���. \n"
					+ "�����̳� ��ü������ �˻� �����մϴ�.");
			searchIpt.requestFocus();
			return false;
		}
		
		if(searchTxt.length()<2) {
			ca.AlertInfoMsg("�ȳ�", "�˻�� �ʹ� ª���ϴ�.");
			searchIpt.requestFocus();
			return false;
		}
		
		if(!cc.HanEng(searchTxt)) {
			ca.AlertInfoMsg("�ȳ�", "�ѱ�,������, ���ڷθ� �ۼ����ּ���.");
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
