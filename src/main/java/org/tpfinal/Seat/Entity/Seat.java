package org.tpfinal.Seat.Entity;

import java.util.UUID;

public class Seat {
    private final UUID uuid;
    private Integer amount;
    private Float unitCost;
    private Float totalCost;

    public Seat(Integer amount, Float unitCost) {
        this.uuid = UUID.randomUUID();
        this.amount = amount;
        this.unitCost = unitCost;
        this.totalCost = amount * unitCost;
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
