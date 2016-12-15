
public class Driver {

	public static void main(String[] args) {
		GraduateStudent jim = new GraduateStudent("jim", "cram", 34, 1234, 2004, "Book1");
		Student bob = new Student("greg", "bullman", 68, 4321, 2009);
		System.out.println(jim.getAge());
		System.out.println(jim.getGraduationYear());
		System.out.println(jim.getThesisTitle());
		System.out.println(bob);
	}

}
