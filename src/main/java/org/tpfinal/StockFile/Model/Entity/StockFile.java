package org.tpfinal.StockFile.Model.Entity;

import org.tpfinal.Interfaces.IntStrategy;
import org.tpfinal.Seat.Entity.Seat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StockFile {
    private LocalDate date;
    private String activity;
    private Seat purchase;
    private Seat sale;
    private final List<Seat> balance;
    private IntStrategy balanceStrategy;

    public StockFile(String activity, Seat purchase, Seat sale, IntStrategy balanceStrategy) {
        this.date = LocalDate.now();
        this.activity = activity;
        this.purchase = purchase;
        this.sale = sale;
        this.balanceStrategy = balanceStrategy;
        this.balance = new ArrayList<>();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Seat getPurchase() {
        return purchase;
    }

    public void setPurchase(Seat purchase) {
        this.purchase = purchase;
    }

    public Seat getSale() {
        return sale;
    }

    public void setSale(Seat sale) {
        this.sale = sale;
    }

    public List<Seat> getBalance() {
        return balance;
    }

    public void setBalanceStrategy(IntStrategy balanceStrategy) {
        this.balanceStrategy = balanceStrategy;
    }

    public void addBalance(){
        balanceStrategy.add(balance, this.purchase);
    }

    public void searchBalance(UUID uuid){
        balanceStrategy.search(balance, uuid);
    }
    
    public void updateBalance(Seat toUpdate, Seat updated){
        balanceStrategy.update(toUpdate,updated);
    }
    
    public void deleteBalance(){
        balanceStrategy.delete(balance, sale.getAmount());
    }

    public Float getTotalCostPurchase(){
        return purchase.getTotalCost();
    }

    public Float getTotalCostSale(){
        return sale.getTotalCost();
    }

    public Integer getUnitPurchase(){
        return purchase.getAmount();
    }

    public Integer getUnitSale(){
        return sale.getAmount();
    }

    public Float getUnitCostPurchase(){
        return purchase.getUnitCost();
    }

    public Float getUnitCostSale(){
        return sale.getUnitCost();
    }
}
