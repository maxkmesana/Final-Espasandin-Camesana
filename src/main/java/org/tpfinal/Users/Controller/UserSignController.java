package org.tpfinal.Users.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.tpfinal.Exception.EmailTakenException;
import org.tpfinal.Exception.EmptyFieldException;
import org.tpfinal.Exception.PasswordLimitException;
import org.tpfinal.Users.Model.Entity.User;
import org.tpfinal.Users.Model.Repository.UserRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class UserSignController{
    private UserRepository userRepository;

    private static User temp;

    public UserSignController() {
    }

    public static User getTemp() {
        return temp;
    }

    @FXML
    private Button button;

    @FXML
    private TextField emailField;

    @FXML
    private Label label;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @FXML
    public User createUserLogic() throws EmptyFieldException, PasswordLimitException, EmailTakenException {
        String name = this.nameField.getText();
        String email = this.emailField.getText();
        String username = this.usernameField.getText();
        String password = this.passwordField.getText();
        if(name.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()){
            throw new EmptyFieldException();
        }
        if(password.length()<8 || password.length()>12){
            throw new PasswordLimitException();
        }
        if(userRepository.search(email)!=null){
            throw new EmailTakenException();
        }
        User newUser = new User(name, email, username, password);
        userRepository.saveToJson();
        return newUser;
    }

    @FXML
    public void createUser(ActionEvent event){
        try{
            User newUser = createUserLogic();
            userRepository.add(newUser);
            temp = newUser;
            Stage stage = (Stage) this.button.getScene().getWindow();
            stage.close();
        } catch (EmptyFieldException | PasswordLimitException | EmailTakenException e) {
            Alert exception = new Alert(Alert.AlertType.ERROR);
            exception.setHeaderText(null);
            exception.setTitle("Error");
            exception.setContentText(e.getMessage());
            exception.showAndWait();
        }
    }
}
