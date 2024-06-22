package org.tpfinal.Admin.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import org.tpfinal.Users.Model.Repository.UserRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    private UserRepository userRepository;

    @FXML
    private Button exitButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button updateButton;

    @FXML
    private TableView<?> userList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userRepository = new UserRepository();
    }

    public void updatePassword(){

    }

}

