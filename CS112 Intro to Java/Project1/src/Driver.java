
public class Driver {

	public static void main(String[] args) {
		
		LibraryCatalog catalog = new LibraryCatalog();
		catalog.addBooksFromFile ("booklist");
		UserInterface ui= new UserInterface();
		ui.mainLoop(catalog);

	}

}
