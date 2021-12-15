package JSON;

public class News {
	private String date;
	private String content;
	
	public News() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public News(String date, String content) {
		super();
		this.date = date;
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

}
