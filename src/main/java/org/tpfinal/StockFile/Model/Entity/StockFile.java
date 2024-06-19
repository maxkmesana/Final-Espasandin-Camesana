package org.tpfinal.StockFile.Model.Entity;

import org.tpfinal.Seat.Entity.Seat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StockFile {
    private Date date;
    private String activity;
    private Seat purchase;
    private Seat sales;
    private List<Seat> balance;

    public StockFile(Date date, String activity, Seat purchase, Seat sales) {
        this.date = date;
        this.activity = activity;
        this.purchase = purchase;
        this.sales = sales;
        this.balance = new ArrayList<>();
    }
}
