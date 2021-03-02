package member.help.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import common.CommonView;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class FindPwController {
@FXML JFXTextField inputAuth;
@FXML JFXButton onAuthBtn;
	
	public void OnGetAuth() {
		inputAuth.setDisable(false);
		onAuthBtn.setDisable(false);
	}
	
	public void OnSubmitAuth() {
		AnchorPane aPane = (AnchorPane) onAuthBtn.getParent();
		CommonView commonView = new CommonView();
		commonView.FormLoad(aPane, "../member/edit/view/setPwView.fxml");
		
		
	}

}
