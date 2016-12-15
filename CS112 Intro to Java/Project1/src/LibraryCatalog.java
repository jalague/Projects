import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class LibraryCatalog {
	
	private ArrayList<Book> books = new ArrayList<Book>();
	
	public LibraryCatalog() {
		 ArrayList<Book> books = new ArrayList<Book>();
	}
	public Book getBook(String title){
		
		for (int i= 0; i < books.size();i++){
			
			if (books.get(i).getTitle().equals(title)){
				return books.get(i);
			}
		
		}
		return null;	
		}
	
	public void add(Book book){
		books.add(book);
	}
	
	
	public boolean checkoutBook(String title){
			Book theBook= getBook(title);
			if (theBook!=null){
				if (theBook.getIsCheckedOut()){
					return false;
				}
				else{
					theBook.checkoutBook();
					return true;
					}
				}
			else{
					return false;
				}
			
			
	}
	public boolean returnBook(String title){
		Book theBook= getBook(title);
		if (theBook.getIsCheckedOut()){
			theBook.returnBook();
			return true;
		}
		else{
			
			return false;
		}
	}
	public void addBooksFromFile(String filename){

		String line;
		try {
		    BufferedReader br = new BufferedReader(new FileReader(filename));  

		    while ((line = br.readLine()) != null) {
		    	String[] words = line.split("/");
		    	String title = words[0];
		    	String author = words[1];
		    	int year = Integer.parseInt(words[2]);
		    	Book book1= new Book(title, author, year);
		    	books.add(book1);
		    	
		    }
		    br.close();
		}
		catch(IOException e) {
		    System.out.println("Can't read from the file");
		}
	
	}
	public void printAvailableBooks() {
		ArrayList<String> availableBooks = new ArrayList<String>(); 
		for (int i= 0; i < books.size();i++){
			if (!books.get(i).getIsCheckedOut()){
				availableBooks.add(books.get(i).getTitle());
			}
		}
		System.out.println(availableBooks);
	}
	
	//TEST
	/*public static void main(String[] args){
		LibraryCatalog catalog = new LibraryCatalog();
		
		Book book1= new Book("monkey", "james", 1445);
		Book book2= new Book("Finding carrots", "BOB", 5123);
		Book book3= new Book("Cricket ricekts", "sicko jam", 2765);
		catalog.add(book1);
		catalog.add(book3);
		catalog.add(book2);
		catalog.checkoutBook("monkey");
		Book bbbb= catalog.getBook("Finding carrots");
		System.out.println(bbbb.getIsCheckedOut());
		catalog.printAvailableBooks();
	}*/
}