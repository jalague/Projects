
public class Cat extends Pet {
	private boolean indoor;

	
	public Cat(String name, String size, String gender, String city, String shelter, String age, String breed, boolean in){
		super(name, size, gender, city, shelter, age, breed);
		indoor=in;
		type= "Cat";
	}
	protected boolean getIndoor(){
		return indoor;
	}
	protected void setIndoor(boolean indoor){
		this.indoor=indoor;
	}
	
	public String toString(){
		 String parent = super.toString();
		 String fin;
		 if(indoor){
			 fin = "Indoors Cat"; 
		 }
		 else{
			 fin = "Outdoors Cat";
		 }
		 return "Cat," + " " + parent +", " + fin;
	 }
}
