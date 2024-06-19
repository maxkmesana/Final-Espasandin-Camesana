package org.tpfinal.Users.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.tpfinal.Exception.EmptyFieldException;
import org.tpfinal.Users.Model.Entity.User;
import org.tpfinal.Users.Model.Repository.UserRepository;
import javafx.event.*;

import java.io.IOException;

public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserController() {
    }

    @FXML
    private Label label;

    @FXML
    private Label label2;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signInButton;

    @FXML
    private TextField usernamefield;

    @FXML
    public User userLoginExc() throws EmptyFieldException {
        String username = this.usernamefield.getText();
        String password = this.passwordField.getText();
        if (username.isEmpty() || password.isEmpty()) {
            throw new EmptyFieldException();
        } else {
            User toCheck = new User(username, password);
            if (userRepository.userPasswordCheck(toCheck)) {
                //TODO;
            }
        }
        return null;
    }

    @FXML
    public void userLogin(ActionEvent event) {
        try {
            User newUserLogin = userLoginExc();
        } catch (EmptyFieldException e) {
            Alert exception = new Alert(Alert.AlertType.ERROR);
            exception.setHeaderText(null);
            exception.setTitle("Error");
            exception.setContentText(e.getMessage());
            exception.showAndWait();
        }
    }
}


