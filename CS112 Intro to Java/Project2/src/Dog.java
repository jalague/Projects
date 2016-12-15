
public class Dog extends Pet{
	private boolean hunts;
	
	
	public Dog(String name, String size, String gender, String city, String shelter, String age, String breed, boolean hunts){
		super(name, size, gender, city, shelter, age, breed);
		this.hunts=hunts;
		type ="Dog";
	}
	protected boolean getHunts(){
		return hunts;
	}
	protected void setHunts(boolean hunts){
		this.hunts=hunts;
	}
	
	public String toString(){
		 String parent = super.toString();
		 String fin;
		 if(hunts){
			 fin = "Hunts"; 
		 }
		 else{
			 fin = "Does not hunt";
		 }
		 return "Dog," + " " + parent +", " + fin;
	 }
	
}
