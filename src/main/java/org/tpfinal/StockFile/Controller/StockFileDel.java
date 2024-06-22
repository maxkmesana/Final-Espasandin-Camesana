package org.tpfinal.StockFile.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.tpfinal.Exception.EmptyInputException;
import org.tpfinal.Exception.ExceedsAvailableException;
import org.tpfinal.Exception.InvalidNumberInputException;
import org.tpfinal.Interfaces.IntStrategy;
import org.tpfinal.Seat.Entity.Seat;
import org.tpfinal.StockFile.Model.Entity.StockFile;
import org.tpfinal.Strategies.PEPS;
import org.tpfinal.Strategies.PPP;
import org.tpfinal.Strategies.UEPS;

import java.net.URL;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

public class StockFileDel{

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

    private TableView<StockFile> currentTable;

    private Stage currentStage;

    private StockFile currentStockFile;

    private StockFile previousStockFile;

    private String activity;

    @FXML
    void clickedBack(MouseEvent event) {

    }

    @FXML
    void clickedUpdate(MouseEvent event) {
        try{
            update();
        }catch (EmptyInputException | InvalidNumberInputException e){
            Alert exception = new Alert(Alert.AlertType.ERROR);
            exception.setHeaderText(null);
            exception.setTitle("Error");
            exception.setContentText(e.getMessage());
            exception.showAndWait();
        }
    }

    public void update() throws EmptyInputException, InvalidNumberInputException {
        // TODO: CAMBIAR OBJETO UEPS POR EL OBJETO DEL PRODUCTO.
        IntStrategy strategy = new PEPS();
        String activity = txtFieldActivity.getText();
        String checkUnitNumber = txtFieldUnitNumber.getText();
        String checkUnitCost = txtFieldUnitCost.getText();

        if(activity == null || checkUnitNumber == null || checkUnitCost == null){
            throw new EmptyInputException("Every input has to be filled out.");
        }

        Integer unitNumber;
        Float unitCost;
        try{
            unitNumber = Integer.parseInt(checkUnitNumber);
            unitCost = Float.parseFloat(checkUnitCost);
        }catch (NumberFormatException e){
            unitNumber = 0;
            unitCost = 0f;
        }

        if(unitNumber <= 0){
            throw new InvalidNumberInputException("Every input has to be a positive number.");
        }

        // TODO: si es PPP
//        if (strategy instanceof PPP){
//            switch (activity){
//                case "Purchase":
//                    purchaseMadePPP(strategy, activity, unitNumber, unitCost);
//                    break;
//                case "Sale":
//                    saleMadePPP(strategy, activity, unitNumber, unitCost);
//                    break;
//            }
//            return;
//        }

        switch (activity){
            case "Purchase":
                // Borro el ultimo del StockFile
                currentTable.getItems().remove(currentStockFile);

                // SACO OBJETO DE BALANCES Y METO OTRO
//                saleBalances(strategy, activity, unitNumber, unitCost);
                currentStockFile.getBalance().remove(currentStockFile.getPurchase());
//                currentStockFile.getBalance().add(new Seat(unitNumber,unitCost));


                // Hago compra normalmente. (agregando a balances, etc)
//                normalPurchaseMade(strategy, activity, unitNumber, unitCost);

                break;
            case "Sale":
                currentTable.getItems().remove(currentStockFile);

                currentStockFile.setBalance(previousStockFile.getBalance());
                break;
        }

    }

    public void normalPurchaseMade(IntStrategy strategy, String activity, Integer unitNumber, Float unitCost){
        Seat purchaseSeat = new Seat(unitNumber, unitCost);
        StockFile purchaseStockFile = new StockFile(activity,purchaseSeat,new Seat(),strategy);
        copyPreviousBalances(purchaseStockFile);
        purchaseStockFile.addBalanceForceLast(purchaseSeat);
        currentTable.getItems().add(purchaseStockFile);
    }

//    public void saleBalances(IntStrategy strategy, String activity, Integer unitNumber, Float unitCost){
//        Seat saleSeat = new Seat(unitNumber, unitCost);
//        StockFile purchaseStockFile = new StockFile(activity,new Seat(),saleSeat,strategy);
//        copyPreviousBalances(purchaseStockFile);
//        //List<Seat> salesMade =
//        purchaseStockFile.deleteBalance();
//
////        for (Seat sale : salesMade){
////            currentTableView.getItems().add(new StockFile(purchaseStockFile.getActivity(),
////                    purchaseStockFile.getPurchase(), new Seat(sale), purchaseStockFile.getBalance(),
////                    purchaseStockFile.getBalanceStrategy()));
////        }
//    }

    public void normalSaleMade(IntStrategy strategy, String activity, Integer unitNumber, Float unitCost){
        Seat saleSeat = new Seat(unitNumber, unitCost);
        StockFile purchaseStockFile = new StockFile(activity,new Seat(),saleSeat,strategy);
        copyPreviousBalances(purchaseStockFile);
        List<Seat> salesMade = purchaseStockFile.deleteBalance();

        for (Seat sale : salesMade){
            currentTable.getItems().add(new StockFile(purchaseStockFile.getActivity(),
                    purchaseStockFile.getPurchase(), new Seat(sale), purchaseStockFile.getBalance(),
                    purchaseStockFile.getBalanceStrategy()));
        }
    }

    public void copyPreviousBalances(StockFile currentStockFile){
        try{
            for (Seat seat : currentTable.getItems().getLast().getBalance()){
                currentStockFile.addBalanceForceLast(new Seat(seat));
            }
        }catch (NoSuchElementException e){
            return;
        }
    }



    public void initializeAgain(){
        this.activity = currentStockFile.getActivity();
        txtFieldDate.setText(currentStockFile.getDate().toString());
        txtFieldActivity.setText(activity);
        switch (activity){
            case "Purchase":
                txtFieldUnitCost.setText(Float.toString(currentStockFile.getPurchase().getUnitCost()));
                txtFieldUnitNumber.setText(Integer.toString(currentStockFile.getPurchase().getAmount()));
                break;
            case "Sale":
                txtFieldUnitCost.setText(Float.toString(currentStockFile.getSale().getUnitCost()));
                txtFieldUnitNumber.setText(Integer.toString(currentStockFile.getSale().getAmount()));
                txtFieldUnitCost.setDisable(true);
                break;
        }
    }

    public StockFile getCurrentStockFile() {
        return currentStockFile;
    }

    public void setCurrentStockFile(StockFile currentStockFile) {
        this.currentStockFile = currentStockFile;
    }

    public Stage getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    public TableView<StockFile> getCurrentTable() {
        return currentTable;
    }

    public void setCurrentTable(TableView<StockFile> currentTable) {
        this.currentTable = currentTable;
    }

    public StockFile getPreviousStockFile() {
        return previousStockFile;
    }

    public void setPreviousStockFile(StockFile previousStockFile) {
        this.previousStockFile = previousStockFile;
    }
}
