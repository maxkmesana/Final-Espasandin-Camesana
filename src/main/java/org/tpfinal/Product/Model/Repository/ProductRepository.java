package org.tpfinal.Product.Model.Repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import org.tpfinal.Interfaces.IntRepository;
import org.tpfinal.Product.Model.Entity.Product;

public class ProductRepository implements IntRepository<Product> {
    private static ObservableSet<Product> productSet;

    public ProductRepository() {
        this.productSet = FXCollections.observableSet();
    }

    public static ObservableSet<Product> getProductSet() {
        return productSet;
    }

    @Override
    public void add(Product add) {
        productSet.add(add);
    }

    @Override
    public Product search(String searchField) {
        for(Product product : productSet){
            if(product.getName().equals(searchField)){
                return product;
            }
        }
        return null;
    }

    @Override
    public void update(Product toUpdate, Product updated) {
        toUpdate.setName(updated.getName());
        toUpdate.setDescription(updated.getDescription());
    }

    @Override
    public void remove(Product remove) {
        productSet.remove(remove);
    }
}
