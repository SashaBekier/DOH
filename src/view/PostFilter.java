package view;

import java.time.LocalDateTime;

public class PostFilter {
	
		static boolean[] ascending = {true, true, true, true, true, true, true};
		static Integer postIdF = null;
		static String authorIdF = null;
		static boolean showRepliesF = true;
		static LocalDateTime fromDate = null;
		static LocalDateTime toDate = null;
		
		public static void clearFilters() {
			postIdF = null;
			authorIdF = null;
			showRepliesF = true;
			fromDate = null;
			toDate = null;
		}
	
}
