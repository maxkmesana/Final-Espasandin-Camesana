package org.tpfinal.Users.Model.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.mindrot.jbcrypt.BCrypt;
import org.tpfinal.Interfaces.IntStrategy;
import org.tpfinal.Product.Controller.ProductController;
import org.tpfinal.Product.Model.Entity.Product;
import org.tpfinal.Product.Model.Repository.ProductRepository;
import org.tpfinal.StockFile.Model.Repository.IntStrategyAdapter;
import org.tpfinal.Users.Model.Entity.User;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;


public class UserRepository{
    ProductRepository productRepository;
    private static String PATH = "src/main/resources/user.json";
    private Gson gson;
    private Map<String, User> userMap;

    public Map<String, User> getUserMap() {
        return userMap;
    }

    public UserRepository() {
        productRepository = new ProductRepository();
        gson = new GsonBuilder()
                .registerTypeAdapter(IntStrategy.class, new IntStrategyAdapter())
                .setPrettyPrinting()
                .serializeNulls()
                .create();
        loadFromJson();
        IntStrategyAdapter adapter = IntStrategyAdapter.getInstance();
    }

    public void loadFromJson(){
        try(Reader reader = new FileReader(PATH)){
            Type collectionType = new TypeToken<Map<String, User>>() {}.getType();
            userMap = gson.fromJson(reader, collectionType);
            if(userMap==null){
                userMap = new TreeMap<>(String::compareTo);
            }else{
                loadColection();
            }
        }catch(FileNotFoundException e){
            userMap = new TreeMap<>(String::compareTo);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadColection(){
        for(Map.Entry<String, User> entry : userMap.entrySet()){
            userMap.put(entry.getKey(), entry.getValue());
            for(Product product : entry.getValue().getProductSet()){
                productRepository.add(product);
            }
        }
    }

    public void saveToJson(){
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls()
                .registerTypeAdapter(IntStrategy.class, new IntStrategyAdapter())
                .create();
        try(Writer writer = new FileWriter(PATH)){
            gson.toJson(userMap, writer);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void add(User add) {
        userMap.put(add.getUsername(), add);
        saveToJson();
    }

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
        return userMap.get(searchField);
    }

    public boolean userPasswordCheck(User unckeck){
        User toCheck = userExistance(unckeck.getUsername());
        if(toCheck!=null){
            return BCrypt.checkpw(unckeck.getPassword(), toCheck.getPassword());
        }
        return false;
    }

    public void remove(User remove){
        userMap.remove(remove.getUsername(), remove);
    }

}
