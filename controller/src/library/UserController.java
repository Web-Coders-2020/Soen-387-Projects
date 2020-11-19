package library;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class UserController {
	
	private static ArrayList<User> users;
	
	public static User auth(String id, String pass) {
		try {
			String hashtext = getHashedPassword(pass);
			
			for(int i = 0; i < users.size(); i++) {
				if(users.get(i).getId().equals(id) && users.get(i).getPassword().equals(hashtext)) {
					return users.get(i);
				}
			}
			
			System.out.println("User Authorization Failed");
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static void addUsers(String fileName) {
		users = new ArrayList<>();
		JSONParser jsonParser = new JSONParser();
		
		try (FileReader reader = new FileReader(fileName))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray userList = (JSONArray) obj;
            userList.forEach(user -> parseUserObject((JSONObject)user));
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	
	private static void parseUserObject(JSONObject user)  {
		String id = (String)user.get("id");
		String fullName = (String)user.get("full_name");
		String email = (String)user.get("email");
		String pass = (String)user.get("pass");
		
		User userObj = new User(id, fullName, email, pass);
		users.add(userObj);
	}
	
	private static String getHashedPassword(String inPass) throws NoSuchAlgorithmException {
		String rt = "";
		
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.reset();
		m.update(inPass.getBytes());
		
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1, digest);
		rt = bigInt.toString(16);
		
		return rt;
	}
}
