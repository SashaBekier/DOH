package view;

import java.util.List;

import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Post;


public class PostPieChart {
	private List<Post> posts;
	
	public PostPieChart(List<Post> posts) {
		this.posts = posts;
		
		Stage stage = new Stage();
		VBox container = new VBox(configurePie(100,999,true));
		Scene scene = new Scene(container, 400,400);
		stage.setScene(scene);
		
		stage.show();
		
	}

	
	private PieChart configurePie(int lowBelow, int highAbove, boolean onShares) {
		int[] sliceCounts = {0,0,0};
		
		for(Post post: posts) {
			int value = 0;
			if(onShares) {
				value = post.getShares();
			} else {
				value = post.getLikes();
			}
			if(value < lowBelow) {
				sliceCounts[0]++;
			} else if (value <= highAbove) {
				sliceCounts[1]++;
			} else {
				sliceCounts[2]++;
			}
		}
		PieChart pie = new PieChart();
		PieChart.Data low = new PieChart.Data("Low", sliceCounts[0]);
		PieChart.Data med = new PieChart.Data("Medium", sliceCounts[1]);
		PieChart.Data high = new PieChart.Data("High", sliceCounts[2]);
		
		pie.getData().addAll(low, med, high);
		return pie;
	}
}
