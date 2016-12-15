
import java.util.Scanner;


public class UserInterface {
	public void mainLoop(PetDatabase p) {
		Scanner sc =new Scanner(System.in);
		sc.useDelimiter("\r\n");
		int com= 0;
		String type;
		String breed;
		String age;
		String size;
		String city;
		while (com != 1){
			System.out.println("Enter the type of pet: Dog, Cat, Bird or any");
			type = sc.next();
			System.out.println("Enter a breed, the following breeds are available:");
			p.printAvailableBreeds(type);
			breed=sc.next();
			System.out.println("Enter an age (young, adult, senior):");
			age= sc.next();
			System.out.println("Enter a size (small, medium, large):");
			size= sc.next();
			System.out.println("Enter a city in CA");
			city= sc.next();
			p.makeAvailable(type, breed, size, age, city);
			System.out.println("Found the Avaialbe pets: ");
			System.out.println("------------------------------------");
			
			p.printAvailablePets();
			
			
			
			
			
			
			
			System.out.println("Enter '0' to do another search, otherwise enter '1' ");
			p.resetAvailability();
			com = sc.nextInt();
		
		
		}
	sc.close();
	}
	
}
