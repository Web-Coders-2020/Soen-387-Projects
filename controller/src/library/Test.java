package library;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;


public class Test {

	public static void main(String[] args) {
		/*ArrayList<Post> posts;
		PostController.setRecent(3);
		
		UserController.addUsers("userdata.json");
		MysqlConnection.init();
		
		String title = "title";
		String user1 = "user1";
		String user2 = "user2";
		Timestamp created = new Timestamp(System.currentTimeMillis());
		Timestamp modified = new Timestamp(System.currentTimeMillis());
		String content = "content";
		
		System.out.println("-------------------- User Auth ----------------");
		if(UserController.auth(user1, "abc") != null) {
			System.out.println("Auth Test Failed");
			return;
		}
		
		System.out.println("-------------------- Post Insert ----------------");
		System.out.println("User Authenticated. Creating Posts for user1");
		for(int i = 0; i < 5; i++) {
			created = new Timestamp(System.currentTimeMillis());
			PostController.insert(title + i, user1, created, content + i);
		}
		
		if(UserController.auth(user2, "abc") != null) {
			System.out.println("Auth Test Failed");
			return;
		}
		
		System.out.println("User Authenticated. Creating Posts for user2");
		for(int i = 0; i < 5; i++) {
			created = new Timestamp(System.currentTimeMillis());
			PostController.insert(title + i, user2, created, content + i);
		}
		
		System.out.println("-------------------- Get Recent ----------------");
		System.out.println("Getting Recent");
		posts = PostController.getRecent();
		for(int i = 0; i < posts.size(); i++) {
			System.out.println(posts.get(i));
		}
		
		System.out.println("Search for " + user2);
		posts = PostController.searchByUser(user2);
		System.out.println("Got " + posts.size() + " posts by " + user2);
		
		System.out.println("-------------------- Deletion Post ----------------");
		System.out.println("Deleting posts by " + user2);
		for(int i = 0; i < posts.size(); i++) {
			PostController.delete(posts.get(i).id);
		}
		
		System.out.println("Getting Recent");
		posts = PostController.getRecent();
		for(int i = 0; i < posts.size(); i++) {
			System.out.println(posts.get(i));
		}
		
		System.out.println("-------------------- Search by user ----------------");
		System.out.println("Search for " + user1);
		posts = PostController.searchByUser(user1);
		System.out.println("Got " + posts.size() + " posts by " + user1);
		
		System.out.println("Updating posts of " + user1 + " by passing incorrect user " + user2);
		for(int i = 0; i < posts.size(); i++) {
			modified = new Timestamp(System.currentTimeMillis());
			
			PostController.update(posts.get(i).id, posts.get(i).title + "_updated", user2, 
					modified, posts.get(i).content + "_updated");
		}
		
		System.out.println("Getting Recent");
		posts = PostController.getRecent();
		for(int i = 0; i < posts.size(); i++) {
			System.out.println(posts.get(i));
		}
		
		try {
			Thread.sleep(2000);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("-------------------- Post Update ----------------");
		System.out.println("Updating posts of " + user1 + " by passing correct user");
		for(int i = 0; i < posts.size(); i++) {
			modified = new Timestamp(System.currentTimeMillis());
			
			PostController.update(posts.get(i).id, posts.get(i).title + "_updated", user1, 
					modified, posts.get(i).content + "_updated");
		}
		
		System.out.println("Getting Recent");
		posts = PostController.getRecent();
		for(int i = 0; i < posts.size(); i++) {
			System.out.println(posts.get(i));
		}
		
		System.out.println("Search for " + user1);
		posts = PostController.searchByUser(user1);
		System.out.println("Got " + posts.size() + " posts by " + user1);
		
		System.out.println("Deleting posts by " + user1);
		for(int i = 0; i < posts.size(); i++) {
			PostController.delete(posts.get(i).id);
		}
		
		System.out.println("Getting Recent");
		posts = PostController.getRecent();
		for(int i = 0; i < posts.size(); i++) {
			System.out.println(posts.get(i));
		}
		
		System.out.println("-------------------- Search by Date ----------------");
		System.out.println("Creating Posts for user1 by manually adding dates in between");
		created = new Timestamp(System.currentTimeMillis());
		Timestamp start = new Timestamp(System.currentTimeMillis());
		Timestamp end = new Timestamp(System.currentTimeMillis());
		for(int i = 0; i < 5; i++) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(created);
			cal.add(Calendar.DAY_OF_WEEK, 14);
			created.setTime(cal.getTime().getTime());
			//ts = new Timestamp(cal.getTime().getTime());
			if(i == 2) {
				start = new Timestamp(created.getTime());
				System.out.println("start: " + start);
			}
			if(i == 4) {
				end = new Timestamp(created.getTime());
				System.out.println("end: " + end);
			}
			PostController.insert(title + i, user1, created, content + i);
		}
		
		System.out.println("Getting Posts by user 1");
		posts = PostController.searchByUser(user1);
		for(int i = 0; i < posts.size(); i++) {
			System.out.println(posts.get(i));
		}
		
		System.out.println("Getting Posts by daterange between " + start + " : " + end);
		posts = PostController.searchByDateRange(start, end);
		for(int i = 0; i < posts.size(); i++) {
			System.out.println(posts.get(i));
		}
		
		System.out.println("Deleting posts by " + user1);
		posts = PostController.searchByUser(user1);
		for(int i = 0; i < posts.size(); i++) {
			PostController.delete(posts.get(i).id);
		}
		
		System.out.println("Getting Recent");
		posts = PostController.getRecent();
		for(int i = 0; i < posts.size(); i++) {
			System.out.println(posts.get(i));
		}
		
		System.out.println("-------------------- Post by hashtag ----------------");
		System.out.println("Creating Posts for user1 with hastag");
		created = new Timestamp(System.currentTimeMillis());
		PostController.insert(title + "_with_hashtag", user1, created, content + " #tag1 #tag2 asdhajsd #tag3");
		
		System.out.println("Post with hashtag: ");
		posts = PostController.searchByUser(user1);
		for(int i = 0; i < posts.size(); i++) {
			System.out.println(posts.get(i) + " - " + posts.get(i).content);
		}
		
		String tag = "tag1";
		System.out.println("Search by tag: " + tag);
		posts = PostController.searchByHashTag(tag);
		for(int i = 0; i < posts.size(); i++) {
			System.out.println(posts.get(i) + " - " + posts.get(i).content);
		}
		
		tag = "tag2";
		System.out.println("Search by tag: " + tag);
		posts = PostController.searchByHashTag(tag);
		for(int i = 0; i < posts.size(); i++) {
			System.out.println(posts.get(i) + " - " + posts.get(i).content);
		}
		
		tag = "tag4";
		System.out.println("Search by tag: " + tag);
		posts = PostController.searchByHashTag(tag);
		for(int i = 0; i < posts.size(); i++) {
			System.out.println(posts.get(i) + " - " + posts.get(i).content);
		}
		
		tag = "tag3";
		System.out.println("Search by tag: " + tag);
		posts = PostController.searchByHashTag(tag);
		for(int i = 0; i < posts.size(); i++) {
			System.out.println(posts.get(i) + " - " + posts.get(i).content);
		}*/
	}

}
