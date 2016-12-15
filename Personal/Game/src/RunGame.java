import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;



public class RunGame {

	ArrayList<String> drinks;
	ArrayList<String> mixers;
	int gameTime;
	int delay;
	HashMap<String,String> numbers;
	
	public RunGame(ArrayList<String> drinks,ArrayList<String> mixers,int gameTime, int delay, boolean intervals,HashMap<String,String> numbers){
		
		
		this.drinks=drinks;
		this.mixers=mixers;
		this.gameTime=gameTime;
		this.delay=delay;
		this.numbers=numbers;
		
		
		Email e = new Email();
//		e.sendOpeningEmail(numbers, gameTime, delay);
//		try{
//			TimeUnit.SECONDS.sleep(20);
//			}
//			catch(Exception u){
//				System.out.println("Exception in sleep");
//			}
		
	 
	 RandomNum rn;
	 int i=1;
	 rn= new RandomNum(drinks,mixers, numbers);
	 Random random = new Random();
	 int randomChugg;
	 double chanceOfHype= .333;
	 //---------------INITIAL CHUGG-----------------------------
	 System.out.println("initial");
	
//		 -------------------------------------------------------
		 System.out.println("here we go");
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
			 TimeUnit.MINUTES.sleep(randomChugg);
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
