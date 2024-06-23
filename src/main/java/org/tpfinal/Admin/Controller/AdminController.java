package org.tpfinal.Admin.Controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.tpfinal.Exception.EmptyFieldException;
import org.tpfinal.Users.Model.Entity.User;
import org.tpfinal.Users.Model.Repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    private UserRepository userRepository;

    @FXML
    private Button exitButton;

    @FXML
    private TextField newPasswordField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button removeButton;

    @FXML
    private Button updateButton;

    @FXML
    private TableView<User> userList;

    @FXML
    private TextField usernameField;

    private User selected;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userRepository = new UserRepository();
    }

    @FXML
    public void selectuser(MouseEvent event){
        selected = this.userList.getSelectionModel().getSelectedItem();
        if(selected!=null) {
            this.usernameField.setText(selected.getUsername());
            this.passwordField.setText(selected.getPassword());
        }
    }

    @FXML
    public void changePassword(){
        User userSelected = this.userList.getSelectionModel().getSelectedItem();
        if(selected==null) {
            Alert exception = new Alert(Alert.AlertType.ERROR);
            exception.setHeaderText(null);
            exception.setTitle("Error");
            exception.setContentText("No user selected, please select the one you want to update.");
            exception.showAndWait();
        }else{
            try{
                User aux = new User(userSelected.getUsername(), userSelected.getPassword());
                if(!userRepository.getUserMap().containsKey(userSelected.getUsername())){
                    userRepository.getUserMap().put(userSelected.getUsername(), aux);
                    this.userList.setItems(FXCollections.observableArrayList((User) userRepository.getUserMap()));
                    this.userList.refresh();
                    infMessage("Password updated successfuly");
                }
            }catch(EmptyFieldException e){
                Alert exception = new Alert(Alert.AlertType.ERROR);
                exception.setHeaderText(null);
                exception.setTitle("Error");
                exception.setContentText(e.getMessage());
                exception.showAndWait();
            }
        }
    }

    public void infMessage(String message){
        Alert exception = new Alert(Alert.AlertType.INFORMATION);
        exception.setHeaderText(null);
        exception.setTitle("Error");
        exception.setContentText(message);
        exception.showAndWait();
    }


}

