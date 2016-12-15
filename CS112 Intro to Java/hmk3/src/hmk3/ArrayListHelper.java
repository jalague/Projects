package hmk3;
import java.util.ArrayList;

public class ArrayListHelper {
	
	
	public static ArrayList<Integer> mult(ArrayList<Integer> x , ArrayList<Integer> y){
		ArrayList<Integer> z = new ArrayList<Integer>();
		for (int i = 0; i < x.size() ;i++ ){
			 
			int value = x.get(i) * y.get(i);
			z.add(i, value);
		}
		return z;
	}
	public static void main(String[] args) {
		ArrayList<Integer> x = new ArrayList<Integer>();
		x.add(1);
		x.add(2);
		ArrayList<Integer> y = new ArrayList<Integer>();
		y.add(2);
		y.add(3);
		
		
		ArrayList<Integer> f= mult(x, y);
		for (int i = 0; i < f.size() ;i++ ){
			System.out.println(f.get(i));
		}
	}
}
