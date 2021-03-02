package storeinfo.service;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import common.CommonAlert;
import javafx.scene.Parent;
import javafx.scene.control.Label;

public class StoreInfoServiceImpl implements IStoreInfoService{

	@Override
	public void Onshare(Parent form) {
		Label storeName = (Label) form.lookup("#storeName");
		Label storeAddr = (Label) form.lookup("#storeAddr");
		Label storeTel = (Label) form.lookup("#storeTel");
		
		String name = storeName.getText();
		String addr = storeAddr.getText();
		String tel = storeTel.getText();
		
		StringSelection strSelection = 
				new StringSelection("매장명 : " +name
						+"\n전화번호 : " + tel
						+"\n주소 : " + addr);
		
		setClipboardContent(strSelection);
		callAlert("안내메시지", "클립보드에 업체 정보가 저장되었습니다.");
	}
	
	private void setClipboardContent(StringSelection strSelection) {
		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		clip.setContents(strSelection, null);
	}
	
	private void callAlert(String title, String content) {
		CommonAlert ca = new CommonAlert();
		ca.AlertInfoMsg(title, content);
	}

}
