My Project does not use GUI

The main function is in the Driver class, which creates instances of class PetDataBase and Userinterface

my program has classes Birds, Dogs, and Cats which inherit from class Pet. These classes have mostly getters and setters for the attributes of the classes

There are also classes PetDatabase and UserInterface

PetDataBase reads the pet files and the shelter files, splits the lines into useful information and stores that information into arraylists
	This class also has methods which determine if an animal will be available based on the criteria the user enterd (I added a variable to class Pet "isAvailable" that is a boolean value telling whether the pet will be avaialbe based on the users search)
	the method that prints the results of the users search is also in PetDatabase, which iterates over the arraylist of pets and uses the above boolean value to determine whether the pet should be displayed in the result depending on the users search

Class UserInterface asks the user to enter their search criteria and then uses that information in the methods of class PetDatabase