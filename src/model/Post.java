/*

 * Post
 *
 * Version 1.0
 *
 * 29 Sep 2023
 *
 * © 2023 Sasha Bekier & RMIT.
 */
package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Post class is a data class to used to define the post data required by
 * the app. All Post attributes are declared final as they will not need to be
 * changed after initialisation.
 * 
 * @author Sasha Bekier
 * @version 1.0
 *
 */
public class Post {

	private static final String COMMA_ENCODING = "%COMMA%";
	
	public static final int BY_POST_ID = 0;
	public static final int BY_AUTHOR = 1;
	public static final int BY_LIKES = 2;
	public static final int BY_SHARES = 3;
	public static final int BY_DATE = 4;
	public static final int BY_PARENT = 5;
	
	
	

	/**
	 * takes a string of text, removes any encoded commas such as might be
	 * created by encodeQuotedCommas and removes double quotes if they surround
	 * the whole string. Used to restore encoded strings after splitting a CSV
	 * line and before using the strings to instantiate a Post object.
	 * 
	 * @param line a string that might contain encoded commas
	 * @return the input line that has been modified as described
	 */
	private static String decodeQuotedCommas(String line) {
		line = line.replace(Post.COMMA_ENCODING, ",");
		if (line.charAt(0) == '"' && line.charAt(line.length() - 1) == '"') {
			line = line.substring(1, line.length() - 1);
		}
		return line;
	}

	/**
	 * takes a String of text and replaces any comma occurring within double
	 * quotation marks with a code String as defined by COMMA_ENCODING. Used
	 * prior to splitting a csv line in order to preserve commas within the body
	 * of a text field.
	 * 
	 * @param line this is a line from a CSV file such as would be output from
	 *             toCSVRepr
	 * @return the input line that has been modified as described.
	 */
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

	/**
	 * takes a csv formatted string representation of a post object and
	 * instantiates it.
	 * 
	 * @param repr String representation of a post object as output from
	 *             toCSVRepr
	 * @return the Post object represented by the input string.
	 * @throws DateTimeParseException         from the String constructor.
	 * @throws NumberFormatException          from the String constructor.
	 * @throws ArrayIndexOutOfBoundsException if there are not at least 7 fields
	 * @see toCSVRepr
	 */
	public static Post fromCSVRepr(String repr) throws DateTimeParseException,
			NumberFormatException, ArrayIndexOutOfBoundsException {
		repr = encodeQuotedCommas(repr);
		String[] fields = repr.split(",");
		return new Post(fields[0], decodeQuotedCommas(fields[1]),
				decodeQuotedCommas(fields[2]), fields[3], fields[4], fields[5],
				fields[6]);
	}

	public final String author;
	public final String content;
	public final int id;
	public final int likes;
	public final int parentId;
	public final LocalDateTime postedAt;
	public final int shares;

	/**
	 * Class constructor, takes all parameters with their correct types.
	 * 
	 * @param id       this posts id.
	 * @param content  the text body of this post.
	 * @param author   the author code for this post. The system anonymises the
	 *                 posts however post authors are given a code so that posts
	 *                 by the same author can be identified.
	 * @param likes    the recorded number of likes for this post.
	 * @param shares   the recorded number of shares for this post.
	 * @param dateTime the date and time this post was first made.
	 * @param parentId the post id of the post this post was replying to. If
	 *                 this post is a top level post then parentID should be set
	 *                 to 0.
	 */
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

	/**
	 * Class constructor, takes all parameters as Strings and validates them
	 * before passing them to the correctly typed constructor.
	 * 
	 * @param id       this posts id.
	 * @param content  the text body of this post.
	 * @param author   the author code for this post. The system anonymises the
	 *                 posts however post authors are given a code so that posts
	 *                 by the same author can be identified.
	 * @param likes    the recorded number of likes for this post.
	 * @param shares   the recorded number of shares for this post.
	 * @param dateTime the date and time this post was first made. dateTime will
	 *                 accept Strings of the formats permitted by
	 *                 IOValidator.getDate().
	 * @param parentId the post id of the post this post was replying to. If
	 *                 this post is a top level post then parentID should be set
	 *                 to 0.
	 * @throws DateTimeParseExeption will be thrown if a dateTime String that
	 *                               cannot be interpreted is submitted.
	 * @throws NumberFormatException will be thrown if any of the parameters -
	 *                               id, likes, shares, or parentId - are unable
	 *                               to be parsed as an integer.
	 */
	public Post(String id, String content, String author, String likes,
			String shares, String dateText, String parentId)
			throws DateTimeParseException, NumberFormatException {
		this(Integer.parseInt(id), content, author, Integer.parseInt(likes),
				Integer.parseInt(shares),
				buildLocalDateTime(dateText),
				Integer.parseInt(parentId));
	}

	/**
	 * Used by SmaManager's sort comparators in order to sort by id.
	 * 
	 * @return this posts id as an integer.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Used by SmaManager's sort comparators in order to sort by likes.
	 * 
	 * @return this posts recorded number of likes as an integer.
	 */
	public int getLikes() {
		return likes;
	}

	/**
	 * used by SmaManager's sort comparators in order to sort by date and time
	 * posted.
	 * 
	 * @return this posts LocalDateTime for time of original posting.
	 */
	public LocalDateTime getPostedAt() {
		return postedAt;
	}

	/**
	 * Used by SmaManager's sort comparators in order to sort by shares.
	 * 
	 * @return this posts recorded number of shares as an integer.
	 */
	public int getShares() {
		return shares;
	}

	/**
	 * used to prepare a String representation of the post suitable to be added
	 * to a CSV save file.
	 * 
	 * @return a CSV formatted text string.
	 */
	public String toCSVRepr() {
		String modContent = content;
		if (content.contains(",")) {
			modContent = '"' + content + '"';
		}
		return id + "," + modContent + "," + author + "," + likes + "," + shares
				+ "," + postedAt + "," + parentId;
	}
	public static final DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = DateTimeFormatter
			.ofPattern("dd/MM/yyyy HH:mm");

	/**
	 * takes a String of one of the following formats: "dd/MM/yyyy HH:mm",
	 * "d/MM/yyyy HH:mm" or "yyyy-MM-ddTHH:mm" and returns a LocalDateTime.
	 *
	 * @param dateText a String that conforms to one of the required formats.
	 * @return the parsed LocalDateTime.
	 * @throws DateTimeParseException if the dateText String is not of a valid
	 *                                format.
	 */
	public static LocalDateTime buildLocalDateTime(String dateText)
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

	public String getContent() {
		return content;
	}

	public String getAuthorId() {
		return author;
	}

	public int getParentId() {
		return parentId;
	}
}
