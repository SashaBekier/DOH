package view;

import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Post;
import model.Validators;
import view.controls.ValidatedButton;
import view.controls.ValidatedTextField;


public class PostPieChart {
	private List<Post> posts;
	private Stage stage = new Stage();
	private Integer DEFAULT_LOW_BELOW = 100;
	private Integer DEFAULT_HIGH_ABOVE = 999;
	private PieChart pie;
	
	public PostPieChart(List<Post> posts) {
		this.posts = posts;
		
		VBox container = new VBox(5);
		container.setAlignment(Pos.CENTER);
		FlowPane controls = new FlowPane();
		controls.setAlignment(Pos.CENTER);
		controls.setHgap(5);
		
		ValidatedButton submit = new ValidatedButton("Update Pie");
		submit.setDefaultButton(true);
		
		Label low = new Label("Low is Below: ");
		ValidatedTextField lowBelow = new ValidatedTextField(
				s->Validators.isInt(s),submit);
		lowBelow.setValidatedText(DEFAULT_LOW_BELOW.toString());
		lowBelow.setPrefWidth(50);
		
		Label high = new Label("High is Above: ");
		ValidatedTextField highAbove = new ValidatedTextField(
				s->Validators.isInt(s),submit);
		highAbove.setValidatedText(DEFAULT_HIGH_ABOVE.toString());
		highAbove.setPrefWidth(50);
		
		ToggleButton onShares = new ToggleButton("Show Shares");
		onShares.setSelected(true);
		
		onShares.setOnAction(e -> onShares.setText(
				onShares.isSelected()?"Show Shares":"Show Likes"));
			
		Label warning = new Label(
				"High above value should be higher than low below value.");
		warning.setTextFill(Color.RED);
		warning.setVisible(false);

		controls.getChildren().addAll(low,lowBelow,high,highAbove,onShares,submit);
		
		pie = configurePie(DEFAULT_LOW_BELOW,DEFAULT_HIGH_ABOVE,true);
		
		container.getChildren().addAll(controls, warning, pie );
		Scene scene = new Scene(container, 500,500);
		
		submit.setOnAction(e ->{
			container.getChildren().remove(pie);
			int lowVal = Integer.parseInt(lowBelow.getText());
			int highVal = Integer.parseInt(highAbove.getText());
			pie = configurePie(lowVal,highVal,onShares.isSelected());
			container.getChildren().add(pie);
			if(lowVal<highVal) {
				warning.setVisible(false);
			} else {
				warning.setVisible(true);
			}
		});
		stage.setScene(scene);
		stage.show();
	}

	
	private PieChart configurePie(int lowBelow, int highAbove, 
			boolean onShares) {
		int[] sliceCounts = {0,0,0};
		String mode = onShares?" Shares":" Likes";
		String lowString = "0 - " + Integer.toString(lowBelow-1) + mode;
		String medString = Integer.toString(lowBelow) + " - " 
					+ Integer.toString(highAbove)+ mode;
		String highString = Integer.toString(highAbove + 1) + "+"+ mode;
		for(Post post: posts) {
			int value = onShares?post.getShares():post.getLikes();
			stage.setTitle("Pie Chart of Posts by" + mode);
			if(value < lowBelow) {
				sliceCounts[0]++;
			} else if (value <= highAbove) {
				sliceCounts[1]++;
			} else {
				sliceCounts[2]++;
			}
		}
		PieChart pie = new PieChart();
		PieChart.Data low = new PieChart.Data(lowString, sliceCounts[0]);
		PieChart.Data med = new PieChart.Data(medString, sliceCounts[1]);
		PieChart.Data high = new PieChart.Data(highString, sliceCounts[2]);
		
		pie.getData().addAll(low, med, high);
		pie.setLabelsVisible(false);
		
		pie.prefWidthProperty().bind(stage.widthProperty().multiply(0.8));
		pie.prefHeightProperty().bind(pie.widthProperty());
		return pie;
	}
}
