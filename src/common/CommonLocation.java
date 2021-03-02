package common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javafx.scene.control.Label;

public class CommonLocation {
	String city;
	//���� �ּҷ� ����, �浵 ���ͼ� ������ ǥ�� �õ� (������ġ)
	String lat;
	String lon;
	
	//���� ���� ip���� ������
	public void getCity() throws Exception {
		String urlStr="http://www.ip-api.com/json";
//		String urlStr="http://ip-api.com/json";
		URL url = new URL(urlStr);
		BufferedReader bf;
		String line = "";
		String result ="";
		bf = new BufferedReader(new InputStreamReader(url.openStream()));
		
		while((line=bf.readLine())!=null) {
			result = result.concat(line);
		}
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(result);
		city = (String) obj.get("city");
		
	}
	
	public void setCity(Label lbl) {
		lbl.setText(city);
	}
	
}
