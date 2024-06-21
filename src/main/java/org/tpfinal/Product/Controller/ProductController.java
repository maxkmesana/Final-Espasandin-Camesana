package org.tpfinal.Product.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import org.tpfinal.Exception.EmptyFieldException;
import org.tpfinal.Product.Model.Entity.Product;
import org.tpfinal.Product.Model.Repository.ProductRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
    private ProductRepository productRepository;

    @FXML
    private Button addButton;

    @FXML
    private Button backButton;

    @FXML
    private TextArea descripField;

    @FXML
    private Label descrpLabel;

    @FXML
    private TextField nameField;

    @FXML
    private Label nameLabel;

    @FXML
    private Button removeButton;

    @FXML
    private TableView setList;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<?, ?> descriptColumn;

    @FXML
    private TextField filterField;

    @FXML
    private Button updateButton;

    private ObservableSet<Product> filteredSet;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productRepository = new ProductRepository();
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.descriptColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        filteredSet = FXCollections.observableSet();
        this.setList.setItems(FXCollections.observableArrayList(ProductRepository.getProductSet()));
    }

    public Product createProduct() throws EmptyFieldException {
        String name = this.nameField.getText();
        String description = this.descripField.getText();
        if(name.isEmpty() || description.isEmpty()){
            throw new EmptyFieldException();
        }else{
            return new Product(name, description);
        }
    }

    public void addProduct(){
        try{
            Product newProduct = createProduct();
            productRepository.add(newProduct);
            this.setList.setItems(FXCollections.observableArrayList(ProductRepository.getProductSet()));
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
        String filterName = this.filterField.getText();

        if (filterName.isEmpty()) {
            this.setList.setItems(FXCollections.observableArrayList(ProductRepository.getProductSet()));
        } else {
            this.filteredSet.clear();
            for (Product product : ProductRepository.getProductSet()) {
                if (product.getName().toLowerCase().contains(filterName.toLowerCase())) {
                    this.filteredSet.add(product);
                }
            }
            this.setList.setItems(FXCollections.observableArrayList(filteredSet)); // Usar filteredSet en lugar de ProductRepository.getProductSet()
        }
    }
}


