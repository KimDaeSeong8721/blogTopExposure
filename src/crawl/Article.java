package crawl;

public class Article {
	String blog_url ;
	String blog_title;
	String post_date;
	String post_title;
	int average_visitor_count ;
    String rank ;
	String keyword ;
	int img_count ;  //add
	int repKeywordTitleCount ;
	int repKeywordContentsCount;
	int contentsCount;
	
	public int getContentsCount() {
		return contentsCount;
	}

	public int getRepKeywordTitleCount() {
		return repKeywordTitleCount;
	}

	public int getRepKeywordContentsCount() {
		return repKeywordContentsCount;
	}

	public String getBlog_url() {
		return blog_url;
	}

	public String getPost_date() {
		return post_date;
	}

	public String getPost_title() {
		return post_title;
	}
	public String getBlog_title() {
		return blog_title;
	}
	public int getAverage_visitor_count() {
		return average_visitor_count;
	}
	public int getImg_count() {  //add
		return img_count;        //add
	}
	public String getRank() {
		return rank;
	}
}
