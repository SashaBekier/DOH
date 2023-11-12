package model;

public class Validators {
	private static DAHModel model = DAHModel.getDAHModel();
	
	public static boolean hasContent(String text) {
		if(text.length() > 0) return true;
		return false;
	}
	
	public static boolean isIntBetweenOrBlank(int start, int end, String text) {
		if(text.length() == 0) return true;
		return isIntBetween(start, end, text);
	}
	
	public static boolean isIntBetween(int start, int end, String text) {
		if(isInt(text)) {
			int value = Integer.parseInt(text);
			if(value >= start && value <= end) return true;
		}
		return false;
	}
	
	public static boolean isInt(String text) {
		try {
			Integer.parseInt(text);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static boolean isOldPassword(String text) {
		return model.getActiveUser().matchesPassword(text);
	}
	
	public static boolean isPostIdValidAndAvailable(String postIdText) {
		int postId = -1;
		try {
			postId = Integer.parseInt(postIdText);
			model.getPostById(postId);
		} catch (NumberFormatException e) {
			
		} catch (InvalidPostIdException e) {
			return true;
		}
		return false;
	}
}
