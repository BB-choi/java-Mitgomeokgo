package common;

public class CommonCheck {
	public boolean HanEng(String txt) {
		boolean bl = txt.matches("[0-9|a-z|A-Z|��-��|��-��|��-��].*\\S");
		return bl;
	}

}
