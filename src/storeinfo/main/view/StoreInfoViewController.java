package storeinfo.main.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import common.CommonAlert;
import common.CommonView;
import common.IController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.stage.Stage;
import storeinfo.service.IStoreInfoService;
import storeinfo.service.StoreInfoServiceImpl;

public class StoreInfoViewController implements Initializable, IController{
	IStoreInfoService iStoreServ;
	CommonAlert ca;
	CommonView cv;
	Parent form;
	@FXML JFXButton shareBtn;
	@FXML JFXButton favoriteBtn;
	@FXML JFXButton visitedBtn;
	@FXML JFXButton ratingBtn;
	@FXML JFXButton closeBtn;
	
	public StoreInfoViewController() {
		iStoreServ = new StoreInfoServiceImpl();
		ca = new CommonAlert();
		cv = new CommonView();
	}
	
	public void OnShare() {
		iStoreServ.Onshare(form);
	}
	
	public void OnWriteRating() {
		String filePath = "../storeinfo/rating/view/writeRating.fxml";
		cv.FormPopup(filePath, "���� �����");
	}
	
	public void OnSetFavorite() {
		ca.AlertConfirm("���ɸ��� �߰�", "���� �������� �߰��Ͻðڽ��ϱ�?", favoriteBtn);
	}
	
	public void OnSetVisited() {
		ca.AlertConfirm("�湮���� �߰�", "�湮 �������� �߰��Ͻðڽ��ϱ�?", visitedBtn);
	}

	public void OnClose() {
		Stage thisStage = (Stage) form.getScene().getWindow();
		thisStage.close();
	}
	
	private void setCursor() {
		ArrayList<JFXButton> btnLst = new ArrayList<JFXButton>();
		btnLst.add(ratingBtn);btnLst.add(favoriteBtn);btnLst.add(visitedBtn);btnLst.add(shareBtn);btnLst.add(closeBtn);
		for(JFXButton btn:btnLst) btn.setCursor(Cursor.HAND);
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setCursor();
		
	}

	@Override
	public void setForm(Parent root) {
		this.form=root;
		
	}

}
