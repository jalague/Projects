
public class Liquor extends Drink{
	
	boolean shot;
	String mixer;
	public Liquor(String alc, boolean shot, String mixer) {
		super(alc);
		this.shot=shot;
		if(shot){
			this.mixer="none";
			
		}
		else{
			this.mixer=mixer;
		}

	}
	
	@Override 
	public String toString(){
		if(shot){
			return "Chugg a shot of "+ alcohol+ "!!";
		}
		else{
			return "Chugg "+ alcohol+ " mixed with "+ mixer;
		}
	}

}
