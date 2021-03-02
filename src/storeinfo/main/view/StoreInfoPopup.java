package storeinfo.main.view;

import java.util.ArrayList;
import java.util.List;

import common.CommonView;

public class StoreInfoPopup {
	String name;
	String addr;
	String tel;
	
	ArrayList<String> storeInfoLst;
	public void popup() {
		CommonView commonV = new CommonView();
		commonV.StoreInfoPopup("../storeinfo/main/view/storeInfoView.fxml", "가게 상세 정보", storeInfoLst);
	}
	
	public void setName(String name, String addr, String tel) {
		this.name=name;
		this.addr=addr;
		this.tel=tel;
		
		setList();
	}
	
	private void setList() {
		storeInfoLst = new ArrayList<String>();
		storeInfoLst.add(name);
		storeInfoLst.add(addr);
		storeInfoLst.add(tel);
	}
	
	public String getName() {
		return name;
	}
	
	public String getAddr() {
		return addr;
	}
	
	public String getTel() {
		return tel;
	}
}
