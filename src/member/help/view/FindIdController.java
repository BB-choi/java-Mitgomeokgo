package member.help.view;

import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;

public class FindIdController {
@FXML JFXTextField mailField;
	
	public void OnSubmit() {
		
		String mailAddr = mailField.getText();
		System.out.println("�Էµ� �̸��� : " + mailAddr);
		
	}
}
