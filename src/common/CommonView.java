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
	
	//ù ȭ�� ���������� �ε�
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
	
	
	//��� �޴����� Ŭ���� ���� ȭ�鿡 ǥ��
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
	
	//�˻���� ǥ��
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
	
	
	
	//���� â���� �̵�, â ������ ����
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
	
	//�˾�
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
	
	//���� ��� �˾� url(html ���� / �Ϲ����ּ� �����Ͽ� ǥ��)
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
	
//	���� �������� ���� ���� ����(�ӽ�)
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
		case 1 : gradeStr = "���� ��� : �ſ���"; break;
		case 2 : gradeStr = "���� ��� : ���"; break;
		case 3 : gradeStr = "���� ��� : ����";break;
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