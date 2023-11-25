package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Post {
	public static final int BY_POST_ID = 0;
	public static final int BY_AUTHOR = 1;
	public static final int BY_LIKES = 2;
	public static final int BY_SHARES = 3;
	public static final int BY_DATE = 4;
	public static final int BY_PARENT = 5;
	public static final int BY_CONTENT = 6;
	public final String author;
	public final String content;
	public final int id;
	public final int likes;
	public final int parentId;
	public final LocalDateTime postedAt;
	public final int shares;
	private static final String COMMA_ENCODING = "%COMMA%";
	private static final DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = DateTimeFormatter
			.ofPattern("dd/MM/yyyy HH:mm");
	
	public Post(int id, String content, String author, int likes, int shares,
			LocalDateTime dateTime, int parentId) {
		this.id = id;
		this.content = content;
		this.author = author;
		this.likes = likes;
		this.shares = shares;
		this.parentId = parentId;
		postedAt = dateTime;
	}
	public Post(String id, String content, String author, String likes,
			String shares, String dateText, String parentId)
			throws DateTimeParseException, NumberFormatException {
		this(Integer.parseInt(id), content, author, Integer.parseInt(likes),
				Integer.parseInt(shares), buildLocalDateTime(dateText),
				Integer.parseInt(parentId));
	}
	
	private static LocalDateTime buildLocalDateTime(String dateText)
			throws DateTimeParseException {
		LocalDateTime answer = null;
		DateTimeFormatter formatter = DEFAULT_DATE_TIME_FORMATTER;
		if (dateText.contains("-")) {
			formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		} else if (dateText.length() == 15) {
			formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");
		}
		answer = LocalDateTime.parse(dateText, formatter);

		return answer;
	}

	private static String decodeQuotedCommas(String line) {
		line = line.replace(Post.COMMA_ENCODING, ",");
		if (line.charAt(0) == '"' && line.charAt(line.length() - 1) == '"') {
			line = line.substring(1, line.length() - 1);
		}
		return line;
	}

	private static String encodeQuotedCommas(String line) {
		boolean inQuotes = false;

		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == '"') {
				inQuotes = !inQuotes;
			}
			if (line.charAt(i) == ',' && inQuotes) {
				line = line.subSequence(0, i) + Post.COMMA_ENCODING
						+ line.subSequence(i + 1, line.length());
				i += Post.COMMA_ENCODING.length() - 1;
			}
		}
		return line;
	}

	public static Post fromCSVRepr(String repr) throws DateTimeParseException,
			NumberFormatException, ArrayIndexOutOfBoundsException {
		repr = encodeQuotedCommas(repr);
		String[] fields = repr.split(",");
		return new Post(fields[0], decodeQuotedCommas(fields[1]),
				decodeQuotedCommas(fields[2]), fields[3], fields[4], fields[5],
				fields[6]);
	}

	public String getAuthorId() {
		return author;
	}

	public String getContent() {
		return content;
	}

	public int getId() {
		return id;
	}

	public int getLikes() {
		return likes;
	}

	public int getParentId() {
		return parentId;
	}

	public LocalDateTime getPostedAt() {
		return postedAt;
	}

	public int getShares() {
		return shares;
	}

	public String toCSVRepr() {
		String modContent = content;
		if (content.contains(",")) {
			modContent = '"' + content + '"';
		}
		return id + "," + modContent + "," + author + "," + likes + "," + shares
				+ "," + postedAt + "," + parentId;
	}
}
