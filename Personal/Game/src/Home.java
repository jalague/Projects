import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class Home {

	public static ArrayList<String> numbers;
	
	
	public static void main(String args[]){
		
		
		Setup s = new Setup();
		
		ArrayList<String> drinks=s.setupDrinks();
				 		
		
		ArrayList<String> mixers=s.setupMixers();
		
		
		int gameTime=s.setGameTime();
		int delay=s.setChuggInterval();
		boolean intervals= s.randomIntervals();

		HashMap<String,String> numbers = s.setupNumbers();
		

		
		 
		 Email e = new Email();
			e.sendOpeningEmail(numbers, gameTime, delay);
			try{
				TimeUnit.SECONDS.sleep(20);
				}
				catch(Exception u){
					System.out.println("Exception in sleep");
				}
			
		 
		 RandomNum rn;
		 int i=1;
		 rn= new RandomNum(drinks,mixers, numbers);
		 Random random = new Random();
		 int randomChugg;
		 double chanceOfHype= .333;
		 for(int x=0;x<gameTime;x+=delay){
			 double hype =random.nextDouble();
			 
			 if(intervals){
				 randomChugg = random.nextInt(delay);
				 
			 }
			 else{
				 randomChugg= delay;
			 }
			 if (hype<= chanceOfHype){
			
			 
			 try{
					TimeUnit.MINUTES.sleep(randomChugg-1);
					}
					catch(Exception l){
						System.out.println("Exception in sleep");
				}
			 e.sendHypeEmail(numbers);
			 try{
					TimeUnit.MINUTES.sleep(1);
					}
					catch(Exception l){
						System.out.println("Exception in sleep");
				}
			 
			 
			 }
			 else{
				 try{
				 TimeUnit.MINUTES.sleep(randomChugg-1);
				}
				catch(Exception v){
					System.out.println("Exception in sleep");
				}
			 }
			 
			 
				String name= rn.randomNumber();
				//System.out.println("the one chosen "+ to);
				 Drink theMix= rn.ranodmChugg();
				 e.sendChuggEmail(theMix.toString(), name, numbers, i);
				 i++;
				 
			int timeLeft = delay-randomChugg;
			try{
				TimeUnit.MINUTES.sleep(timeLeft);
				}
				catch(Exception l){
					System.out.println("Exception in sleep");
			}
						

		 }
	}
}
