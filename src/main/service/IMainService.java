package main.service;

import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;

import javafx.scene.Parent;
import javafx.scene.control.Label;

public interface IMainService {
	void btnHover(ArrayList<JFXButton> Btnlst);
	void showTooltip(Label locationLbl);
	boolean searchCheck(Parent form);
	void resultFormLoad(Parent form);
	void resetTxtField(Parent form);
	void setProgressIndicator(Parent form);
}
