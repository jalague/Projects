package hmk3;
import java.util.Scanner;
import java.util.Random;
public class GuessingGame {
	public static void guessNumber(){
		int guess = 10;
		Random r = new Random();
		int rand = r.nextInt(10);
		Scanner input = new Scanner(System.in);
		while (rand != guess){
			System.out.print("Guess a number:");
			 guess = input.nextInt();
			if (rand > guess)
				System.out.println("Guess Higher");
			else if (rand < guess)
				System.out.println("Guess Lower");
		}
		System.out.println("Correct!");
		
	}
	public static void main(String[] args){
		guessNumber();
}
	
}
