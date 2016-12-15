package hmk5;

public class Profile {
	private String name;
	private String picFile;
	private String status;
	
	
	Profile(String n, String file, String stat){
		name=n;
		picFile=file;
		status=stat;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String n){
		name=n;
	}
	public String getPicFile(){
		return picFile;
	}
	
	public void setPicFile(String file){
		picFile=file;
	}
	public String getStatus(){
		return status;
	}
	
	public void setStatus(String stat){
		status=stat;
	}
	public String toString(){
		return name+ ", "+ picFile + ", "+ status;
	}
}
