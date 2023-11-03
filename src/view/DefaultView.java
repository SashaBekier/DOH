package view;

import controller.DefaultController;

public class DefaultView extends View{
	private DefaultController control;
	
	public DefaultView(DefaultController control) {
		this.control = control;
		drawMiddle();
	}
	
	private void drawMiddle() {
		//middlePane = new whateverlayout
	}
}
