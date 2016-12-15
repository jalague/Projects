package hmk4;

import java.util.Arrays;

public class Driver {
	public static void main(String[] args){
		Square s1= new Square(5);
		Circle c1 = new Circle(2.5);
		Circle c2=new Circle(1);
		Area[] shapes= {s1,c1,c2};
		for (int i= 0; i < shapes.length; i++) {
			System.out.println(shapes[i]);
		}
		Arrays.sort(shapes);
		for (int i= 0; i < shapes.length; i++) {
			System.out.println(shapes[i]);
		}
	}
}
