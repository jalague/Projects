package hmk1;
import java.util.Scanner;
public class SalesClass {
	public static void main(String[] args){
		
		
		int quantity = 0;
		double total = 0;
		Scanner input = new Scanner(System.in);
		String productid = "go";
		while ( !productid.equals("q")){
			
			System.out.println("Enter the product:");
			productid = input.next();
			if (productid.equals("q")){
				break;
			}
			System.out.println("Enter the quantity");
			quantity = input.nextInt();
			
			
			switch (productid){
			case "1":
				total = total + quantity * 2.38;
				break;
			case "2": 
				total = total+ quantity * 4.50;
				break;
			case "3": 
				total = total + quantity *9.98;
				break;
			case "4":
				total = total + quantity *4.49;
				break;
			case "5":
				total = total + quantity *6.87;
				break;
			
			}
			
			
		}
		
		System.out.printf("The total value sold is: %f%n", total);
		input.close();
	}
}
