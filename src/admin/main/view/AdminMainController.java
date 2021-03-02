package admin.main.view;

import common.CommonView;

public class AdminMainController {
	
	public void OnMember() {
		System.out.println("회원관리클릭");
		CommonView commonView = new CommonView();
		commonView.FormPopup("../admin/member/view/adminMemberMainView.fxml", "회원 관리 페이지");
	}
	
	public void OnRating() {
		System.out.println("평점관리 클릭");
		CommonView commonView = new CommonView();
		commonView.FormPopup("../admin/rating/view/adminRatingMainView.fxml", "평가 관리 페이지");
	}

}
