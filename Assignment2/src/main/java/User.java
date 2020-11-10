import java.io.Serializable;
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
