package library;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class PostController {
	
	public static int MAX_RECENT = 10;
	
	public static void setRecent(int recent) {
		MAX_RECENT = recent;
	}
	
	public static void insert(String title, String userId, Timestamp created, String content, Blob data) {
		
		if(MysqlConnection.conn == null) {
			MysqlConnection.init();
		}
		
		try {
			PreparedStatement st = MysqlConnection.conn.prepareStatement(
					"insert into  servlet.post values (default, ?, ?, ?, ?, ?, ?)"
					);
			
			st.setString(1, title);
			st.setString(2, userId);
			st.setTimestamp(3, created);
			st.setTimestamp(4, created);
			st.setString(5, content);
			st.setBlob(6, data);
			st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void delete(int id) {
		PreparedStatement st;
		try {
			if(MysqlConnection.conn == null) {
				MysqlConnection.init();
			}
			st = MysqlConnection.conn.prepareStatement(
					"delete from servlet.post where id = ?");
			
			st.setInt(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void update(int id, String title, String userId, Timestamp modified, String content, Blob data) {
		try {
			if(MysqlConnection.conn == null) {
				MysqlConnection.init();
			}
			PreparedStatement st = MysqlConnection.conn.prepareStatement(
					"SELECT * from servlet.post where id = ?"
					);
			st.setInt(1, id);
			ResultSet resultSet = st.executeQuery();
			
			Post tmpPost = new Post();
			
			while (resultSet.next()) {
				tmpPost.id = resultSet.getInt("id");
				tmpPost.userId = resultSet.getString("user_id");
				break;
			}
			
			if(tmpPost.id == null) {
				System.out.println("No post found with id " + id);
				return;
			}
			
			if(tmpPost.userId.equals(userId) == false) {
				System.out.println("Unauthorized. User " + userId + " is not owner of this post. Cannot udpate");
				return;
			}
			
			st = MysqlConnection.conn.prepareStatement(
					"update servlet.post SET title = ?, modified = ?, content = ?, data = ? where id = ?");
			
			st.setString(1, title);
			st.setTimestamp(2, modified);
			st.setString(3, content);
			st.setBlob(4, data);
			st.setInt(5, id);
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Post searchById(int id) {
		PreparedStatement st;
		
		try {
			if(MysqlConnection.conn == null) {
				MysqlConnection.init();
			}
			st = MysqlConnection.conn.prepareStatement(
					"SELECT * from servlet.post where id = ?");
			
			st.setInt(1, id);
			ResultSet resultSet = st.executeQuery();
			
			while (resultSet.next()) {
				Post tmpPost = new Post();
				tmpPost.id = resultSet.getInt("id");
				tmpPost.title = resultSet.getString("title");
				tmpPost.created = resultSet.getTimestamp("created");
				tmpPost.modified = resultSet.getTimestamp("modified");
				tmpPost.userId = resultSet.getString("user_id");
				tmpPost.content = resultSet.getString("content");
				tmpPost.data = resultSet.getBlob("data");
				return tmpPost;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static ArrayList<Post> searchByUser(String userId) {
		PreparedStatement st;
		ArrayList<Post> tmpPosts = new ArrayList<>();
		
		try {
			if(MysqlConnection.conn == null) {
				MysqlConnection.init();
			}
			st = MysqlConnection.conn.prepareStatement(
					"SELECT * from servlet.post where user_id = ?"
					);
			
			st.setString(1, userId);
			ResultSet resultSet = st.executeQuery();
			
			while (resultSet.next()) {
				Post tmpPost = new Post();
				tmpPost.id = resultSet.getInt("id");
				tmpPost.title = resultSet.getString("title");
				tmpPost.created = resultSet.getTimestamp("created");
				tmpPost.modified = resultSet.getTimestamp("modified");
				tmpPost.userId = userId;
				tmpPost.content = resultSet.getString("content");
				tmpPost.data = resultSet.getBlob("data");
				tmpPosts.add(tmpPost);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tmpPosts;
	}
	
	public static ArrayList<Post> searchByDateRange(Timestamp start, Timestamp end) {
		PreparedStatement st;
		ArrayList<Post> tmpPosts = new ArrayList<>();
		
		try {
			if(MysqlConnection.conn == null) {
				MysqlConnection.init();
			}
			st = MysqlConnection.conn.prepareStatement(
					"SELECT * from servlet.post where created between ? and ?");
			
			st.setTimestamp(1, start);
			st.setTimestamp(2, end);
			ResultSet resultSet = st.executeQuery();
			
			while (resultSet.next()) {
				Post tmpPost = new Post();
				tmpPost.id = resultSet.getInt("id");
				tmpPost.title = resultSet.getString("title");
				tmpPost.created = resultSet.getTimestamp("created");
				tmpPost.modified = resultSet.getTimestamp("modified");
				tmpPost.userId = resultSet.getString("user_id");
				tmpPost.content = resultSet.getString("content");
				tmpPost.data = resultSet.getBlob("data");
				tmpPosts.add(tmpPost);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tmpPosts;
	}
	
	public static ArrayList<Post> searchByHashTag(String tag) {
		PreparedStatement st;
		ArrayList<Post> tmpPosts = new ArrayList<>();
		tag = "#" + tag.trim();
		
		try {
			if(MysqlConnection.conn == null) {
				MysqlConnection.init();
			}
			st = MysqlConnection.conn.prepareStatement(
					"SELECT * from servlet.post where content LIKE ?");
			
			st.setString(1, "%"+tag+"%");
			ResultSet resultSet = st.executeQuery();
			
			while (resultSet.next()) {
				Post tmpPost = new Post();
				tmpPost.id = resultSet.getInt("id");
				tmpPost.title = resultSet.getString("title");
				tmpPost.created = resultSet.getTimestamp("created");
				tmpPost.modified = resultSet.getTimestamp("modified");
				tmpPost.userId = resultSet.getString("user_id");
				tmpPost.content = resultSet.getString("content");
				tmpPost.data = resultSet.getBlob("data");
				tmpPosts.add(tmpPost);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tmpPosts;
	}
	
	public static ArrayList<Post> getRecent() {
		PreparedStatement st;
		ArrayList<Post> tmpPosts = new ArrayList<>();
		
		try {
			if(MysqlConnection.conn == null) {
				MysqlConnection.init();
			}
			st = MysqlConnection.conn.prepareStatement(
					"SELECT * from servlet.post order by modified DESC LIMIT " + MAX_RECENT);
			
			ResultSet resultSet = st.executeQuery();
			
			while (resultSet.next()) {
				Post tmpPost = new Post();
				tmpPost.id = resultSet.getInt("id");
				tmpPost.title = resultSet.getString("title");
				tmpPost.created = resultSet.getTimestamp("created");
				tmpPost.modified = resultSet.getTimestamp("modified");
				tmpPost.userId = resultSet.getString("user_id");
				tmpPost.content = resultSet.getString("content");
				tmpPost.data = resultSet.getBlob("data");
				tmpPosts.add(tmpPost);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tmpPosts;
	}
}
