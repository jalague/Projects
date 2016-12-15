package hmk6;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class FileBrowser {
	
	public static void traverse(String directory, ArrayList<File> resArr) {
		
		File dir = new File(directory);
		System.out.println("Our directory is: " + directory);
		
		
		File[] files = dir.listFiles();
		for (int i = 0; i <files.length; i++) {
				String name= files[i].getPath();
				
				if (name.endsWith(".txt")) {
					
					resArr.add(files[i]);
				}
				if(files[i].isDirectory()){
					traverse(name, resArr);
				}
				
					
			}
			
		}
	public static void traverseCount(String dir, HashMap<String,Integer> h){
		/*  Could not figure out how to check/ return the file
		 * type of each file so had to hard code file types
		 * HOWEVER
		 * if there is a method to return file types I would
		 * iterate over the directory and for each file check it's type, first checking to see if the type
		 * was already in the hash map and if it was already, then I would "put" the file type into the hash map, but with a count of plus 1.
		 *  if the file type was not yet in the hash  map (the hash map would return null) and I would "put" the new file type into the map
		 *  with an initial value of zero.
		 *  then if the file was a directory it would simply recursively call the traverseCount method
		 */
		
		
		File d = new File(dir);
		System.out.println("Our directory is: " + dir);
		
		
		File[] files = d.listFiles();
		for (int i = 0; i <files.length; i++) {
				String name= files[i].getPath();
				if(files[i].isDirectory()){
					traverseCount(name, h);
				}
				if (name.endsWith(".txt")) {
						
						Integer v= h.get(".txt");
						Integer w = v+1;
						h.put(".txt", w);
					
					
				}
				if (name.endsWith(".java")) {
					Integer v= h.get(".java");
								
					Integer w = v+1;					
					h.put(".txt", w);
					
				}
				
				if (name.endsWith(".pdf")) {
					Integer v= h.get(".pdf");
								
					Integer w = v+1;					
					h.put(".pdf", w);
					
				}
					
			}
				
	}
	


}