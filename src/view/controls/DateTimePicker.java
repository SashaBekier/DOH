package view.controls;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import javafx.event.EventDispatcher;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import model.Validators;

public class DateTimePicker {
	private HBox container = new HBox();
	private ValidatedTextField hours = new ValidatedTextField(s -> Validators.isIntBetweenOrBlank(0, 23, s));
	private ValidatedTextField mins = new ValidatedTextField(s -> Validators.isIntBetweenOrBlank(0, 59, s));
	private DatePicker date = new DatePicker();
	
	
	public DateTimePicker() {
		container.getChildren().addAll(date, hours, new Label(":"), mins);
		date.setOnMouseMoved(e -> container.fireEvent(e));
		date.setOnKeyTyped(e -> container.fireEvent(e));
		hours.setOnKeyTyped(e -> container.fireEvent(e));
		hours.setOnMouseMoved(e -> container.fireEvent(e));
		mins.setOnKeyTyped(e -> container.fireEvent(e));
		mins.setOnMouseMoved(e -> container.fireEvent(e));
	}
	
	public DateTimePicker(String hours, String minutes) {
		this();
		this.hours.setValue(hours);
		this.mins.setValue(minutes);
	}
	
	public HBox getControl() {
		return container;
	}
	
	public LocalDateTime getDateTime() {
		String dateTimeString = "";
		String hoursString = hours.getText();
		String minsString = mins.getText();
		if(date.getValue() != null &&
				hoursString != "" &&
				minsString != "") {
			dateTimeString = date.getValue().toString();
		
			dateTimeString += "T" + String.format("%2s", hours.getText()).replace(' ', '0') 
				+ ":" + String.format("%2s", mins.getText()).replace(' ', '0');
		}
		try {
			return LocalDateTime.parse(dateTimeString);
		} catch (DateTimeParseException e) {
			return null;
		}
	}
	
	
}
