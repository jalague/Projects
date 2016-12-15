package Game;

public class Beer extends Drink{

	boolean shotgun;
	
	public Beer(String alc, boolean shotgun) {
		super(alc);
		this.shotgun=shotgun;
		
	}
	
	@Override
	public String toString(){
		if(shotgun){
		return "Beer, shotgun it! (if it's in a can)";
		}
		else{
			return "Beer, no 'shotgunning' necessary, but you still have to Chugg...";
		}
	}

}
