import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ProfileDatabase {
	
	
	private HashMap<String, Profile> profileMap= new HashMap<String, Profile>();
	ProfileDatabase(){
		
		HashMap<String, Profile> profileMap= new HashMap<String, Profile>();
		 HashMap<String, String> friendMap= new HashMap<String, String>();
		 HashMap<String, String> supporterMap= new HashMap<String, String>();
	}

	public Profile find(String name){
		
			
		if( profileMap.get(name)!= null){
		Profile prof= profileMap.get(name);
		
		return prof;
		}
		
		else{
			Profile notF = new Profile("not Found", name, "Not Found", "Not Found");
			return notF;
		}
		
	
		
	}
	public void addProfile(String name, Profile p){
		profileMap.put(name, p);
	}
	
	
	public void addProfilesFromFile(String filename){
		String line;
		
		try {
		   
			BufferedReader br = new BufferedReader(new FileReader(filename));  
		    
		   
		    while ((line = br.readLine()) != null) {
		    	
		    	String[] words = line.split("; ");
		   
		    	if (words[0].equals("user")){
		    		
		    		
		    		Profile newUser = new UserProfile(words[0], words[1], words[2], words[3], words[4]);
		    		profileMap.put(words[1], newUser);
		    	
		    		
		    	}
		    	
		    	else {
		    	    		
		    		Profile newOrg = new OrganizationProfile(words[0], words[1], words[2], words[3], words[4], words[5], words[6], words[7]);
		    		profileMap.put(words[1], newOrg);
		    		   		
		    	}
		   
		    
		    }
		    // adds the names of the organizations to the intial supporters
		    for(String key: profileMap.keySet()){
		    	if (profileMap.get(key).getType().equals("organization")){
		    		Profile org = profileMap.get(key);
		    		String[] supporties = org.getSupporters().split(",");
	    			
		    		for(int d=0; d< supporties.length && d>=0 ;d++){
		    			
		    			Profile person = profileMap.get(supporties[d].trim());
	    				System.out.println(person);
	    				person.supports(org.getName());
	    				
	    			}	
		    	}
		}
		    br.close();
		    
		    }
		catch(IOException e) {
		    System.out.println("Can't read from the file");
		}
			
	}

	public void addFriend(String yourName, String newFName){
		if( profileMap.get(newFName)!= null){
		Profile person= profileMap.get(yourName);
		 Profile newFriend= profileMap.get(newFName);
		 
		 person.addFriends(newFName);
		 newFriend.addFriends(yourName);
		}
		 
	}
	public void addSupporter(String organization, String supportName){
		 Profile org= profileMap.get(organization);
		 //Profile person= profileMap.get(supportName);
		 
		 org.addSupporters(supportName);
		// person.addSupports();
		
		 
	}
	
	public void addToFriendsNews(Profile p, String post){
		String friends = p.getFriends();
		String[] friendz = friends.split(",");
		
		for(int s =0; s< friendz.length; s++){
			Profile afriend= profileMap.get(friendz[s]);
			afriend.addPost(p.getName(),post);
			
		}
	
	}
	public void addToSupportersNews(Profile org, String post){
		String supporters=org.getSupporters();
		String[] supporterz = supporters.split(",");
		for(int q =0; q< supporterz.length; q++){	
			Profile asupporter = profileMap.get(supporterz[q].trim());
			asupporter.addPost(org.getName(), post);
		}
		}
	public void printAll(){
		for(String key: profileMap.keySet()){
			System.out.println(profileMap.get(key));
		}
	}
}
