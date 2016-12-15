import java.util.Scanner;
public class UserInterface {
	 
	public void mainLoop(LibraryCatalog bc) {
		
		Scanner sc =new Scanner(System.in);
		sc.useDelimiter("\r\n");
		int com = 0;
		while (com != 4){
			System.out.println("Enter 1 to check out a book, 2 to return a book, 3 to print the list of available books, or 4 to exit:");
			com = sc.nextInt();
			if (com ==1){
			System.out.println("Checking out the book? Please enter the title:");	
			String titles = sc.next();
			boolean checkout = bc.checkoutBook(titles);
			if (checkout){
				System.out.println("Book successfully checked out");
				}
				else{
					System.out.println("Book is already checked out");
				}
			}
			else if(com ==2){
			System.out.println("Returning the book? Please enter the title:");
			String titles = sc.next();
			boolean returns= bc.returnBook(titles);
				if (returns){
				System.out.println("Book returned");
				}
				else{
					System.out.println("Book not checked out");
				}
			}
			else if (com==3){
				bc.printAvailableBooks();
			}
			else if (com==4){
			break;
			}
			else{
				System.out.println("Enter a valid command:");
			}
		}

	    sc.close();
	} 
}
