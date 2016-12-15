package hmk4;

public class Circle implements Area, Comparable<Area> {
	private double radius;
	 
	 Circle(double radius){
		 this.radius=radius;
	 }
	 
	 public double getArea(){
		 double area= 3.1415*(radius*radius);
		 return area;
	 }
	 
	 public int compareTo(Area other){
			if (getArea()<other.getArea()){
				return -1;	
			}
			else if (getArea()==other.getArea()){
				return 0;
			}
			else if (getArea()>other.getArea()){
				return 1;
			}
			else return 5;
		}
	 
	 public String toString() {
	      return radius + " Area: "+getArea();
	   }
}
