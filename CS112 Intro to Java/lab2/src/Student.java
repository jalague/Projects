
public class Student extends Person {
	protected int studentID;
	protected int graduationYear;
	
	public Student(String fName, String lName, int age, int id, int gradYear){
		super(fName, lName, age);
		studentID = id;
		graduationYear = gradYear;
	}
	
	 public int getStudentID() {
			return studentID;
		    }
	 public void setStudentID(int id) {
		 studentID = id;
	    }
	 public int getGraduationYear() {
			return graduationYear;
		    }
	 public void setGraduationYear(int gradYear) {
		 graduationYear = gradYear;
	    }
	 public String toString(){
		 String parent = super.toString();
		 return parent+ " " +studentID;
	 }
}
