package common;

import java.io.IOException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import main.info.view.InfoController;
import searchstore.dao.ISearchDAO;
import searchstore.dao.SearchStoreDaoImpl;

public class CommonView {
Parent form;
ISearchDAO iDao;
String gradeStr;
String iconPath = "file:res/icon/icon.png";

	public CommonView() {
		iDao = new SearchStoreDaoImpl();
	}
	
	//첫 화면 메인페이지 로드
	public void FormLoad(Stage stage, String fxmlPath, String title) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
		Parent root=null;
		
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setForm(loader, root);
		setStage(stage, root, title);
		stage.show();
	}
	
	
	//상단 메뉴에서 클릭시 메인 화면에 표시
	public void FormLoad(AnchorPane pane, String fxmlPath) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
		try {
			Parent contentView = loader.load();
			pane.getChildren().clear();
			pane.getChildren().add(contentView);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//검색결과 표시
	public void searchLoad(Parent form, AnchorPane pane, String fxmlPath) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
		Parent contentView = null;
		try {
			contentView = loader.load();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		setForm(loader, contentView);
		setChildren(pane, contentView);
	}
	
	
	
	//같은 창에서 이동, 창 사이즈 변경
	public void FormLoad(JFXButton btn ,String fxmlPath, double width, double height) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
		AnchorPane anchorPane = (AnchorPane) btn.getParent();
		Parent contentView = null;
		
		try {
			contentView = loader.load();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setChildren(anchorPane, contentView);
		resize(anchorPane, width, height);
	}
	
	//팝업
	public void FormPopup(String fxmlPath, String title) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
		Parent root=null;
		
		try {
			root = loader.load();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		Stage stage = new Stage();
		setStage(stage, root, title);
		stage.show();
	}
	
	//메인 배너 팝업 url(html 파일 / 일반웹주소 구분하여 표시)
	public void PopupHandleUrl(String fxmlPath, String title, String url) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
		Parent root=null;
		try {
			root = loader.load();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		Stage stage = new Stage();
		
		InfoController ctrler = loader.getController();
		ctrler.setView(url);
		
		setStage(stage, root, title);
		stage.show();
	}
	
//	가게 상세정보로 가게 정보 전달(임시)
	public void StoreInfoPopup(String fxmlPath, String title, ArrayList<String> lst) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
		Parent root=null;
		
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Stage stage = new Stage();
		
		String storeName = lst.get(0);
		String storeAddr = lst.get(1);
		String storeTel = lst.get(2);
		
		gradeStr = getGrade(storeName);
		setLabel(root, storeName, storeAddr, storeTel, gradeStr);
		
		setForm(loader, root);
		setStage(stage, root, title);
		stage.show();
	};
	
	private void setChildren(AnchorPane parent, Parent children) {
		parent.getChildren().clear();
		parent.getChildren().add(children);
	}
	
	private void setForm(FXMLLoader loader, Parent root) {
		IController iCtrler = loader.getController();
		iCtrler.setForm(root);
	}
	
	private void setStage(Stage stage, Parent root, String title) {
		Scene scene = new Scene(root);
		stage.setTitle(title);
		stage.getIcons().add(new Image(iconPath));
		stage.setResizable(false);
		stage.setScene(scene);
		root.requestFocus();
	}

	private void resize(AnchorPane anchorPane,Double width, Double height) {
		Window window = anchorPane.getScene().getWindow();
		window.setWidth(width);
		window.setHeight(height);
		window.centerOnScreen();
	}
	
	private String getGrade(String sName) {
		int grade = iDao.getGrade(sName);
		switch(grade) {
		case 1 : gradeStr = "위생 등급 : 매우우수"; break;
		case 2 : gradeStr = "위생 등급 : 우수"; break;
		case 3 : gradeStr = "위생 등급 : 좋음";break;
		default : gradeStr = "";
		
		}
		return gradeStr;
	}
	
	private void setLabel(Parent root, String storeName, String storeAddr, String storeTel, String gradeStr) {
		Label nameLbl = (Label) root.lookup("#storeName");
		Label addrLbl = (Label) root.lookup("#storeAddr");
		Label  telLbl = (Label) root.lookup("#storeTel");
		Label gradeLbl = (Label) root.lookup("#grade");
		
		nameLbl.setText(storeName);
		addrLbl.setText(storeAddr);
		telLbl.setText(storeTel);
		gradeLbl.setText(gradeStr);
	}

}