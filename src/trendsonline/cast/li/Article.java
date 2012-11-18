package trendsonline.cast.li;

public class Article {
	private String title;
	private String link;
	private String date;
	private String description;
	private String content;
	
	public Article(String title, String link, String date, String description, String content){
		this.title = title;
		this.link = link;
		this.date = date;
		this.description = description;
		this.content = content;
	}
	
	public String getTitle(){
		return title;
	}
	public String getLink(){
		return link;
	}
	public String getDate(){
		return date;
	}
	public String getDescription(){
		return description;
	}
	public String getContent(){
		return content;
	}
}
