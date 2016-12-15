package Game;
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
		
	}
	


	
	
	
	public HashMap<String, String> parseNumbers(String input){
		HashMap<String,String> numbers = new HashMap<String,String>();
		
		String[] players = input.split("\n");
		String player ="start";
		for(int y=0; y<players.length;y++){
			
			player= players[y];
			String[] playersplit = player.split(",");
			if(playersplit.length==3){
//				for(int x=0;x<players.length;x++){
//					System.out.println(x);
//					System.out.println(players[x]);
//				}
				
				playersplit[0]=playersplit[0].trim();
				playersplit[1]=playersplit[1].trim().replaceAll("-", "").replaceAll(" ", "");
	
				playersplit[2]=playersplit[2].replaceAll("-", "").replaceAll("&", "").replaceAll(" ", "").toLowerCase().trim();
				String ending = providers.get(playersplit[2]);
				numbers.put(playersplit[0], playersplit[1]+ending);
			}
			
			
		}
		System.out.println(numbers);
		if(player.equalsIgnoreCase("start")){
			System.out.println("You didn't enter any names, please start the whole process over");
		}
		else{
			
			System.out.println("Thank you for entering the names");
		}
		return numbers;
		
	}
	
	
}
