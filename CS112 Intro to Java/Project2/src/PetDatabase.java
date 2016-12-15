import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class PetDatabase {
	ArrayList<Pet> pets = new ArrayList<Pet>();
	ArrayList<String> shelterNames = new ArrayList<String>();
	ArrayList<String> shelterAddress = new ArrayList<String>();
	ArrayList<String> shelterPhone = new ArrayList<String>();
	ArrayList<String> shelterInfo = new ArrayList<String>();

	public PetDatabase(){
		ArrayList<Pet> pets = new ArrayList<Pet>();
		ArrayList<String> shelterNames = new ArrayList<String>();
		ArrayList<String> shelterAddress = new ArrayList<String>();
		ArrayList<String> shelterPhone = new ArrayList<String>();
		ArrayList<String> shelterInfo = new ArrayList<String>();

	}
	
	
    
    
	
	public void addPetsFromFile(String filename){
	
		String line;
		try {
		    BufferedReader br = new BufferedReader(new FileReader(filename));  
		    
		    
		    while ((line = br.readLine()) != null) {
		    	
		    	String[] words = line.split(",");
		    	for(int f=0; f<words.length;f++){
		    		words[f]=words[f].trim();
		    	}
		    	String type = words[0];
		    	if (type.equals("Dog")){
		    		Pet dog1 = new Dog(words[2], words[3], words[5], words[7], words[8], words[4], words[1], Boolean.parseBoolean(words[6]));
		    		pets.add(dog1);

		    		}
		    	
		    	else if (type.equals("Cat")){
		    		Pet cat1 = new Cat(words[2], words[3], words[5], words[7], words[8], words[4], words[1], Boolean.parseBoolean(words[6]));
		    		pets.add(cat1);
		    		
		    	}
		    	else if (type.equals("Bird")){
		    		Pet bird1 = new Bird(words[2], words[3], words[5], words[7], words[8], words[4], words[1], Boolean.parseBoolean(words[6]));
		    		pets.add(bird1);
		    		    	
		    	
		    		}
		    }
		    br.close();
		    
		}
		catch(IOException e) {
		    System.out.println("Can't read from the file");
		}
	}



	public void addSheltersFromFile(String filename){
		
		String line;
		try {
		    BufferedReader br = new BufferedReader(new FileReader(filename));  
		    
		    
		    while ((line = br.readLine()) != null) {
		    	line = line.trim();
		    	String[] words = line.split(";");
		    	for(int f=0; f<words.length;f++){
		    		words[f]=words[f].trim();
		    	}
		    	shelterNames.add(words[0]);
		    	shelterAddress.add(words[1]);
		    	shelterPhone.add(words[2]);
		    	shelterInfo.add(words[3]);

		    }
		    br.close(); 
		}
		catch(IOException e) {
		    System.out.println("Can't read from the file");
		}
	}

	
	public void printAvailableBreeds(String type){
		if(type.equals("Dog") || type.equals("")|| type.equals("any")){
			System.out.println("Poodle, Retriever, Bulldog, Terrier, German Shepard");
		}
		if(type.equals("Cat")|| type.equals("")|| type.equals("any")){
			System.out.println("Domestic Long Hair, Domestic Short Hair, Siamese, Siberian");
		}
		if(type.equals("Bird")|| type.equals("")|| type.equals("any")){
			System.out.println("Parrot, Canary");
		}
		
	}
	
	public void makeAvailable(String typ, String brd, String siz, String ag, String cit){
		for (int m=0; m<pets.size();m++){
			Pet aPet = pets.get(m);
			

			if (typ.equals(aPet.getType()) || typ.equals("") ||typ.equals("any"))
			{
				if(brd.equals(aPet.getBreed()) || brd.equals("") || brd.equals("any")){
					if(siz.equals(aPet.getSize()) || siz.equals("") || siz.equals("any")){
						if(ag.equals(aPet.getAge()) || ag.equals("")|| ag.equals("any")){
							if(cit.equals(aPet.getCity()) || cit.equals("") || cit.equals("any")){
								aPet.setIsAvailable(true);
								//System.out.println(aPet);
							}
						}
					}
				}
			
			
				
			}
			
			
		}
	}
	public void resetAvailability(){
		for (int m=0; m<pets.size();m++){
			Pet aPet = pets.get(m);
			aPet.setIsAvailable(false);
		}
	}
	
	public void printAvailablePets(){
		ArrayList<Pet> availablePets = new ArrayList<Pet>();
		for(int d =0; d<pets.size();d++){
			Pet thePet = pets.get(d);
			if(thePet.getIsAvailable()){
				availablePets.add(thePet);
			}
		}
		
		for (int l =0; l<availablePets.size(); l++) 
		{ 
			System.out.println(availablePets.get(l)); 
			
			Pet availPet= availablePets.get(l);
			
			for (int w=0; w< shelterNames.size(); w++){
				String aShelter= shelterNames.get(w);
				
				if( aShelter.equals(availPet.getShelter())){
					System.out.print("Shelter: ");
					System.out.println(aShelter);
					System.out.println(shelterAddress.get(w));
					System.out.println(shelterPhone.get(w));
				}
			}
		}
		
	}
}