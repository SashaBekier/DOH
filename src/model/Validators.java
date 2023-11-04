package model;

public class Validators {
	public static boolean hasContent(String text) {
		if(text.length() > 0) return true;
		return false;
	}
}
