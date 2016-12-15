import java.util.ArrayList;

public class Profile {
	private String name;
	private String picFile;
	private String type;
	private String intstat;
	private ArrayList<Post> posts = new ArrayList<Post>();
	private ArrayList<Post> newsFeed = new ArrayList<Post>();
	private String supports;
	
	Profile(String type, String n, String file, String post){
		name=n;
		picFile=file;
		this.type=type;
		Post post1 = new Post(n,post);
		posts.add(post1);
		newsFeed.add(post1);
		intstat=post;
		supports = "";
	}
	
	public  void supports(String org){
		supports = org + "," +supports;
	}
	public String getSupports(){
		return supports;
	}
	
	public String getIntStat(){
		return intstat;
	}
		
	public ArrayList<Post> getNewsFeed(){
		return newsFeed;
	}
	
	public void addPost(String poster, String post){
		Post post1 = new Post(poster,post);
		if(poster.equals(name)){
		posts.add(post1);
		}
		newsFeed.add(post1);
	}
	public ArrayList<Post> getPosts(){
		return posts;
	}
	
	protected String getType(){
		return type;
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
	public  void addFriends(String nFriend){
	}
	
	public void addSupporters(String name){
	}
	public String getFriends(){
		return "";
	}
	public String getSupporters(){
		return "";
	}
	
	public String toString(){
		return name+ ", "+ picFile ;
	}
	public String getNumberOfLikes(){
		return "";
	}
	public String getPhoneNumber(){
		return "";
	}
	public String getAddress(){
		return "";
	}
	public void setNumberOfLikes(String num){
		
	}
}
