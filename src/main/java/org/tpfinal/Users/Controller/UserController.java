package org.tpfinal.Users.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import org.tpfinal.Exception.EmptyFieldException;
import org.tpfinal.StockManagerLanding;
import org.tpfinal.Users.Model.Entity.User;
import org.tpfinal.Users.Model.Repository.UserRepository;
import javafx.event.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    private UserRepository userRepository;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userRepository = new UserRepository();
    }

    @FXML
    public User userLoginLogic() throws EmptyFieldException {
        String username = this.usernamefield.getText();
        String password = this.passwordField.getText();
        if (username.isEmpty() || password.isEmpty()) {
            throw new EmptyFieldException();
        } else {
            User toCheck = new User(username, password);
            if (userRepository.userPasswordCheck(toCheck) ||
                    (UserSignController.getTemp() != null &&
                            username.equals(UserSignController.getTemp().getUsername()) &&
                            BCrypt.checkpw(password, UserSignController.getTemp().getPassword()))) {
                try{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/tpfinal/stockManagerLanding.fxml"));
                    Parent root = loader.load();
                    StockManagerLanding controller = loader.getController();
                    controller.setUsername(toCheck.getUsername());
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) this.usernamefield.getScene().getWindow();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                }catch(IOException e){
                    Alert exception = new Alert(Alert.AlertType.ERROR);
                    exception.setHeaderText(null);
                    exception.setTitle("Error");
                    exception.setContentText(e.getMessage());
                    exception.showAndWait();
                }
            } else {
                Alert exception = new Alert(Alert.AlertType.ERROR);
                exception.setHeaderText(null);
                exception.setTitle("Error");
                exception.setContentText("Invalid username or password, try again.");
                exception.showAndWait();
            }/*else if(username == "Admin" && password == "Admin123"){

            }*/
        }
        return null;
    }

    @FXML
    public void userLogin(MouseEvent event) {
        login();
    }

    @FXML
    void enterPressedLogin(KeyEvent event) {
        login();
    }

    public void login(){
        try {
            User newUserLogin = userLoginLogic();
        } catch (EmptyFieldException e) {
            Alert exception = new Alert(Alert.AlertType.ERROR);
            exception.setHeaderText(null);
            exception.setTitle("Error");
            exception.setContentText(e.getMessage());
            exception.showAndWait();
        }
    }

    private void showModalWindow(Scene scene) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    public void createUser(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/tpfinal/userSign.fxml"));
            Parent root = loader.load();
            UserSignController signController = loader.getController();
            Scene scene = new Scene(root, 600, 600);
            showModalWindow(scene);
        }catch(IOException e){
            Alert exception = new Alert(Alert.AlertType.ERROR);
            exception.setHeaderText(null);
            exception.setTitle("Error");
            exception.setContentText(e.getMessage());
            exception.showAndWait();
        }
    }

}


