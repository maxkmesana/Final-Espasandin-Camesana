package org.tpfinal.Users.Model.Entity;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import org.mindrot.jbcrypt.BCrypt;
import org.tpfinal.Abstracts.Person;
import org.tpfinal.Product.Model.Entity.Product;
import org.tpfinal.Users.Model.Repository.UserRepository;

public class User extends Person {
    private UUID id;
    private String username;
    private String password;
    private String path;
    private Set<Product> productSet;
    //private static ObservableSet<Product> productSet;

    public User(String name, String email, String username, String password) {
        super(name, email);
        this.id = UUID.randomUUID();
        this.username = username;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        this.path = "src/main/resources/" + username + ".json";
        this.productSet = new HashSet<>();
    }

    public User(String username, String password) {
        super(null, null);
        this.username = username;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPath() {
        return path;
    }


    public Set<Product> getProductSet() {
        return productSet;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }

}
