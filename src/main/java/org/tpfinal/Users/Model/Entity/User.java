package org.tpfinal.Users.Model.Entity;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import org.mindrot.jbcrypt.BCrypt;
import org.tpfinal.Abstracts.Person;
import org.tpfinal.Product.Model.Entity.Product;

public class User extends Person {
    private UUID id;
    private String username;
    private String password;
    private String path;
    private Set<Product> productSet;

    public User(String name, String email, UUID id, String username, String password, String path, Set<Product> productSet) {
        super(name, email);
        this.id = UUID.randomUUID();
        this.username = username;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        this.path = "src/main/resources" + username + ".json";
        this.productSet = new TreeSet<>((u1, u2) -> u1.getName().compareTo(u2.getName()));
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
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
