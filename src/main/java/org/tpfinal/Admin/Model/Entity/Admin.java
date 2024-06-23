package org.tpfinal.Admin.Model.Entity;
import org.mindrot.jbcrypt.BCrypt;

public class Admin {
    private String username;
    private String password;

    public Admin() {
        this.username = "admin";
        this.password = BCrypt.hashpw("admin123", BCrypt.gensalt());
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
