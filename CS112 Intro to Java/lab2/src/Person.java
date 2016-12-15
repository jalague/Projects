
public class Person {
	
	    protected String firstName;
	    protected String lastName;
	    protected float age;
	    
	   public Person(String firstN, String lastN, int age) {
		firstName = firstN;
		lastName = lastN;
		this.age = age;
	    }

	    public String getFirstName() {
		return firstName;
	    }
	    
	     public String getLastName() {
		return lastName;
	    }

	     public void setFirstName(String firstN) {
		 firstName = firstN;
	    }

	    public void setLastName(String lastN) {
		 lastName = lastN;
	    }

	    public float getAge() {
		return age;
	    }

	    public void setAge(float age) {
		this.age = age;
	    }

	    public String toString() {

		return firstName + " " + lastName;
	    }
	}

