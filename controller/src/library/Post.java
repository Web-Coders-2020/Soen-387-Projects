package library;


import java.sql.Blob;
import java.sql.Timestamp;

public class Post {
	
	public Integer id = null;
	public String title;
	public String userId;
	public Timestamp created;
	public Timestamp modified;
	public String content;
	public Blob data;
	
	@Override
	public String toString() {
		String rt = "";
		rt += id + ": " + userId + ": " + title + ": " + created + ": " + modified;
		return rt;
	}
}
