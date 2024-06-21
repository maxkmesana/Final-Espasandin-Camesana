package org.tpfinal.Product.Model.Entity;

import org.tpfinal.Seat.Entity.Seat;
import org.tpfinal.StockFile.Model.Entity.StockFile;
import org.tpfinal.Strategies.UEPS;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Product {
    private String name;
    private String decription;
    private Integer totalStock;
    private Float totalPrice;
    private static List<StockFile> stockFileList;

    static {
        stockFileList = new ArrayList<>();

        // TODO: DELETE THESE .add BELOW
        stockFileList.add(new StockFile("Compra", new Seat(5, 2.5f),new Seat(0, 0f), new UEPS()));
        stockFileList.add(new StockFile("Compra", new Seat(20, 4.5f),new Seat(0, 0f), new UEPS()));
        stockFileList.add(new StockFile("Compra", new Seat(40, 6f),new Seat(0, 0f), new UEPS()));
    }

    public Product(String name, String decription) {
        this.name = name;
        this.decription = decription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
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

    public static List<StockFile> getStockFileList() {
        return stockFileList;
    }
}
