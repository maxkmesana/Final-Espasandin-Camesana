package org.tpfinal.Product.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class ProductController {

    @FXML
    private Button button;

    @FXML
    private Text test;

    @FXML
    void test(MouseEvent event) {
        test.setText("TEST TEST");
        test.setVisible(true);
    }

}