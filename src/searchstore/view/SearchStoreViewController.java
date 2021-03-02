package searchstore.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXToggleButton;

import common.IController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;
import searchstore.service.ISearchStoreService;
import searchstore.service.SearchStoreServiceImpl;

public class SearchStoreViewController implements Initializable, IController{
public static WebView wView;
Boolean isSelectedtBtn;
Parent form;
String status;
@FXML private WebView webView;
@FXML private Label hygieneLbl;
@FXML private Label safetyLbl;
@FXML private Label resultLbl;
@FXML private HBox bothBox;
@FXML private Label word;
@FXML private ScrollPane scrollPane;
@FXML private JFXToggleButton toggleAllBtn;
@FXML private JFXToggleButton toggleHygieneBtn;
@FXML private JFXToggleButton toggleSafetyBtn;
@FXML private JFXToggleButton toggleBothBtn;
ArrayList<JFXToggleButton> toggleBtns;

ISearchStoreService iServ;


	public SearchStoreViewController() {
		iServ = new SearchStoreServiceImpl();
	}

	public void setWebView(WebView webview) {
		wView = webView;
	}

	public WebView getWebView() {
		return wView;
	}
	
	private void scrollPolicy() {
		scrollPane.hbarPolicyProperty (). setValue (ScrollPane.ScrollBarPolicy.NEVER);
		scrollPane.vbarPolicyProperty (). setValue (ScrollPane.ScrollBarPolicy.ALWAYS);
	}
	
	public void OnAllResult() {
		status = "allResult";
		
		iServ.getAll(form);
		iServ.setCells(form);
		iServ.setStars(form);
		
		iServ.showToggleButton(status);
		iServ.setMapInvisible(webView);
		
	}
	
	public void OnHygiene() {
		status = "hygiene";
		String table = "hygiene";
		
		iServ.getOneTable(table, word);
		iServ.setCells(form);
		iServ.setStars(form);
		
		iServ.showToggleButton(status);
		iServ.setMapInvisible(webView);
		
	}
	
	public void OnSafety() {
		status = "safety";
		String table = "safety";
		iServ.getOneTable(table, word);
		iServ.setCells(form);
		iServ.setStars(form);
		
		iServ.showToggleButton(status);
		iServ.setMapInvisible(webView);
	}
	
	public void OnBoth() {
		status ="both";
		iServ.getBothStarStore(form);
		iServ.getBothStarList();
		iServ.setCells(form);
		iServ.setStars(form);
		
		iServ.showToggleButton(status);
		iServ.setMapInvisible(webView);
	}
	
	private void setCursor() {
		resultLbl.setCursor(Cursor.HAND);
		hygieneLbl.setCursor(Cursor.HAND);
		safetyLbl.setCursor(Cursor.HAND);
		bothBox.setCursor(Cursor.HAND);
	}
	
	public void isSelectedAllToggleBtn() {
		toggleAllBtn.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				iServ.toggleAllBtnListener(form);
				iServ.setMapInvisible(webView);
				
			}
		});
	}
	
	private void isSelectedHygieneToggleBtn() {
		
		toggleHygieneBtn.selectedProperty().addListener(new ChangeListener<Boolean>() {
			String table = "hygiene";

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				iServ.toggleHygieneBtnListener(table, form);
				iServ.setMapInvisible(webView);
			}
		});
	}
	
	private void isSelectedSafetyToggleBtn() {
		toggleSafetyBtn.selectedProperty().addListener(new ChangeListener<Boolean>() {
			String table = "safety";

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				iServ.toggleSafetyBtnListener(table, form);
				iServ.setMapInvisible(webView);
			}
		});
	}
	
	private void isSelectedBothToggleBtn() {
		toggleBothBtn.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				iServ.toggleBothBtnListener(form);
				iServ.setMapInvisible(webView);
			}
		});
	}
	
	private void addToggleBtnLst() {
		toggleBtns = new ArrayList<JFXToggleButton>();
		toggleBtns.add(toggleAllBtn);
		toggleBtns.add(toggleHygieneBtn);
		toggleBtns.add(toggleSafetyBtn);
		toggleBtns.add(toggleBothBtn);
		
		iServ.setToggleBtns(toggleBtns);
	}
	
	private void setToggleBtn() {
		status = "init";
		iServ.showToggleButton(status);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setWebView(webView);
		scrollPolicy();	
		setCursor();
		addToggleBtnLst();
		setToggleBtn();
		isSelectedAllToggleBtn();
		isSelectedHygieneToggleBtn();
		isSelectedSafetyToggleBtn();
		isSelectedBothToggleBtn();
	}

	@Override
	public void setForm(Parent root) {
		this.form=root;
		
	}
}
