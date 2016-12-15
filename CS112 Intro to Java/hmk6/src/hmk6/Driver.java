package hmk6;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Driver {
	public static void main(String[] args) {
		ArrayList<File> filesFound = new ArrayList<File>();
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		FileBrowser t = new FileBrowser();
		t.traverse(args[0], filesFound);
		
		for (int i=0; i<filesFound.size();i++){
			System.out.println(filesFound.get(i));
		}
		// EXTRA CREDIT
		map.put(".txt", 0);
		map.put(".java", 0);
		map.put(".pdf", 0);
		t.traverseCount(args[0], map);
		
		for(String key: map.keySet()){
			System.out.println(key);
			System.out.println(map.get(key));
		}
		
		
	}
}
