package org.tpfinal.Product.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.tpfinal.Product.Model.Entity.Product;
import org.tpfinal.Users.Model.Entity.User;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class ProductAddController implements Initializable {

    @FXML
    private TextArea descriptionField;

    @FXML
    private Button exitButton;

    @FXML
    private Label labelDescription;

    @FXML
    private Label labelLanding;

    @FXML
    private Label labelName;

    @FXML
    private TextField nameField;

    @FXML
    private TableView<?> productSet;

    @FXML
    private Button sumbitButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void addProduct(){
        // TODO : esto funciona, aplicar logica de agregado -- User.getProductSet().add(productito);
    }
}
