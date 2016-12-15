package hmk1;

public class HealthProfile {
	private String fname;
	private String lname;
	private String gender;
	private int height;
	private int weight;
	private int age;
	
	public HealthProfile(String fname, String lname, String gender, int height, int weight, int age){
		this.fname = fname;
		this.lname = lname;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
		this.age = age;
	}
	
	public String getFname(){
		return fname;
	}
	public String getLname(){
		return lname;
	}
	public String getGender(){
		return gender;
	}
	public int getHeight(){
		return height;
	}
	public int getWeight(){
		return weight;
	}
	public int getAge(){
		return age;
	}
	public void setFname(String fname){
		this.fname = fname;
	}
	public void setLname(String lname){
		this.lname= lname;
	}
	public void setGender(String gender){
		this.gender = gender;
	}
	public void setHeight(int height){
		this.height = height;
	}
	public void setWeight(int weight){
		this.weight = weight;
	}
	public void setAge(int age){
		this.age = age;
	}
	public int computeMaximumHeartRate(){
		int maxHeart = 220 - age;
		return maxHeart;
	}
	public String computeTargetHeartRateRange(){
		int maxHeart = computeMaximumHeartRate();
		double minTRate = (maxHeart-age) * 0.5;
		double maxTRate = (maxHeart-age) *0.85;
		String heartRange = minTRate +" - "+ maxTRate;
		return heartRange;
	}
	public double computeBMI(){
		double BMI = (weight*703) / (height *height);
				return BMI;
	}
	public String toString(){
		return fname + " " + lname+ " " + height+ "in "+ weight+ "lbs";
	}
	
}
