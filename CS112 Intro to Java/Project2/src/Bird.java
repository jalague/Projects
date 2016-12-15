
public class Bird extends Pet {
	private boolean talks;

	
	public Bird(String name, String size, String gender, String city, String shelter, String age, String breed, boolean talks){
		super(name, size, gender, city, shelter, age, breed);
		this.talks=talks;
		type ="Bird";
	}
	protected boolean getTalks(){
		return talks;
	}
	protected void setTalks(boolean talks){
		this.talks=talks;
	}
	
	
	public String toString(){
		 String parent = super.toString();
		 String fin;
		 if(talks){
			 fin = "Talks"; 
		 }
		 else{
			 fin = "Does not Talk";
		 }
		 return "Bird," + " " + parent +", " + fin;
	 }
}
