


public class OrganizationProfile extends Profile{
	//private String announcement;
	private String numberOfLikes;
	//private ArrayList<Profile> supporters = new ArrayList<Profile>();
	private String supporters;
	private String address;
	private String phoneNumber;
	
	
	public OrganizationProfile(String type, String n, String file, String number, String address, String announce, String numberOfL, String supporters){
		super(type, n, file, announce);
		
		numberOfLikes=numberOfL;
		this.supporters = supporters;
		phoneNumber=number;
		this.address=address;
		
		
	}
		
	public String getPhoneNumber(){
		return phoneNumber;
	}
	
	public void setPhoneNumber(String num){
		phoneNumber=num;
	}
	
	public String getAddress(){
		return address;
	}
	
	public void setAddress(String add){
		address= add;
	}
	public String getNumberOfLikes(){
		return numberOfLikes;
	}
	
	public void setNumberOfLikes(String num){
		int sum=Integer.parseInt(numberOfLikes) + Integer.parseInt(num);
		numberOfLikes=String.valueOf(sum);
		
	}
		
	public void addSupporters(String name){
		supporters = supporters + ", " +name;
	}
	public String getSupporters(){
		return supporters;
	}
	//For debugging
	public String toString(){
		return super.toString() + ", " + address + ", " + phoneNumber+ " " +numberOfLikes+ " " ;
	}
}
