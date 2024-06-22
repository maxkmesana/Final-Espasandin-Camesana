package org.tpfinal.Product.Model.Entity;

import org.tpfinal.StockFile.Model.Entity.StockFile;

import java.util.LinkedList;
import java.util.List;
import java.util.*;

public class Product {
    private String name;
    private String description;
    //private String strategy;
    private static List<StockFile> stockFileList;

    static {
        stockFileList = new LinkedList<>();
    }



    public Product(String name, String description) {
        this.name = name;
        this.description = description;
        this.stockFileList = new LinkedList<>();/**
                                                   check about the collection use, may not be the greater solution.
                                                */
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setStockFileList(List<StockFile> stockFileList) {
        this.stockFileList = stockFileList;
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
