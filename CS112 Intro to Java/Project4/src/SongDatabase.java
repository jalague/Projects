import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;


public class SongDatabase {
	ArrayList<Song> allMp3 = new ArrayList<Song>();
	ArrayList<Song> songLibrary = new ArrayList<Song>();
	ArrayList<Song> searchList = new ArrayList<Song>();
	public SongDatabase(){
		ArrayList<Song> allMp3 = new ArrayList<Song>();
		ArrayList<Song> songLibrary = new ArrayList<Song>();
		ArrayList<Song> searchList = new ArrayList<Song>();
	}
	
	public ArrayList<Song> getArray(){
		return allMp3;
	}
	public ArrayList<Song> getLibraryArray(){
		return songLibrary;
	}
	public void clearCurrent(){
		allMp3.clear();
	}
	public void clearCurrentLibrary(){
		songLibrary.clear();
	}
	public ArrayList<Song> getSearchArray(){
		return searchList;
	}
	
	public void traverseMp3(String directory){
		File dir = new File(directory);
		
		//System.out.println("Our directory is: " + directory);
				
		File[] files = dir.listFiles();
		for (int i = 0; i <files.length; i++) {
				String name= files[i].getPath();
				
				if (name.endsWith(".mp3")) {
					try{
					String path= files[i].getPath();
					AudioFile f = AudioFileIO.read(files[i]);
					Tag tag = f.getTag();
					String artist = tag.getFirst(FieldKey.ARTIST);
					String title = tag.getFirst(FieldKey.TITLE);
					String album = tag.getFirst(FieldKey.ALBUM);
					Song newSong = new Song(title, artist ,path, album);
					allMp3.add(newSong);
					}
					catch(Exception e){
						System.out.println("Could not read mp3");
					}
				}
				if(files[i].isDirectory()){
					traverseMp3(name);
				}					
			}
			
	}
	
	public void sortDatabaseTitle(){
		
		for(int k=0; k<allMp3.size(); k++){
			int c= k+1;
			Song temp;
			for(int i=0; i<allMp3.size()-c; i++){
				if(allMp3.get(i).getTitle().compareToIgnoreCase(allMp3.get(i+1).getTitle())>0){
					temp=allMp3.get(i);
					allMp3.set(i, allMp3.get(i+1));
					allMp3.set(i+1, temp);
				}
			}
		}
	}
	public void sortLibraryTitle(){
		for(int k=0; k<songLibrary.size(); k++){
			int c= k+1;
			Song temp;
			for(int i=0; i<songLibrary.size()-c; i++){
				if(songLibrary.get(i).getTitle().compareToIgnoreCase(songLibrary.get(i+1).getTitle())>0){
					temp=songLibrary.get(i);
					songLibrary.set(i, songLibrary.get(i+1));
					songLibrary.set(i+1, temp);
				}
			}
		}
	}
	public void sortSearchTitle(){
		for(int k=0; k<searchList.size(); k++){
			int c= k+1;
			Song temp;
			for(int i=0; i<searchList.size()-c; i++){
				if(searchList.get(i).getTitle().compareToIgnoreCase(searchList.get(i+1).getTitle())>0){
					temp=searchList.get(i);
					searchList.set(i, searchList.get(i+1));
					searchList.set(i+1, temp);
				}
			}
		}
	}
	public ArrayList<Song> sortArtist(ArrayList<Song> list){
		for(int k=0; k<list.size(); k++){
			int c= k+1;
			Song temp;
			for(int i=0; i<list.size()-c; i++){
				String[] fullName=list.get(i).getArtist().split(" ");
				String[] otherFName=list.get(i+1).getArtist().split(" ");
				/*if(fullName.length>1 && otherFName.length>1){
					
					if(fullName[1].compareToIgnoreCase(otherFName[1])<0){
						temp=allMp3.get(i);
						allMp3.set(i, allMp3.get(i+1));
						allMp3.set(i+1, temp);
					}
				}
				else if(fullName.length==1 && otherFName.length>1){
					
					if(fullName[0].compareToIgnoreCase(otherFName[1])<0){
						
						temp=allMp3.get(i);
						allMp3.set(i, allMp3.get(i+1));
						allMp3.set(i+1, temp);
					}
				}
				else if(fullName.length>1 && otherFName.length==1){
					
					if(fullName[1].compareToIgnoreCase(otherFName[0])>0){
						
						temp=allMp3.get(i);
						allMp3.set(i, allMp3.get(i+1));
						allMp3.set(i+1, temp);
					}
				}
				else{*/
						
						if(fullName[0].compareToIgnoreCase(otherFName[0])>0){
							temp=list.get(i);
							list.set(i, list.get(i+1));
							list.set(i+1, temp);
						}
				//}
			}
			
		}
		return list;
	}
	public void saveLibrary(String f){
		//if(f.endsWith(".txt")){
	    File file = new File(f);
		PrintWriter printWriter;
		String theLine=null;
	    try {
			printWriter = new PrintWriter (file);
			printWriter.println(allMp3.size());
			for(int q =0; q<allMp3.size();q++){
				for(int r=0;r<4; r++){
					if(r==0)
						theLine=allMp3.get(q).getTitle();
					else if (r==1)
						theLine=allMp3.get(q).getArtist();
					else if (r==2)
						theLine=allMp3.get(q).getAlbum();
					else if (r==3)
						theLine=allMp3.get(q).getFile();
					
					printWriter.println(theLine);
				}
			
			}
			if (printWriter != null)
				printWriter.close ();  
		
	    }
			catch (FileNotFoundException e) {
			System.out.println("Could not find the file.");
		}
		//}
	}
	
	public void readLibrary(File f){
		String title=null;
		String album=null;
		String artist=null;
		String path=null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));  
			int numSongs= Integer.parseInt(br.readLine());
			for(int w=0; w<numSongs;w++){
							
				for(int l=0; l<4;l++){
					String line= br.readLine();
					if(l==0)
						title = line;
					else if (l==1)
						artist = line;
					else if(l==2)
						album = line;
					else
						 path=line;
				}
			
		    	Song newSong = new Song(title, artist, path, album);
		    	songLibrary.add(newSong);
			}
		    
			
		    br.close();
		    
	    }
		catch(IOException e) {
			System.out.println("Can't read from the file");
		}
	
	}
	
	public ArrayList<Song> searchDatabase(String s, String array){
		ArrayList<Song> theArray=null;
		if(array.equals("f")){
			theArray= allMp3;
		}
		else{
			theArray=songLibrary;
		}
		searchList.clear();
		for(int h=0; h< theArray.size();h++){
			Song aSong= theArray.get(h);
			if(aSong.getTitle().startsWith(s)){
				searchList.add(aSong);
			}
		}
		return searchList;
	}
}
