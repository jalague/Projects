import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class Setup {
	
	private float gameTime;
	
	private float textTime;
	
	private ArrayList<String> ingredients;
	
	private ArrayList<String> liquor;
	
	private ArrayList<String> mixers;
	
	private HashMap<String,String> providers;
	
	//private HashMap<String, HashMap<String, String>> drinkMenu; // Mapped as (Liquor, (name, mixer))
	private Scanner input;
	
	public Setup(){
		this.input=input;
		
		gameTime=60;
		textTime=10;
		 input = new Scanner(System.in);
		providers = new HashMap<String,String>();
		providers.put("tmobile", "@tmomail.net");
		providers.put("att", "@txt.att.net");
		providers.put("virginmobile", "@vmoble.com");
		providers.put("cingular", "@cingularme.com");
		providers.put("sprint", "@messaging.sprintpcs.com");
		providers.put("verizon", "@vtext.com");
		providers.put("nextel", "@messaging.nextel.com");
		providers.put("uscellular", "@email.uscc.net");
		providers.put("suncom", "@tms.suncom.com");
		providers.put("powertel", "@ptel.net");
		providers.put("alltel", "@message.alltel.com");
		providers.put("projectfi", "@msg.fi.google.com");
		
		System.out.println("Hello, Welcome to Chugg!");
		System.out.println();
		System.out.println("Let's begin by setting up the game and then we'll get to Chuggin");
	}
	
	public int setGameTime(){
		System.out.println();
		System.out.println("Now please enter below the duration of the game (in minutes):");
		int time=input.nextInt();
		
		gameTime=time;
		return time;
	}
	public int setChuggInterval(){
		System.out.println();
		System.out.println("Now please enter the time in between chuggs (in whole minutes):");
		int time=input.nextInt();
		
		textTime=time;
		return time;
		
		
	}
	
	public boolean randomIntervals(){
		System.out.println();
		System.out.println("If you would like to play with the drinks sent out at random times within the intverals please enter 'yes', if you would like the drink to be sent out on a consistent basis please enter 'no'");
		String bool= input.next();
		if(bool.toLowerCase().equals("yes")){
			return true;
		}
		else{
			return false;
		}
	}
	
	public ArrayList<String> setupDrinks(){
		
		System.out.println("First, please enter the type of alcohol you have available. Enter each new alcohol on a new line and then enter 'done' when complete");
		System.out.println("For example:");
		System.out.println("vodka");
		System.out.println("whiskey");
		System.out.println("beer");
		System.out.println("done");

		System.out.println();
		System.out.println("Now it's your turn to enter what drinks you have below...");
		 ArrayList<String> drinks = new ArrayList<String>();
		String drank= input.next();
		if(!drank.equals("done")){
			
			drinks.add(drank);
		while(!drank.equals("done")){
			
			drank= input.next();
			if (drank.equals("done")){
				break;
			}
			else{
				drinks.add(drank);
			}
		}
		}
		this.liquor=drinks;
		System.out.println();
		System.out.println(drinks.toString());
		return drinks;
	}
 	
	
	public ArrayList<String> setupMixers(){
		System.out.println("Now, please enter all the drink mixers you have available. Enter each new mixer on a new line and then enter 'done' when complete");
		System.out.println("For example:");
		System.out.println("club soda");
		System.out.println("orange juice");
		System.out.println("Coca-cola");
		System.out.println("done");

		System.out.println();
		System.out.println("Now it's your turn to enter what mixers you have below...");
		 ArrayList<String> mixers = new ArrayList<String>();
		String drank= input.next();
		if(!drank.equals("done")){
			
			mixers.add(drank);
		while(!drank.equals("done")){
			
			drank= input.next();
			if (drank.equals("done")){
				break;
			}
			else{
				mixers.add(drank);
			}
		}
		}
		this.mixers=mixers;
		System.out.println();
		System.out.println(mixers.toString());
		return mixers;
		
	}
	
	public HashMap<String, String> setupNumbers(){
		HashMap<String,String> numbers = new HashMap<String,String>();
		System.out.println("Finally, we need the names and phone numbers of all the players.");
		System.out.println("Please enter a each players name, phone number, and phone service provider, all seperated by a comma.");
		System.out.println("Example: ");
		System.out.println("John, 4145553333, T-mobile");
		System.out.println("Meagan, 4146789101, Sprint");
		System.out.println("Now please enter the players below and enter 'done' when finished:");
		String player = "start";
		
		while(!player.equalsIgnoreCase("done")){
			
			player= input.nextLine();
			String[] players = player.split(",");
			if(players.length==3){
//				for(int x=0;x<players.length;x++){
//					System.out.println(x);
//					System.out.println(players[x]);
//				}
				
				players[0]=players[0].trim();
				players[1]=players[1].trim().replaceAll("-", "").replaceAll(" ", "");
	
				players[2]=players[2].replaceAll("-", "").replaceAll("&", "").replaceAll(" ", "").toLowerCase().trim();
				String ending = providers.get(players[2]);
				numbers.put(players[0], players[1]+ending);
			}
			
			
		}
		System.out.println(numbers);
		if(player.equalsIgnoreCase("start")){
			System.out.println("You didn't enter any names, please start the whole process over");
		}
		else{
			System.out.println("The game has begun");
		}
		return numbers;
		
	}
	
	
}
