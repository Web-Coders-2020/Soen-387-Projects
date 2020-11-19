package library;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlConnection {
	
	public static Connection conn = null;
	public static Statement statement = null;
	public static PreparedStatement preparedStatement  = null;
	public static ResultSet resultSet = null;
	
	public static void init() {
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/servlet?" +
				                                   "user=root&password=");
			statement = conn.createStatement();
			//resultSet = statement.executeQuery("select * from feedback.comments");
			//writeResultSet(resultSet);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void writeResultSet(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String user = resultSet.getString("myuser");
            String website = resultSet.getString("webpage");
            String summary = resultSet.getString("summary");
            Date date = resultSet.getDate("datum");
            String comment = resultSet.getString("comments");
        }
    }
	
	private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }
        } catch (Exception e) {

        }
    }
}
