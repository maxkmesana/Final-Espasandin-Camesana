package org.tpfinal.Seat.Entity;

public class Seat {
    private Integer amount;
    private Float unitPrice;
    private Float totalPrice;

    public Seat(Integer amount, Float unitPrice) {
        this.amount = amount;
        this.unitPrice = unitPrice;
        this.totalPrice = amount * unitPrice;
    }

    public Integer getAmount() {
        return amount;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
        this.totalPrice = this.amount * this.unitPrice;
        // recalculates totalPrice automatically.
    }


    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
        this.totalPrice = this.amount * this.unitPrice;
        // recalculates totalPrice automatically.
    }
}
