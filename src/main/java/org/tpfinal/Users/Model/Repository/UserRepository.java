package org.tpfinal.Users.Model.Repository;

import com.google.gson.Gson;
import org.mindrot.jbcrypt.BCrypt;
import org.tpfinal.Interfaces.IntRepository;
import org.tpfinal.Users.Model.Entity.User;

import java.util.Map;
import java.util.UUID;

public class UserRepository implements IntRepository<User> {
    private static final String PATH = "src/main/resources/user.json";
    private Gson gson = new Gson();
    private Map<UUID, User> userMap;




    @Override
    public void add(User add) {

    }

    @Override
    public User search(String searchField) {
        for(Map.Entry<UUID, User> entry : userMap.entrySet()){
            if(entry.getKey().equals(searchField)){
                return entry.getValue();
            }
        }
        return null;
    }

    public User userExistance(String searchField){
        for(Map.Entry<UUID, User> entry : userMap.entrySet()){
            if(entry.getValue().getUsername().equals(searchField)){
                return entry.getValue();
            }
        }
        return null;
    }

    public boolean userPasswordCheck(User unckeck){
        User toCheck = userExistance(unckeck.getName());
        if(toCheck!=null){
            return BCrypt.checkpw(unckeck.getPassword(), toCheck.getPassword());
        }
        return false;
    }

    @Override
    public void update(User toUpdate, User updated) {

    }

    @Override
    public void remove(User remove) {

    }
}
