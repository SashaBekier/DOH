package view;

import java.time.LocalDateTime;

public class PostFilter {
	
		static boolean[] ascending = {true, true, true, true, true, true};
		static Integer postIdF = null;
		static String authorIdF = null;
		static boolean showRepliesF = true;
		static LocalDateTime fromDate = null;
		static LocalDateTime toDate = null;
		
		public static void clearFilters() {
			for(boolean asc: PostFilter.ascending) {
				asc = !asc;
			}
			PostFilter.postIdF = null;
			PostFilter.authorIdF = null;
			PostFilter.showRepliesF = true;
			PostFilter.fromDate = null;
			PostFilter.toDate = null;
		}
	
}
