
public class Song {
	private String title;
	private String artist;
	private String file;
	private String album;

	public Song(String t, String a, String f, String al){
		title=t;
		artist=a;
		file=f;
		album=al;
	}

	public String getTitle(){
		return title;
	}
	public String getArtist(){
		return artist;
	}
	public String getFile(){
		return file;
	}
	public String getAlbum(){
		return title;
	}
	public void setTitle(String t){
		title=t;
	}
	public void setArtist(String a){
		artist=a;
	}
	public void setFile(String f){
		file=f;
	}
	public void setAlbum(String al){
		album=al;
	}
}
