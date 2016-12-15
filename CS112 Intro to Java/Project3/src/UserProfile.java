

public class UserProfile extends Profile {
	
	String friends;
	
	UserProfile(String type,String n, String file, String stat, String friendls){
		super(type, n, file, stat);
		
		String[] splitF= friendls.split(",");
		friends="";
		for(int c = 0; c< splitF.length; c++){
			friends= friends + splitF[c].trim() +",";
		}
		
	}
			
	public String getFriends(){
		return friends;
	}
	public void addFriends(String friend){
		friends = friends  +friend +",";
	 
	}
	//For debugging
	public String toString(){
		return  super.toString() + ", " + friends;
	}
	
}
