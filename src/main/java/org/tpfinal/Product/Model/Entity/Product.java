package org.tpfinal.Product.Model.Entity;

import java.util.Objects;

public class Product {
    private String name;
    private String decripcion;
    private Integer totalStock;
    private Float totalPrice;

    public Product(String name, String decripcion) {
        this.name = name;
        this.decripcion = decripcion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDecripcion() {
        return decripcion;
    }

    public void setDecripcion(String decripcion) {
        this.decripcion = decripcion;
    }

    public Integer getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(Integer totalStock) {
        this.totalStock = totalStock;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Should be used in <code>StockFile</code> to increase amount of <code>totalStock</code>
     * of a Product object when new stock enters our system.
     *
     * @param amount desired amount to increase
     */
    public void increaseTotalStock(Integer amount){
        totalStock += amount;
    }

    /**
     * Should be used in <code>StockFile</code> to decrease amount of <code>totalStock</code>
     * of a Product object when stock leaves our system.
     *
     * @param amount desired amount to decrease
     */
    public void decreaseTotalStock(Integer amount){
        totalStock-= amount;
    }

    /**
     * Should be used in <code>StockFile</code> to increase amount of <code>totalPrice</code>
     * of a Product object when new stock enters our system.
     *
     * @param amount desired amount to increase
     */
    public void increaseTotalPrice(Float amount){
        totalPrice += amount;
    }

    /**
     * Should be used in <code>StockFile</code> to decrease amount of <code>totalPrice</code>
     * of a Product object when stock leaves our system.
     *
     * @param amount desired amount to decrease
     */
    public void decreaseTotalPrice(Float amount){
        totalPrice-= amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
