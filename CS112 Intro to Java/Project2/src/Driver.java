
public class Driver {
	
	public static void main(String[] args){
		PetDatabase allPets = new PetDatabase();
		allPets.addPetsFromFile("petsFile");
		allPets.addSheltersFromFile("animalShelters");
		UserInterface ui =new UserInterface();
		ui.mainLoop(allPets);
		
	}

}
