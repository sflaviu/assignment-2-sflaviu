package bookstore.entity;

import javax.persistence.*;

/**
 * Created by Catalysts on 8/9/2015.
 */
@Entity
@Table(name = "users")
public class User{
    @Id
    private String username;
    private String password;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}