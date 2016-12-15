package Game;

public class Pick extends Drink{
	private boolean poison;
	public Pick(String alc, boolean p ) {
		super(alc);
		this.poison=p;
	}

	@Override
	public String toString(){
		if(poison){
		return "Chugger gets to make their own drink out of anything!";	
		}
		else{
		return  alcohol+ ", but mix it with whatever you want!";
		}
		}
}
