import java.io.Serializable;
<<<<<<< HEAD
import java.sql.Date;

/**
 * Post bean
 */

public class Post implements Serializable {

    private String author;
    private String title;
    private String message;
    private Date date;

    public Post(){

    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }

}
=======
import java.security.SecureRandom;
import java.util.Random;

public class User implements Serializable {
    private String username;
    private String email;
    private String password;
    private String Salt;

    public User(){

    }

    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }



}



/**
    Random ranGen = new SecureRandom();
    byte[] aesKey = new byte[16];  // 16 bytes = 128 bits

    public byte[] getAesKey() {
        return aesKey;
    }ranGen.nextBytes()
}
 */
>>>>>>> bussiness layer is done and servlets are under construction
