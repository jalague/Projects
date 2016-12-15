package hmk4;

public class Square implements Area, Comparable<Area>{
	 private double side;
	 
	 Square(double side){
		 this.side=side;
	 }
	 
	public double getArea(){
		 double area= (side*side);
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
	      return  side+ " Area: "+getArea();
	   }
}
