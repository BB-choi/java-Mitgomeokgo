package admin.main.view;

import common.CommonView;

public class AdminMainController {
	
	public void OnMember() {
		System.out.println("ȸ������Ŭ��");
		CommonView commonView = new CommonView();
		commonView.FormPopup("../admin/member/view/adminMemberMainView.fxml", "ȸ�� ���� ������");
	}
	
	public void OnRating() {
		System.out.println("�������� Ŭ��");
		CommonView commonView = new CommonView();
		commonView.FormPopup("../admin/rating/view/adminRatingMainView.fxml", "�� ���� ������");
	}

}
