package org.tpfinal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.tpfinal.Product.Controller.ProductController;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StockManagerLanding implements Initializable {

    @FXML
    private Button buttonChange;

    @FXML
    private Button buttonExit;

    @FXML
    private Button buttonManagment;

    @FXML
    private Button buttonManagment1;

    @FXML
    private Label labelLanding;

    @FXML
    private Label username;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setUsername(String username) {
        this.username.setText(username);
    }

    @FXML
    public void switchToProdManagment(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/tpfinal/productManagment.fxml"));
            Parent root = loader.load();
            ProductController controller = loader.getController();
            Scene scene = new Scene(root, 719, 598);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
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
    }

    public void exit(){
        Stage stage = (Stage) this.buttonExit.getScene().getWindow();
        stage.close();
    }

}