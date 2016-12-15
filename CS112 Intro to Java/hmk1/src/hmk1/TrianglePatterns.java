package hmk1;

public class TrianglePatterns {
	public static void printPattern1(int n){
		
		for (int i=1; i<=n; i++){
			
			for (int x=1; x<=i;x++){
				System.out.print("*");
			}
			System.out.println("");
		}
	}
	public static void printPattern2(int n){
		int d = 0;
		for (int i = n; i>=1; i--){
			
			for(int v=1; v<=d; v++ ){
				System.out.print(" ");
			}
			for(int x=i; x>=1; x--){
				System.out.print("*");
			
			}
			d++;
			System.out.println("");
		}
		
	}
	public static void main(String[] args) {
		printPattern1(7);
		printPattern2(7);

	}

}
