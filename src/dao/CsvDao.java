package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import model.DAHModel;
import model.Post;
import model.User;

public class CsvDao {
	private static DAHModel model = DAHModel.getDAHModel();
	private static final String CSV_HEADER = "ID,content,author,likes,shares,date-time,main_post_id";
	

	public static int[] importPostsFrom(File myFile) throws FileNotFoundException {
		int[] result = new int[2];
		Scanner file = new Scanner(myFile);
		String line = "";
		while (file.hasNextLine()) {
			line = file.nextLine();
			if (!line.equals(CSV_HEADER)) {
				try {
					Post newPost = Post.fromCSVRepr(line);
					model.addPost(newPost);
					result[0]++;
				} catch (NumberFormatException e) {
					result[1]++;
				} catch (DateTimeParseException e) {
					result[1]++;
				} catch (ArrayIndexOutOfBoundsException e) {
					result[1]++;
				}
			}
		}
		file.close();
		return result;
	}

}
