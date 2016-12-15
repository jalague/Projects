
public abstract class Pet {
	private String name;
	private String size;
	private String gender;
	private String city;
	private String shelter;
	private String age;
	private String breed;
	private boolean isAvailable;
	protected String type;
	
	public Pet(String name, String size, String gender, String city, String shelter, String age, String breed){
		this.name= name;
		this.size=size;
		this.gender=gender;
		this.city= city;
		this.shelter=shelter;
		this.age=age;
		this.breed=breed;
		isAvailable=false;
	}

	protected String getType(){
		return type;
	}

	protected boolean getIsAvailable(){
		return isAvailable;
	}
	protected void setIsAvailable(boolean available){
		isAvailable=available;
	}
	protected String getName(){
		return name;
	}
	protected void setName(String name){
		this.name=name;
	}
	protected String getSize(){
		return size;
	}
	protected void setSize(String size){
		this.size=size;
	}

	protected String getGender(){
		return gender;
	}
	protected void setGender(String gender){
		this.gender=gender;
	}
	protected String getCity(){
		return city;
	}
	protected void setCity(String city){
		this.city=city;
	}
	protected String getShelter(){
		return shelter;
	}
	protected void setShelter(String shelter){
		this.shelter=shelter;
	}
	protected String getAge(){
		return age;
	}
	protected void setAge(String age){
		this.age=age;
	}
	protected String getBreed(){
		return breed;
	}
	protected void setBreed(String breed){
		this.breed=breed;
	}
	
	public String toString(){
		return name+", "+ breed+", "+ age+", "+ size+", "+gender;
	}
}
