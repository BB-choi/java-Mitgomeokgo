package member.mypage.view;

import com.jfoenix.controls.JFXButton;

import common.CommonView;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class MyPageViewController {
@FXML JFXButton memEditBtn;
	
	public void OnMemberEdit() {
		CommonView commonView = new CommonView();
		AnchorPane aPane = (AnchorPane) memEditBtn.getParent();
		commonView.FormLoad(aPane, "../member/edit/view/memberEditMainView.fxml");
	}
	
	public void OnFavoriteStore() {
		CommonView commonView = new CommonView();
		commonView.FormPopup("../member/favorite/view/favoriteStoreView.fxml", "历厘 概厘 包府");
	}
	
	public void OnStoreRating() {
		CommonView commonView = new CommonView();
		commonView.FormPopup("../member/rating/view/StoreRatingView.fxml", "历厘 概厘 包府");
	}

}
