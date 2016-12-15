
public class Post  {
	
	private long time;
	private String post;
	private String poster;
	
	Post(String poster, String post){
	time=System.currentTimeMillis();
	this.post=post;
	this.poster=poster;
	}
	
	
	public long getTime(){
		return time;
	}
	public String getPost(){
		return post;
	}
	
	public String getPoster(){
		return poster;
	}
	// Used in debugging
	public String toString(){
		return post +" , " + poster;
	}
}
