
public class Book {
	private String title;
	private String author;
	private int year;
	private boolean isCheckedOut;
	
	public Book(String title, String author, int year){
		this.title = title;
		this.author =author;
		this.year = year;
		this.isCheckedOut = false;
	}
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	public String getAuthor(){
		return author;
	}
	
	public void setAuthor(String author){
		this.author = author;
	}
	public boolean getIsCheckedOut(){
		return isCheckedOut;
	}

	
	public void checkoutBook(){
		isCheckedOut = true;
	}
	public int getYear(){
		return year;
	}
	
	public void setYear(int year){
		this.year = year;
	}
	
	public void returnBook(){
		isCheckedOut = false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
