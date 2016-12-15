package hmk1;
import java.util.Scanner;
public class HealthProfileTest {

	public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	System.out.println("Enter first name:");
	String firstName= input.nextLine();
	System.out.println("Enter last name:");
	String lastName= input.nextLine();
	System.out.println("Enter gender:");
	String gender= input.nextLine();
	System.out.println("Enter age:");
	int age= input.nextInt();
	System.out.println("Enter height:");
	int height= input.nextInt();
	System.out.println("Enter weight:");
	int weight= input.nextInt();
	
	HealthProfile profile1 = new HealthProfile(firstName, lastName, gender, height, weight, age);
	System.out.println(profile1.toString());
	int maxHeart = profile1.computeMaximumHeartRate();
	double BMI = profile1.computeBMI();
	String heartRange = profile1.computeTargetHeartRateRange();
	System.out.println("Max Heart Rate is: "+ maxHeart+ " "+ "BMI is: "+ BMI+ " "+ "Heart Range is: "+ heartRange);
	}
	

}
