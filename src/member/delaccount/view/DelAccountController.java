package member.delaccount.view;

import com.jfoenix.controls.JFXButton;

import common.CommonAlert;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class DelAccountController {
@FXML JFXButton delAccountBtn;	
	
	public void OnDelAccount() {
		CommonAlert commonAlert = new CommonAlert();
		Stage currentStage = (Stage) delAccountBtn.getParent().getScene().getWindow();
		boolean isDelete = (boolean) commonAlert.AlertConfirm("ȸ�� Ż��", "ȸ�� ������ �����մϴ�.", delAccountBtn);
		
//		System.out.println(isDelete);
		
		if(isDelete) {
//			DB���� ����� �����ϸ�
			commonAlert.AlertInfoMsg("ȸ��Ż�� �Ϸ�", "ȸ�� ������ �����Ǿ����ϴ�.");
			currentStage.close();
		}
		else {
			System.out.println("ȸ��Ż�� ��� ��ư ����");
		}
	}

}
