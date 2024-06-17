package org.tpfinal.Product.Model.Repository;

import org.tpfinal.Interfaces.IntRepository;
import org.tpfinal.Product.Model.Entity.Product;

import java.util.HashSet;
import java.util.Set;

public class ProductRepository implements IntRepository<Product> {
    private final Set<Product> productSet; // seria final?

    public ProductRepository() {
        this.productSet = new HashSet<>();
    } // Se puede cambiar por implementacion de JSON.

    public Set<Product> getProductSet() {
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
        toUpdate.setTotalPrice(updated.getTotalPrice());
        toUpdate.setTotalStock(updated.getTotalStock());
    }

    @Override
    public void remove(Product remove) {
        productSet.remove(remove);
    }
}
