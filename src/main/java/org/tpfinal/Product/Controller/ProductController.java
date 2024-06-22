package org.tpfinal.Product.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.tpfinal.Exception.EmptyFieldException;
import org.tpfinal.Product.Model.Entity.Product;
import org.tpfinal.Product.Model.Repository.ProductRepository;
import javafx.event.ActionEvent;
import org.tpfinal.Users.Model.Entity.User;
import org.tpfinal.Users.Model.Repository.UserRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
    private ProductRepository productRepository;
    private UserRepository userRepository;

    private String currentUsername;
    User currentUser;

    public void getUsername(String username){
        this.currentUsername = username;
    }


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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userRepository = new UserRepository();
        productRepository = new ProductRepository();
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.descriptColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        filteredSet = FXCollections.observableSet();
        this.setList.setItems(FXCollections.observableArrayList(ProductRepository.getProductSet()));
    }

    public User currentUserUsage(){
        return this.currentUser = userRepository.userExistance(currentUsername);
    }

    @FXML
    public Product createProduct() throws EmptyFieldException {
        String name = this.nameField.getText();
        String description = this.descripField.getText();
        if(name.isEmpty() || description.isEmpty()){
            throw new EmptyFieldException();
        }else{
            return new Product(name, description);
        }
    }

    @FXML
    public void addProduct(){
        try{
            User current = currentUserUsage();
            Product newProduct = createProduct();
            current.getProductSet().add(newProduct);//TODO: ya tenemos el como usar el set del usuario, ahora queda hacer funcionales los metodos update y remove
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
    }

    @FXML
    public void filterProducts(KeyEvent event) {
        User current = currentUserUsage();
        String filterName = this.filterField.getText();

        if (filterName.isEmpty()) {
            this.setList.setItems(FXCollections.observableArrayList(current.getProductSet()));
        } else {
            this.filteredSet.clear();
            for (Product product : ProductRepository.getProductSet()) {
                if (product.getName().toLowerCase().contains(filterName.toLowerCase())) {
                    this.filteredSet.add(product);
                }
            }
            this.setList.setItems(FXCollections.observableArrayList(filteredSet));
        }
    }

    @FXML
    public void selectProduct(MouseEvent event){
        Product selected = this.setList.getSelectionModel().getSelectedItem();
        if(selected!=null){
            this.nameField.setText(selected.getName());
            this.descripField.setText(selected.getDescription());
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
}


