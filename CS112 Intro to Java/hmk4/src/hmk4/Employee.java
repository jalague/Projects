

package hmk4;

import java.util.Arrays;
public class Employee implements Comparable<Employee>{
	private String name;
	private float salary;
	
	Employee(String name, float salary){
		this.name=name;
		this.salary=salary;
	}
	

	public String getName(){
		return name;
	}
	public float getSalary(){
		return salary;
	}
	
	public int compareTo(Employee emp){
		if (this.salary<emp.getSalary()){
			return -1;	
		}
		else if (this.salary==emp.getSalary()){
			return 0;
		}
		else if (this.salary>emp.getSalary()){
			return 1;
		}
		else return 5;
	}
	
	public String toString() {
	      return name + ", " + salary;
	   }
	public static void main(String[] args){
		Employee p1 = new Employee("Johnson", 40);
		Employee p2 = new Employee("Zonks", 10);
		Employee p3 = new Employee("Lohnes", 60);
		Employee p4 = new Employee("Acker", 200);
		Employee[] employees = {p1,p2,p3,p4};
		for (int i= 0; i < employees.length; i++) {
			System.out.println(employees[i]);
		}
		Arrays.sort(employees);
		for (int i= 0; i < employees.length; i++) {
			System.out.println(employees[i]);
		}
	}
	
 
	}
