package common;

import java.util.Optional;

import com.jfoenix.controls.JFXButton;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.Window;

public class CommonAlert {
	
	private void setAlertImg(Alert alert) {
		alert.setGraphic(new ImageView("file:res/icon/caution.png"));
		Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
		alertStage.getIcons().add(new Image("file:res/icon/icon.png"));
	}
	
	private void setAlertTxt(Alert alert, String title, String msg) {
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(msg);
	}
	
	private void setYNButton(Alert alert){
		ButtonType btnOk = new ButtonType("확인", ButtonData.YES);
		ButtonType btnCancel = new ButtonType("취소",ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(btnOk, btnCancel);
	}
	
	private void setOwner(Alert alert, JFXButton btn) {
		Window window =btn.getParent().getScene().getWindow();
		alert.initOwner(window);
	}
	
	
	
//	단순 안내
	public void AlertInfoMsg(String title, String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		setAlertTxt(alert, title, msg);
		setAlertImg(alert);
		alert.showAndWait();
	}
	
//	로그인 true, false return
	public boolean LoginResultMsg(String title, String msg, String id, String pw, JFXButton btn) {
		Alert alert = new Alert(AlertType.INFORMATION);
		setAlertTxt(alert, title, msg);
		setAlertImg(alert);
		setOwner(alert, btn);
		
		alert.showAndWait();
		
		return true;
//		return false;
	}
	
	//예 / 아니오 대답을 요구할 경우 (종속성, boolean)
	public boolean AlertConfirm(String title, String msg, JFXButton btn) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		setAlertTxt(alert, title, msg);
		setAlertImg(alert);
		setYNButton(alert);
		setOwner(alert, btn);
		
		Optional<ButtonType> result = alert.showAndWait();
		
		if(result.get().getText()=="확인") {
			return true;
		}
		else return false;
	}
}
