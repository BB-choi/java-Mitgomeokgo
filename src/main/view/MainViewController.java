package main.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import common.CommonView;
import common.IController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import main.service.IMainService;
import main.service.MainServiceImpl;
import searchstore.service.ISearchStoreService;
import searchstore.service.SearchStoreServiceImpl;


public class MainViewController implements Initializable, IController{

private IMainService iMainServ;
private ISearchStoreService iSearchServ;
private CommonView cv;
private Parent form;
@FXML AnchorPane anchorMain;
@FXML JFXTextField searchIpt;
@FXML JFXButton searchBtn;
@FXML JFXButton loginBtn;
@FXML JFXButton memBtn;
@FXML JFXButton exitBtn;
@FXML JFXButton topBtn;
@FXML Label locationLbl;
VBox box;

	
	public MainViewController() {
		iMainServ = new MainServiceImpl();
		iSearchServ = new SearchStoreServiceImpl();
		cv = new CommonView();
	}
	

	public void OnSearchBtn() {
		anchorMain.setVisible(false);
		anchorMain.getChildren().clear();
		iMainServ.setProgressIndicator(form);
        
		Boolean inputChk = iMainServ.searchCheck(form);
		loadResult(inputChk);
	}
	
	private void loadResult(Boolean inputChk) {
		if(inputChk) {
			iMainServ.resultFormLoad(form);
			iSearchServ.setResultList(form);
			iSearchServ.setResultForm(form);
			iSearchServ.setCells(form);
			iSearchServ.setStars(form);
			iSearchServ.setMap(form);
			iMainServ.resetTxtField(form);
		}
	}
	
	public void OnTop() {
		cv.FormLoad(anchorMain, "../main/view/mainBannerView.fxml");
	}
	
	
	public void OnLogin() {
		cv.FormPopup("../member/login/view/loginView.fxml", "회원 로그인");
	}
	
	public void OnMember() {
		cv.FormPopup("../member/mypage/view/mypageView.fxml", "회원 메뉴");
	}
	
	public void OnExit() {
		System.exit(0);
	}
	
	public void OnAdmin() {
		cv.FormPopup("../admin/main/view/adminMainView.fxml", "관리자 메뉴");
	}
	
	public void showTooltip() {
		iMainServ.showTooltip(locationLbl);
	}
	
	public void btnHover() {
		ArrayList<JFXButton> Btnlst = new ArrayList<JFXButton>();
		Btnlst.add(topBtn);Btnlst.add(searchBtn);Btnlst.add(loginBtn);Btnlst.add(memBtn);Btnlst.add(exitBtn);

		iMainServ.btnHover(Btnlst);
	}
	
	public void setCity(String str) {
		String city = str;
		locationLbl.setText(city);
	}
	
	public void IptEnterEvent() {
		searchIpt.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode()==KeyCode.ENTER) OnSearchBtn();
			}
		});
	}
	
	@Override
	public void setForm(Parent root) {
		this.form=root;
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		cv.FormLoad(anchorMain, "../main/view/MainBannerView.fxml");
		searchIpt.requestFocus();
		btnHover();
		showTooltip();
		IptEnterEvent();
		

	}


}
