package org.tpfinal.StockFile.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.tpfinal.Product.Model.Entity.Product;
import org.tpfinal.Seat.Entity.Seat;
import org.tpfinal.StockFile.Model.Entity.StockFile;
import org.tpfinal.Strategies.PPP;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
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

    private StockFile selectedStockFile;

    private StockFileAddController stockFileAddController;

    private Product parentProduct;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        activitiesColumn.setCellValueFactory(new PropertyValueFactory<>("activity"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        balancesColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));
        purchasesColumn.setCellValueFactory(new PropertyValueFactory<>("purchase"));
        sales.setCellValueFactory(new PropertyValueFactory<>("sale"));

        totalCostColumnP.setCellValueFactory(new PropertyValueFactory<>("totalCostPurchase"));
        totalCostColumnS.setCellValueFactory(new PropertyValueFactory<>("totalCostSale"));

        unitColumnP.setCellValueFactory(new PropertyValueFactory<>("unitPurchase"));
        unitColumnS.setCellValueFactory(new PropertyValueFactory<>("unitSale"));

        unitCostColumnP.setCellValueFactory(new PropertyValueFactory<>("unitCostPurchase"));
        unitCostColumnS.setCellValueFactory(new PropertyValueFactory<>("unitCostSale"));


        unitColumnB.setCellValueFactory(new PropertyValueFactory<>("amount"));
        unitCostColumnB.setCellValueFactory(new PropertyValueFactory<>("unitCost"));
        totalCostColumnB.setCellValueFactory(new PropertyValueFactory<>("totalCost"));

        populateStockFiles();
    }

    @FXML
    void addButtonClicked(MouseEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/tpfinal/stockFileAdd.fxml"));
            Parent root = loader.load();
            stockFileAddController = loader.getController();

            Scene scene = new Scene(root, 600, 600);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setResizable(false);
            stockFileAddController.setCurrentStage(stage);
            stockFileAddController.setCurrentTableView(tableStockFiles);
            stockFileAddController.setStrategy(parentProduct);

            stage.showAndWait();
        }catch(IOException e){
            Alert exception = new Alert(Alert.AlertType.ERROR);
            exception.setHeaderText(null);
            exception.setTitle("Error");
            exception.setContentText(e.getMessage());
            exception.showAndWait();
        }
    }

    @FXML
    void selectFile(MouseEvent event) {
        selectedStockFile = tableStockFiles.getSelectionModel().getSelectedItem();

        if(selectedStockFile != null){
            populateBalances(selectedStockFile.getBalance());
        }
    }

    @FXML
    void deleteButtonClicked(MouseEvent event) {

        try{
            StockFile last = tableStockFiles.getItems().getLast();

            if(last != null){
                if (last.getBalanceStrategy() instanceof PPP){
                    deletePppRedirect(last);
                    return;
                }
                switch(last.getActivity()){
                    case "Purchase":
                        last.getBalance().remove(last.getPurchase());
                        tableStockFiles.getItems().remove(last);
                        break;
                    case "Sale":
                        tableStockFiles.getItems().remove(last);
                        break;
                }
            }
        }catch (NoSuchElementException e){
            Alert exception = new Alert(Alert.AlertType.ERROR);
            exception.setHeaderText(null);
            exception.setTitle("Error");
            exception.setContentText("There are no elements to be deleted.");
            exception.showAndWait();
        }
    }

    public void deletePppRedirect(StockFile currentStockFile){
        currentStockFile = stockFileAddController.getPreviousPPP();
        tableStockFiles.getItems().remove(tableStockFiles.getItems().getLast());
    }

    public StockFile previousStockFile(StockFile currentStockFile){
        StockFile previous = tableStockFiles.getItems().getFirst();
        for (StockFile stockFile : tableStockFiles.getItems()){
            if(previous.equals(stockFile)){
                return previous;
            }
            previous = stockFile;
        }
        return previous;
    }

    public void populateStockFiles(){
        for (StockFile stockFile : Product.getStockFileList()){
            tableStockFiles.getItems().add(stockFile);
        }
    }

    public void populateBalances(List<Seat> balances){
        balancesTable.getItems().clear();
        for(int i = balances.size() - 1; i >= 0; i--){
            balancesTable.getItems().add(balances.get(i));
        }
    }

    public Product getParentProduct() {
        return parentProduct;
    }

    public void setParentProduct(Product parentProduct) {
        this.parentProduct = parentProduct;
        labelProdName.setText(parentProduct.getName() + ": " + parentProduct.getStrategy().toString());
    }
}
