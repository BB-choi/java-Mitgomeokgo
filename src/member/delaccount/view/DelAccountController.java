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
		boolean isDelete = (boolean) commonAlert.AlertConfirm("회원 탈퇴", "회원 정보를 삭제합니다.", delAccountBtn);
		
//		System.out.println(isDelete);
		
		if(isDelete) {
//			DB에서 지우고 성공하면
			commonAlert.AlertInfoMsg("회원탈퇴 완료", "회원 정보가 삭제되었습니다.");
			currentStage.close();
		}
		else {
			System.out.println("회원탈퇴 취소 버튼 누름");
		}
	}

}
