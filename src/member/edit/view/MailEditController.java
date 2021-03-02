package member.edit.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;

public class MailEditController {
@FXML JFXButton getAuthBtn;
@FXML JFXButton submitAuthBtn;
@FXML JFXTextField iptAuth;
	
	public void OnGetAuth() {
		iptAuth.setDisable(false);
		submitAuthBtn.setDisable(false);
	}
	
	public void OnSubmitAuth() {
		
	}

}
