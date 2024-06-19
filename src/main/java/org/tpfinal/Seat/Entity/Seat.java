package org.tpfinal.Seat.Entity;

public class Seat {
    private Integer amount;
    private Float unitCost;
    private Float totalCost;

    public Seat(Integer amount, Float unitCost) {
        this.amount = amount;
        this.unitCost = unitCost;
        this.totalCost = amount * unitCost;
    }

    public Integer getAmount() {
        return amount;
    }

    public Float getUnitCost() {
        return unitCost;
    }

    public Float getTotalCost() {
        return totalCost;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
        this.totalCost = this.amount * this.unitCost;
        // recalculates totalCost automatically.
    }


    public void setUnitCost(Float unitCost) {
        this.unitCost = unitCost;
        this.totalCost = this.amount * this.unitCost;
        // recalculates totalCost automatically.
    }
}
