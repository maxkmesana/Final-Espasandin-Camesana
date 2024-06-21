package org.tpfinal.StockFile.Controller;

import javafx.beans.property.SimpleFloatProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.tpfinal.Product.Model.Entity.Product;
import org.tpfinal.Seat.Entity.Seat;
import org.tpfinal.StockFile.Model.Entity.StockFile;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class StockFileController implements Initializable {

    @FXML // fx:id="activitiesColumn"
    private TableColumn<StockFile, String> activitiesColumn; // Value injected by FXMLLoader

    @FXML // fx:id="anchor"
    private AnchorPane anchor; // Value injected by FXMLLoader

    @FXML // fx:id="balancesColumn"
    private TableColumn<StockFile, List<Seat>> balancesColumn; // Value injected by FXMLLoader

    @FXML // fx:id="balancesTable"
    private TableView<Seat> balancesTable; // Value injected by FXMLLoader

    @FXML // fx:id="btnAdd"
    private Button btnAdd; // Value injected by FXMLLoader

    @FXML // fx:id="btnDelete"
    private Button btnDelete; // Value injected by FXMLLoader

    @FXML // fx:id="btnUpdate"
    private Button btnUpdate; // Value injected by FXMLLoader

    @FXML // fx:id="dateColumn"
    private TableColumn<StockFile, LocalDate> dateColumn; // Value injected by FXMLLoader

    @FXML // fx:id="labelProdName"
    private Label labelProdName; // Value injected by FXMLLoader

    @FXML // fx:id="purchasesColumn"
    private TableColumn<StockFile, Seat> purchasesColumn; // Value injected by FXMLLoader

    @FXML // fx:id="sales"
    private TableColumn<StockFile, Seat> sales; // Value injected by FXMLLoader

    @FXML // fx:id="tableStockFiles"
    private TableView<StockFile> tableStockFiles; // Value injected by FXMLLoader

    @FXML // fx:id="totalCostColumnB"
    private TableColumn<Seat, Float> totalCostColumnB; // Value injected by FXMLLoader

    @FXML // fx:id="totalCostColumnP"
    private TableColumn<StockFile, Float> totalCostColumnP; // Value injected by FXMLLoader

    @FXML // fx:id="totalCostColumnS"
    private TableColumn<StockFile, Float> totalCostColumnS; // Value injected by FXMLLoader

    @FXML // fx:id="unitColumnB"
    private TableColumn<Seat, Integer> unitColumnB; // Value injected by FXMLLoader

    @FXML // fx:id="unitColumnP"
    private TableColumn<StockFile, Integer> unitColumnP; // Value injected by FXMLLoader

    @FXML // fx:id="unitColumnS"
    private TableColumn<StockFile, Integer> unitColumnS; // Value injected by FXMLLoader

    @FXML // fx:id="unitCostColumnB"
    private TableColumn<Seat, Float> unitCostColumnB; // Value injected by FXMLLoader

    @FXML // fx:id="unitCostColumnP"
    private TableColumn<StockFile, Float> unitCostColumnP; // Value injected by FXMLLoader

    @FXML // fx:id="unitCostColumnS"
    private TableColumn<StockFile, Float> unitCostColumnS; // Value injected by FXMLLoader


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        activitiesColumn.setCellValueFactory(new PropertyValueFactory<StockFile, String>("activity"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<StockFile, LocalDate>("date"));
        balancesColumn.setCellValueFactory(new PropertyValueFactory<StockFile, List<Seat>>("balance"));
        purchasesColumn.setCellValueFactory(new PropertyValueFactory<StockFile, Seat>("purchase"));
        sales.setCellValueFactory(new PropertyValueFactory<StockFile, Seat>("sale"));

        totalCostColumnP.setCellValueFactory(new PropertyValueFactory<StockFile, Float>("totalCostPurchase"));
        totalCostColumnS.setCellValueFactory(new PropertyValueFactory<StockFile, Float>("totalCostSale"));

        unitColumnP.setCellValueFactory(new PropertyValueFactory<StockFile, Integer>("unitPurchase"));
        unitColumnS.setCellValueFactory(new PropertyValueFactory<StockFile, Integer>("unitSale"));

        unitCostColumnP.setCellValueFactory(new PropertyValueFactory<StockFile, Float>("unitCostPurchase"));
        unitCostColumnS.setCellValueFactory(new PropertyValueFactory<StockFile, Float>("unitCostSale"));


        unitColumnB.setCellValueFactory(new PropertyValueFactory<Seat, Integer>("amount"));
        unitCostColumnB.setCellValueFactory(new PropertyValueFactory<Seat, Float>("unitCost"));
        totalCostColumnB.setCellValueFactory(new PropertyValueFactory<Seat, Float>("totalCost"));


        populateStockFiles();
    }

    @FXML
    void addButtonClicked(MouseEvent event) {
        // TODO: new scene
    }

    @FXML
    void deleteButtonClicked(MouseEvent event) {
        // TODO: new scene (WARNING WINDOW, [ACCEPT OR DENY])
    }

    @FXML
    void selectFile(MouseEvent event) {
        // TODO: make it so if you click null, it doesn't register (yate provided a source for that)
        //      source: https://www.youtube.com/watch?v=SwYczt6K_Q0&list=PLaxZkGlLWHGUWZxuadN3J7KKaICRlhz5-&index=6
        StockFile selectedStockFile = tableStockFiles.getSelectionModel().getSelectedItem();
        populateBalances(selectedStockFile.getBalance());
    }

    @FXML
    void updateButtonClicked(MouseEvent event) {
        // TODO: new scene
    }

    public void populateStockFiles(){
        for (StockFile stockFile : Product.getStockFileList()){
            tableStockFiles.getItems().add(stockFile);
        }
    }

    public void populateBalances(List<Seat> balances){
        for (Seat seat : balances){
            balancesTable.getItems().add(seat);
        }
    }
}
