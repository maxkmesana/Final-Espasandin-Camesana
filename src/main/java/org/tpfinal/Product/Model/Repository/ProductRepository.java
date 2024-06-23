package org.tpfinal.Product.Model.Repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import org.tpfinal.Interfaces.IntRepository;
import org.tpfinal.Product.Model.Entity.Product;

import java.util.HashSet;
import java.util.Set;

public class ProductRepository implements IntRepository<Product> {
    private static Set<Product> productSet;
    // TODO: SACAR ESE STATIC DE AHI CARAJO. SI HAY MAS USUARIOS VAN A TENER LOS MISMOS DATOS!!!!!!
    //      actualmente son las 7.48 am y no dormi asi que se refactoriza otro dia.

    public ProductRepository() {
        this.productSet = new HashSet<>();
    }

    public static Set<Product> getProductSet() {
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
