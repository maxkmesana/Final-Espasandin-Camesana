package org.tpfinal.Product.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonChange;

    @FXML
    private Button buttonExit;

    @FXML
    private Button buttonManagment;

    @FXML
    private Label labelLanding;

    @FXML
    private AnchorPane menuAnchor;

    @FXML
    private SplitPane splitPane;

    @FXML
    private Label username;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

