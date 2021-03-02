package searchstore.service;

import java.util.ArrayList;

import com.jfoenix.controls.JFXToggleButton;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

public interface ISearchStoreService {

	void getAll(Parent form);
	void getAllOrderByView(Parent form);
	void setCells(Parent form);
	void setStars(Parent form);
	void setMap(Parent form);
	void getOneTable(String table, Parent form);
	void setResultForm(Parent form);
	void setResultList(Parent form);
	void getOneOrderByView(String table, Parent form);
	void setToggleBtns(ArrayList<JFXToggleButton> btns);
	void showToggleButton(String status);
	void toggleAllBtnListener(Parent form);
	void toggleHygieneBtnListener(String table, Parent form);
	void toggleSafetyBtnListener(String table, Parent form);
	void setMapInvisible(WebView webView);
	void getBothStarStore(Parent form);
	void getBothStarList();
	void toggleBothBtnListener(Parent form);

}
