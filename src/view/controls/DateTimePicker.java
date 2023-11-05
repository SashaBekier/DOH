package view.controls;

import java.sql.Time;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import javafx.event.EventDispatcher;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.HBox;

public class DateTimePicker {
	private HBox container = new HBox();
	private Spinner<Integer> hours = new Spinner<Integer>();
	private Spinner<Integer> mins = new Spinner<Integer>();
	private DatePicker date = new DatePicker();
	
	
	public DateTimePicker() {
		hours.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23));
		hours.getValueFactory().setWrapAround(true);
		mins.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59));
		mins.getValueFactory().setWrapAround(true);
		container.getChildren().addAll(date, hours, new Label(":"), mins);
		date.setOnMouseMoved(e -> container.fireEvent(e));
		date.setOnKeyTyped(e -> container.fireEvent(e));
		date.setPrefWidth(100);
		hours.setOnKeyTyped(e -> container.fireEvent(e));
		hours.setOnMouseMoved(e -> container.fireEvent(e));
		hours.setPrefWidth(55);
		mins.setOnKeyTyped(e -> container.fireEvent(e));
		mins.setOnMouseMoved(e -> container.fireEvent(e));
		mins.setPrefWidth(55);
		mins.setEditable(true);
		hours.setEditable(true);
	}
	
	public DateTimePicker(int hours, int minutes) {
		this();
		this.hours.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23,hours));
		this.hours.getValueFactory().setWrapAround(true);
		this.mins.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,minutes));
		this.mins.getValueFactory().setWrapAround(true);
		
	}
	
	public HBox getControl() {
		return container;
	}
	
	public LocalDateTime getDateTime() {
		try {
			LocalTime time = LocalTime.of(hours.getValue(),mins.getValue());
			LocalDateTime answer = LocalDateTime.of(date.getValue(), time);
			return answer;
		} catch (DateTimeException e) {
			return null;
		} catch (NullPointerException e) {
			return null;
		}
	}
}
