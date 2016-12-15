package hmk5;
import java.util.HashMap;

public class ProfileDatabase {
	private HashMap<String, Profile> profileMap= new HashMap<String, Profile>();
	
	ProfileDatabase(){
		HashMap<String, Profile> profileMap = new HashMap<String, Profile>();
	}
	
	public void addProfile(String name, Profile p){
		profileMap.put(name, p);
	}
	public Profile find(String name){
		Profile prof= profileMap.get(name);
		return prof;
	}
	
	public void printAll(){
		for(String key: profileMap.keySet()){
			System.out.println(profileMap.get(key));
		}
	}
}
