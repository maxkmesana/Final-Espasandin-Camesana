package org.tpfinal.Users.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.tpfinal.Exception.EmptyFieldException;
import org.tpfinal.Exception.PasswordLimitException;
import org.tpfinal.Users.Model.Entity.User;
import org.tpfinal.Users.Model.Repository.UserRepository;

public class UserSignController {

    private UserRepository userRepository;

    public UserSignController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserSignController() {
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

    @FXML
    public User createUserLogic() throws EmptyFieldException, PasswordLimitException {
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
        return new User(name, email, username, password);
    }

    @FXML
    public void createUser(ActionEvent event){
        try{
            User newUser = createUserLogic();
            userRepository.add(newUser);
        } catch (EmptyFieldException e) {
            Alert exception = new Alert(Alert.AlertType.ERROR);
            exception.setHeaderText(null);
            exception.setTitle("Error");
            exception.setContentText(e.getMessage());
            exception.showAndWait();
        } catch (PasswordLimitException e) {
            Alert exception = new Alert(Alert.AlertType.ERROR);
            exception.setHeaderText(null);
            exception.setTitle("Error");
            exception.setContentText(e.getMessage());
            exception.showAndWait();
        }
    }

}
