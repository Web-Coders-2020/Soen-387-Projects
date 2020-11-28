package abir.shah.messageBoardSystem.domain.entity;



public class User {

    private String id;
    private String fullName;
    private String email;
    private String hashedPassword;

    public User(String id, String fullName, String email, String hashedPassword) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.hashedPassword = hashedPassword;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }
}
