package member.edit.view;

import com.jfoenix.controls.JFXButton;

import common.CommonView;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class MemberEditMainController {
@FXML JFXButton delAccountBtn;
@FXML JFXButton backBtn;
	
	public void OnPwEdit() {
		CommonView commonView = new CommonView();
		commonView.FormPopup("../member/edit/view/setPwView.fxml", "비밀번호 변경");
	}
	
	public void OnMailEdit() {
		CommonView commonView = new CommonView();
		commonView.FormPopup("../member/edit/view/mailEditView.fxml", "메일주소 변경");
	}
	
	public void OnDelAccount() {
		CommonView commonView = new CommonView();
		commonView.FormLoad(delAccountBtn, "../member/delaccount/view/delAccount.fxml",720,250);
		
	}
	
	public void OnBack() {
		CommonView commonView = new CommonView();
		AnchorPane aPane = (AnchorPane) backBtn.getParent();
		commonView.FormLoad(aPane, "../member/mypage/view/mypageView.fxml");
	
	}

}
