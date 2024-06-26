package org.tpfinal.Product.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.tpfinal.Exception.EmptyFieldException;
import org.tpfinal.Interfaces.IntStrategy;
import org.tpfinal.Product.Model.Entity.Product;
import org.tpfinal.Product.Model.Repository.ProductRepository;
import javafx.event.ActionEvent;
import org.tpfinal.StockFile.Controller.StockFileController;
import org.tpfinal.StockFile.Model.Entity.StockFile;
import org.tpfinal.Strategies.PEPS;
import org.tpfinal.Strategies.PPP;
import org.tpfinal.Strategies.UEPS;
import org.tpfinal.Users.Model.Entity.User;
import org.tpfinal.Users.Model.Repository.UserRepository;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
    private ProductRepository productRepository;

    private UserRepository userRepository;

    private String currentUsername;

    User currentUser;

    @FXML
    private Button backButton;

    @FXML
    private TextArea descripField;

    @FXML
    private TextField nameField;

    @FXML
    private TableView<Product> setList;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<?, ?> descriptColumn;

    @FXML
    private TextField filterField;

    private ObservableSet<Product> filteredSet;

    @FXML
    private ChoiceBox<IntStrategy> systemChoiceBox;

    @FXML
    private Label systemLabel;

    @FXML
    private Button stockManagementButton;

    private Product selected;

    private Stage currentStage;

    public void getUsername(String username){
        this.currentUsername = username;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productRepository = new ProductRepository();
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.descriptColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        filteredSet = FXCollections.observableSet();

        this.systemChoiceBox.getItems().addAll(new UEPS(), new PEPS(), new PPP());
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User currentUserUsage(){
        return this.currentUser = userRepository.userExistance(currentUsername); // llega un User con un prod pleaseLoad
    }

    public void InitializeAgain(){
        User current = currentUserUsage();
        this.setList.setItems(FXCollections.observableArrayList(current.getProductSet()));
    }

    @FXML
    public Product createProduct() throws EmptyFieldException {
        String name = this.nameField.getText();
        String description = this.descripField.getText();
        IntStrategy strategy = this.systemChoiceBox.getSelectionModel().getSelectedItem();
        if(name.isEmpty() || description.isEmpty() || strategy == null){
            throw new EmptyFieldException();
        }else{
            return new Product(name, description, strategy);
        }
    }

    @FXML
    public void addProduct(){
        try{
            User current = currentUserUsage();
            Product newProduct = createProduct();
            current.getProductSet().add(newProduct);
            productRepository.add(newProduct);
            this.setList.setItems(FXCollections.observableArrayList(current.getProductSet()));
            infMessage("Product added successfuly");
        }catch(EmptyFieldException e){
            Alert exception = new Alert(Alert.AlertType.ERROR);
            exception.setHeaderText(null);
            exception.setTitle("Error");
            exception.setContentText(e.getMessage());
            exception.showAndWait();
        }
        userRepository.saveToJson();
    }

    @FXML
    public void filterProducts(KeyEvent event) {
        User current = currentUserUsage();
        String filterName = this.filterField.getText();

        if (filterName.isEmpty()) {
            this.setList.setItems(FXCollections.observableArrayList(current.getProductSet()));
        } else {
            this.filteredSet.clear();
            for (Product product : current.getProductSet()) {
                if (product.getName().toLowerCase().contains(filterName.toLowerCase())) {
                    this.filteredSet.add(product);
                }
            }
            this.setList.setItems(FXCollections.observableArrayList(filteredSet));
        }
    }

    @FXML
    public void selectProduct(MouseEvent event){
        selected = this.setList.getSelectionModel().getSelectedItem();
        if(selected!=null){
            this.nameField.setText(selected.getName());
            this.descripField.setText(selected.getDescription());
            this.stockManagementButton.setDisable(false);
            this.systemChoiceBox.setValue(selected.getStrategy());
        }else{
            stockManagementButton.setDisable(true);
        }
    }

    @FXML
    public void stockManagementClicked(MouseEvent event){
        if(selected != null){
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/tpfinal/stockFile.fxml"));
                Parent root = loader.load();
                StockFileController controller = loader.getController();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.setResizable(false);
                controller.setParentProduct(selected);
                controller.setSelectedStockFileList(selected.getStockFileList());
                controller.setUserRepository(userRepository);
                controller.initializeAgain();
                stage.showAndWait();
            }catch(IOException e){
                Alert exception = new Alert(Alert.AlertType.ERROR);
                exception.setHeaderText(null);
                exception.setTitle("Error");
                exception.setContentText(e.getMessage());
                exception.showAndWait();
            }
        }
    }

    @FXML
    public void updateProduct(ActionEvent event){
        User current = currentUserUsage();
        Product selected = this.setList.getSelectionModel().getSelectedItem();
        if(selected==null){
            Alert exception = new Alert(Alert.AlertType.ERROR);
            exception.setHeaderText(null);
            exception.setTitle("Error");
            exception.setContentText("No product selected, please select the one you want to update.");
            exception.showAndWait();
        }else{
            try{
                Product aux = createProduct();
                if(!current.getProductSet().contains(aux)){
                    productRepository.update(selected, aux);
                    this.setList.setItems(FXCollections.observableArrayList(current.getProductSet()));
                    this.setList.refresh();
                    infMessage("Product updated successfuly");
                }
            }catch(EmptyFieldException e){
                Alert exception = new Alert(Alert.AlertType.ERROR);
                exception.setHeaderText(null);
                exception.setTitle("Error");
                exception.setContentText(e.getMessage());
                exception.showAndWait();
            }
        }
        userRepository.saveToJson();
    }

    @FXML
    public void removeProduct(ActionEvent event){
        User current = currentUserUsage();
        Product selected = this.setList.getSelectionModel().getSelectedItem();
        if(selected==null){
            Alert exception = new Alert(Alert.AlertType.ERROR);
            exception.setHeaderText(null);
            exception.setTitle("Error");
            exception.setContentText("No product selected, please select the one you want to update.");
            exception.showAndWait();
        }else{
            current.getProductSet().remove(selected);
            productRepository.remove(selected);
            this.setList.getItems().remove(selected);
            this.setList.refresh();
        }
        userRepository.saveToJson();
    }

    @FXML
    public void back(ActionEvent event){
        Stage stage = (Stage) this.backButton.getScene().getWindow();
        stage.close();
    }

    public void infMessage(String message){
        Alert exception = new Alert(Alert.AlertType.INFORMATION);
        exception.setHeaderText(null);
        exception.setTitle("Error");
        exception.setContentText(message);
        exception.showAndWait();
    }

    public Stage getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }
}


