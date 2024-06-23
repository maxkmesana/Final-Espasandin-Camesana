package org.tpfinal.StockFile.Controller;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.tpfinal.Exception.EmptyInputException;
import org.tpfinal.Exception.ExceedsAvailableException;
import org.tpfinal.Exception.InvalidNumberInputException;
import org.tpfinal.Interfaces.IntStrategy;
import org.tpfinal.Product.Model.Entity.Product;
import org.tpfinal.Seat.Entity.Seat;
import org.tpfinal.StockFile.Model.Entity.StockFile;
import org.tpfinal.StockFile.Model.Repository.StockFileRepository;
import org.tpfinal.Strategies.PEPS;
import org.tpfinal.Strategies.PPP;
import org.tpfinal.Strategies.UEPS;
import org.tpfinal.Users.Model.Repository.UserRepository;


import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class StockFileAddController implements Initializable {

    private UserRepository userRepository;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSubmit;

    @FXML
    private ChoiceBox<String> choiceBoxActivity;

    @FXML
    private Label labelActivity;

    @FXML
    private Label labelAddSeat;

    @FXML
    private Label labelDate;

    @FXML
    private Label labelType;

    @FXML
    private Label labelUnitNumber;

    @FXML
    private Label labelUnitCost;

    @FXML
    private TextField txtFieldDate;

    @FXML
    private TextField txtFieldUnitNumber;

    @FXML
    private TextField txtFieldUnitCost;

    private Stage currentStage;
    private StockFile previousPPP;

    private TableView<StockFile> currentTableView;
    private Product parentProduct;

    private IntStrategy strategy;

    private List<StockFile> selectedStockFileList;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxActivity.getItems().addAll("Purchase", "Sale");
        txtFieldDate.setText(LocalDate.now().toString());

        choiceBoxActivity.getSelectionModel()
                .selectedItemProperty()
                .addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) -> disableUnitCost(newValue));
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    public void setCurrentTableView(TableView<StockFile> currentTableView) {
        this.currentTableView = currentTableView;
    }

    @FXML
    void clickedBack(MouseEvent event) {
        currentStage.close();
    }

    @FXML
    void enterPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            try{
                submit();
            }catch (EmptyInputException | InvalidNumberInputException e){
                Alert exception = new Alert(Alert.AlertType.ERROR);
                exception.setHeaderText(null);
                exception.setTitle("Error");
                exception.setContentText(e.getMessage());
                exception.showAndWait();
            }
        }
    }

    @FXML
    void clickedSubmit(MouseEvent event) {
        try{
            submit();
        }catch (EmptyInputException | InvalidNumberInputException e){
            Alert exception = new Alert(Alert.AlertType.ERROR);
            exception.setHeaderText(null);
            exception.setTitle("Error");
            exception.setContentText(e.getMessage());
            exception.showAndWait();
        }
    }

    public void disableUnitCost(String newValue){
        if(newValue.equals("Sale")){
            txtFieldUnitCost.setDisable(true);
            txtFieldUnitCost.clear();
            txtFieldUnitCost.setText("0");
        }else{
            txtFieldUnitCost.setDisable(false);
        }
    }

    public void submit() throws EmptyInputException, InvalidNumberInputException {
        String activity = choiceBoxActivity.getSelectionModel().getSelectedItem();
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


        if (strategy instanceof PPP){
            switch (activity){
                case "Purchase":
                    purchaseMadePPP(strategy, activity, unitNumber, unitCost);
                    break;
                case "Sale":
                    saleMadePPP(strategy, activity, unitNumber, unitCost);
                    break;
            }

            return;
        }
        try{
            switch (activity){
                case "Purchase":
                    purchaseMade(strategy, activity, unitNumber, unitCost);
                    break;
                case "Sale":
                    saleMade(strategy, activity, unitNumber, unitCost);
                    break;
            }

        }catch (ExceedsAvailableException e){
            Alert exception = new Alert(Alert.AlertType.ERROR);
            exception.setHeaderText(null);
            exception.setTitle("Error");
            exception.setContentText(e.getMessage());
            exception.showAndWait();
        }
    }

    public void purchaseMadePPP(IntStrategy strategy, String activity, Integer unitNumber, Float unitCost){
        Seat purchaseSeat = new Seat(unitNumber, unitCost);
        StockFile purchaseStockFile = new StockFile(activity,purchaseSeat,new Seat(),strategy);

        copyPreviousBalances(purchaseStockFile);

        previousPPP = new StockFile(activity, purchaseStockFile.getPurchase(),
                new Seat(), purchaseStockFile.getBalance(), strategy);

        purchaseStockFile.addBalancePPP(purchaseStockFile.getBalance(), purchaseSeat);

        currentTableView.getItems().add(purchaseStockFile);
        selectedStockFileList.add(purchaseStockFile);
        userRepository.saveToJson();
    }

    public void saleMadePPP(IntStrategy strategy, String activity, Integer unitNumber, Float unitCost){
        Seat saleSeat = new Seat(unitNumber, unitCost);
        StockFile purchaseStockFile = new StockFile(activity,new Seat(),saleSeat,strategy);
        copyPreviousBalances(purchaseStockFile);
        try{
            List <Seat> salesMade = purchaseStockFile.deleteBalance();

            for (Seat sale : salesMade){
                StockFile newSale = new StockFile(purchaseStockFile.getActivity(),
                        purchaseStockFile.getPurchase(), new Seat(sale), purchaseStockFile.getBalance(),
                        purchaseStockFile.getBalanceStrategy());
                currentTableView.getItems().add(newSale);
                selectedStockFileList.add(newSale);
                userRepository.saveToJson();
            }
        }catch (ExceedsAvailableException e){
            Alert exception = new Alert(Alert.AlertType.ERROR);
            exception.setHeaderText(null);
            exception.setTitle("Error");
            exception.setContentText(e.getMessage());
            exception.showAndWait();
        }

    }

    public void purchaseMade(IntStrategy strategy, String activity, Integer unitNumber, Float unitCost){
        Seat purchaseSeat = new Seat(unitNumber, unitCost);
        StockFile purchaseStockFile = new StockFile(activity,purchaseSeat,new Seat(),strategy);
        copyPreviousBalances(purchaseStockFile);
        purchaseStockFile.addBalanceForceLast(purchaseSeat);
        currentTableView.getItems().add(purchaseStockFile);
        selectedStockFileList.add(purchaseStockFile);
        userRepository.saveToJson();
    }

    public void saleMade(IntStrategy strategy, String activity, Integer unitNumber, Float unitCost){
        Seat saleSeat = new Seat(unitNumber, unitCost);
        StockFile purchaseStockFile = new StockFile(activity,new Seat(),saleSeat,strategy);
        copyPreviousBalances(purchaseStockFile);
        try{
            List <Seat> salesMade = purchaseStockFile.deleteBalance();

            for (Seat sale : salesMade){
                StockFile newSale = new StockFile(purchaseStockFile.getActivity(),
                        purchaseStockFile.getPurchase(), new Seat(sale), purchaseStockFile.getBalance(),
                        purchaseStockFile.getBalanceStrategy());
                currentTableView.getItems().add(newSale);
                selectedStockFileList.add(newSale);
                userRepository.saveToJson();
            }
        }catch (ExceedsAvailableException e){
            Alert exception = new Alert(Alert.AlertType.ERROR);
            exception.setHeaderText(null);
            exception.setTitle("Error");
            exception.setContentText(e.getMessage());
            exception.showAndWait();
        }
    }

    public void copyPreviousBalances(StockFile currentStockFile){
        try{
            for (Seat seat : currentTableView.getItems().getLast().getBalance()){
                currentStockFile.addBalanceForceLast(new Seat(seat));
            }
        }catch (NoSuchElementException e){
            return;
        }
    }

    public StockFile getPreviousPPP() {
        return previousPPP;
    }

    public void setStrategy(Product parentProduct) {
        this.strategy = parentProduct.getStrategy();
    }

    public List<StockFile> getSelectedStockFileList() {
        return selectedStockFileList;
    }

    public void setSelectedStockFileList(List<StockFile> selectedStockFileList) {
        this.selectedStockFileList = selectedStockFileList;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
