package member.login.view;

import com.jfoenix.controls.JFXButton;

import common.CommonAlert;
import common.CommonView;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.stage.Window;

public class LoginViewController {

@FXML JFXButton loginBtn;
	
	public void OnLogin() {
		String id ="id";
		String pw ="pw";
		
		CommonAlert commonAlert = new CommonAlert();
		Boolean isLogin= commonAlert.LoginResultMsg("�α��� ���", "�α��� ����Դϴ�.", id, pw, loginBtn);
		
		Window window = loginBtn.getParent().getScene().getWindow();
		Stage stage = (Stage) window;
		if (isLogin) stage.close();
	
	}
	
	public void OnFindId() {
		CommonView commonView = new CommonView();
		commonView.FormPopup("../member/help/view/findId.fxml", "���̵� ã��");
	}
	
	public void OnFindPw() {
		CommonView commonView = new CommonView();
		commonView.FormPopup("../member/help/view/findPw.fxml", "�н����� ã��");
	}
	
	public void OnRegisterMem() {
		CommonView commonView = new CommonView();
		commonView.FormPopup("../member/register/view/register.fxml", "ȸ������");
	}

}
