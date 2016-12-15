
public class GraduateStudent extends Student{
	protected String thesisTitle;
	
	public GraduateStudent(String fName, String lName, int age, int id, int gradYear, String tTitle){
		super(fName, lName, age, id, gradYear);
		 thesisTitle= tTitle;
	}
	
	 public String getThesisTitle() {
			return thesisTitle;
		    }
	 public void setThesisTitle(String tTitle) {
		 thesisTitle = tTitle;
	    }
}
