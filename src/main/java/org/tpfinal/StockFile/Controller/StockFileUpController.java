package org.tpfinal.StockFile.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class StockFileUpController implements Initializable {

    @FXML
    private AnchorPane anchor;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnUpdate;

    @FXML
    private Label labelActivity;

    @FXML
    private Label labelDate;

    @FXML
    private Label labelUnitCost;

    @FXML
    private Label labelUnitNumber;

    @FXML
    private Label labelUpdateSeat;

    @FXML
    private TextField txtFieldActivity;

    @FXML
    private TextField txtFieldDate;

    @FXML
    private TextField txtFieldUnitCost;

    @FXML
    private TextField txtFieldUnitNumber;

    @FXML
    void clickedBack(MouseEvent event) {

    }

    @FXML
    void clickedUpdate(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO: this entire controller xd.
    }


}
