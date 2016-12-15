package hmk1;

public class PerfectNumbers {
	public static boolean isPerfect(int number){
		int sum = 0;
		for (int x=1; x<number; x++){
			if (number%x ==0){
				sum= sum + x;
			}
		}
		if (sum ==number){
			return true;
		}
		else
			return false;
		
	}
	public static void main(String[] args) {
		for (int z=1; z<=1000;z++){
			if (isPerfect(z)){
				System.out.print(z+",");
			}
		}

	}

}
