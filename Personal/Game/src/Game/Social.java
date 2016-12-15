package Game;

public class Social extends Drink {
	
	private boolean solo;
	public Social(String alc, boolean s) {
		super(alc);
		this.solo=s;
	}
	
	@Override
	public String toString(){
		if(solo){
			return "Chugger gets to pick someone else to drink "+ alcohol+"!";
		}
		else{
		return "Social! Everyone drink some " + alcohol; 
		}
	}

}
