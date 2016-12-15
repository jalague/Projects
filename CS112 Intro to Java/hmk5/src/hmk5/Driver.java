package hmk5;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args){
		Profile timProfile = new Profile("Tim", "tim.png", "Kayaking in Maui");
		Profile jimProfile = new Profile("Jim", "jim.png", "Hiking");
		Profile samProfile = new Profile("Sam", "sam.png", "Sleeping");
		ProfileDatabase pDir = new ProfileDatabase();
		pDir.addProfile("Tim", timProfile);
		pDir.addProfile("Jim", jimProfile);
		pDir.addProfile("Sam", samProfile);
		
		
		Scanner input = new Scanner(System.in);
		int stop=1;
		while (stop!=0){
			System.out.println("Loook up user's profile by name: ");
			String prof = input.next();
			if (pDir.find(prof)!= null){
				System.out.println("Found user! " + pDir.find(prof));
			}
			else{
				System.out.println("User not found");
			}
			System.out.println("If you wish to stop searching enter 1");
			stop= input.nextInt();
		}
		input.close();
	}
}
