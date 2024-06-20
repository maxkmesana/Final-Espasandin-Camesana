package org.tpfinal.Users.Model.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.mindrot.jbcrypt.BCrypt;
import org.tpfinal.Interfaces.IntRepository;
import org.tpfinal.Users.Model.Entity.User;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class UserRepository implements IntRepository<User>{
    private static final String PATH = "src/main/resources/user.json";
    private Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    private Map<String, User> userMap;

    public UserRepository() {
        loadFromJson();
    }

    public void loadFromJson(){
        try(Reader reader = new FileReader(PATH)){
            Type collectionType = new TypeToken<Map<String, User>>() {}.getType();
            userMap = gson.fromJson(reader, collectionType);
            if(userMap==null){
                userMap = new TreeMap<>(String::compareTo);
            }
        }catch(FileNotFoundException e){
            userMap = new TreeMap<>(String::compareTo);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void saveToJson(){
        try(Writer writer = new FileWriter(PATH)){
            gson.toJson(userMap, writer);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void add(User add) {
        userMap.put(add.getUsername(), add);
        saveToJson();
    }

    @Override
    public User search(String searchField) {
        for(Map.Entry<String, User> entry : userMap.entrySet()){
            User temp = entry.getValue();
            if(temp.getEmail().equals(searchField)){
                return entry.getValue();
            }
        }
        return null;
    }

    public User userExistance(String searchField){
        for(Map.Entry<String, User> entry : userMap.entrySet()){
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
