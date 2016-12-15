package hmk1;
import java.math.BigDecimal;
public class FactorialHelper {

	public static int factorial(int n){
		int factorial1 = 1;
		
		for (int i= 1; i<=n; i++){
			
			factorial1 = (factorial1)* i;
			
		}
		return factorial1;
		
	}
	public static double approximateE(int numTerms){
		
		double e= 1;
		for (int i=1; i<= numTerms; i++) {
			
			e=e + 1.0/(factorial(i));
			
		
		}
		return e;
	}
	public static void main(String[] args) {
		int fac1= factorial(6);
		double e1 = approximateE(10);
		System.out.println(fac1+ " "+e1);

	}

}
