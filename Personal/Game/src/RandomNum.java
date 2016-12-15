import java.util.*;
import java.util.Random;
public class RandomNum {

	private ArrayList<String> drinks;
	private ArrayList<String> mixers;
	private final double chanceOfShot = .25;
	private final double chanceOfPick = .2;
	private Random random = new Random();
	private HashMap<String,String> numbers;
	
	public RandomNum(ArrayList<String> d,ArrayList<String> m, HashMap<String,String> n){
		drinks=d;
		mixers=m;
		numbers=n;
		
	}
	
	public String randomNumber(){
		
		int num= random.nextInt(numbers.size());
		int i=0;
		String person="Everyone";
		for(String name: numbers.keySet()){
		
			if(i==num){
				person= name;
			}
			i++;
		}
		return person;
	}
	
	public Drink ranodmChugg(){
		Drink theChugg;
		
		boolean shot=false;
		//String ice="none";
		String mixer;
		int drinkNum=random.nextInt(drinks.size());
		String alc=drinks.get(drinkNum);
		if( random.nextDouble()>=.2){
					
			double chance = random.nextDouble();
			if(chance<=chanceOfShot){
				shot=true;
				System.out.println("shot!");
			}
			
			int mixNum = random.nextInt(mixers.size());
			mixer =mixers.get(mixNum);
			
			if(alc.equals("beer")){
				theChugg = new Beer(alc, shot);
			}
			else {
				if(random.nextDouble()>=chanceOfPick){
				theChugg = new Liquor(alc, shot, mixer);
				}
				else{
					if(random.nextBoolean()){
					theChugg= new Pick(alc, true);
					}
					else{
						theChugg= new Pick(alc, false);
	
					}
				}
			}
		}
		else{
			theChugg= new Social(alc, random.nextBoolean());
		}
		
				
//				double i = random.nextDouble();
//				if (i<=.5&& !alc.equals("beer")){
//					ice="ice";
//					System.out.println("ice");
//				}
		
		
		
		return theChugg;
	}
	
	
	
}
