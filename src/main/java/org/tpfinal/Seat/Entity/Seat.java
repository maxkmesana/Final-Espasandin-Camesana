package org.tpfinal.Seat.Entity;

import java.util.UUID;

public class Seat {
    private UUID uuid;
    private Integer amount;
    private Float unitCost;
    private Float totalCost;

    public Seat() {
        amount = 0;
        unitCost = 0f;
        totalCost = 0f;
    }

    public Seat(Integer amount, Float unitCost) {
        this.uuid = UUID.randomUUID();
        this.amount = amount;
        this.unitCost = unitCost;
        this.totalCost = amount * unitCost;
    }

    public Seat(Float totalCost, Integer amount){
        this.uuid = UUID.randomUUID();
        this.amount = amount;
        this.totalCost = totalCost;
        this.unitCost = totalCost / amount;
    }

    public Seat(Seat copy) {
        this.uuid = copy.uuid;
        this.amount = copy.amount;
        this.unitCost = copy.unitCost;
        this.totalCost = copy.totalCost;
    }

    public UUID getUuid() {
        return uuid;
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

    public void setTotalCost(Float totalCost) {
        this.totalCost = totalCost;
    }

    public boolean isEmpty(){
        return unitCost == 0 || amount == 0;
    }
}
