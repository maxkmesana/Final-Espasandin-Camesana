package org.tpfinal.Admin.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.tpfinal.Exception.EmptyFieldException;
import org.tpfinal.Main;
import org.tpfinal.Users.Model.Entity.User;
import org.tpfinal.Users.Model.Repository.UserRepository;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    private UserRepository userRepository;

    @FXML
    private Button changeUser;

    @FXML
    private TableColumn<?, ?> emailColumn;

    @FXML
    private Button exitButton;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TextField newPasswordField;

    @FXML
    private TableColumn<?, ?> passwordColumn;

    @FXML
    private TextField passwordField;

    @FXML
    private Button removeButton;

    @FXML
    private Button updateButton;

    @FXML
    private TableView<User> userList;

    @FXML
    private TableColumn<?, ?> usernameColumn;

    @FXML
    private TextField usernameField;

    private User selected;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userRepository = new UserRepository();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        Map<String, User> userMap = userRepository.getUserMap();
        ObservableList<User> userList = FXCollections.observableArrayList(userMap.values());
        this.userList.setItems(userList);
    }

    @FXML
    public void selectUser(MouseEvent event){
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
                if(userRepository.getUserMap().containsKey(userSelected.getUsername())){
                    userSelected.setPassword(this.newPasswordField.getText());
                    this.userList.setItems(FXCollections.observableArrayList(userRepository.getUserMap().values()));
                    this.userList.refresh();
                    infMessage("Password updated successfuly");
                }
            }catch(EmptyFieldException e){
                showError(e.getMessage());
            }
        }
    }

    public void removeUser(){
        User userSelected = this.userList.getSelectionModel().getSelectedItem();
        if(selected==null) {
            Alert exception = new Alert(Alert.AlertType.ERROR);
            exception.setHeaderText(null);
            exception.setTitle("Error");
            exception.setContentText("No user selected, please select the one you want to update.");
            exception.showAndWait();
        }else{
            try{
                if(userRepository.getUserMap().containsKey(userSelected.getUsername())){
                    userRepository.remove(userSelected);
                    this.userList.getItems().remove(selected);
                    this.userList.refresh();
                    infMessage("Password updated successfuly");
                }
            }catch(EmptyFieldException e){
                showError(e.getMessage());
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

    private void showError(String message) {
        Alert exception = new Alert(Alert.AlertType.ERROR);
        exception.setHeaderText(null);
        exception.setTitle("Error");
        exception.setContentText(message);
        exception.showAndWait();
    }

    public void changeUser(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("userLogin.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) this.changeUser.getScene().getWindow();
            stage.close();
            stage.setTitle("Welcome to StockManager");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }catch(IOException e){
            showError(e.getMessage());
        }
    }

    public void exit(){
        Stage stage = (Stage) this.exitButton.getScene().getWindow();
        stage.close();
    }

}

