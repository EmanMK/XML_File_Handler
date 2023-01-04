package java_dom_parser;


public class Book {
	
	private String id;
	private String author;
	private String title;
	private String genre;
	private String price;
	private String publishDate;
	private String description;
	
	public Book(String id, String author,String title,String genre, String price,String publishDate,String description) {
		this.id =id;
		this.author=author;
		this.title=title;
		this.genre=genre;
		this.price=price;
		this.publishDate=publishDate;
		this.description=description;
		
	}
	
	
	public String getID() {
		return id;
	}
	public String getAuthor() {
		return author;
	}
	public String getTitle() {
		return title;
	}
	public String getGenre() {
		return genre;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public String getDescription() {
		return description;
	}
	public String getPrice() {
		return price;
	}
	
	
	
	@Override
	public String toString() {
		return "ID: "+id+"\n"+"Author: "+ author +", Title: "+title+", Genre: "+genre+", Price: "+price+", Publish Date: "+publishDate+", Description"+description;
	}

}
