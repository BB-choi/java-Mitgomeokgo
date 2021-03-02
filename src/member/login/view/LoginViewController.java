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
		Boolean isLogin= commonAlert.LoginResultMsg("로그인 결과", "로그인 결과입니다.", id, pw, loginBtn);
		
		Window window = loginBtn.getParent().getScene().getWindow();
		Stage stage = (Stage) window;
		if (isLogin) stage.close();
	
	}
	
	public void OnFindId() {
		CommonView commonView = new CommonView();
		commonView.FormPopup("../member/help/view/findId.fxml", "아이디 찾기");
	}
	
	public void OnFindPw() {
		CommonView commonView = new CommonView();
		commonView.FormPopup("../member/help/view/findPw.fxml", "패스워드 찾기");
	}
	
	public void OnRegisterMem() {
		CommonView commonView = new CommonView();
		commonView.FormPopup("../member/register/view/register.fxml", "회원가입");
	}

}
