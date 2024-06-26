package org.tpfinal.Strategies;

import org.tpfinal.Exception.ExceedsAvailableException;
import org.tpfinal.Interfaces.IntStrategy;
import org.tpfinal.Seat.Entity.Seat;

import java.util.*;

public class PEPS implements IntStrategy {
    // Primero en Entrar, Primero en Salir = FIFO = COLA


    public PEPS() {
    }

    @Override
    public void add(List<Seat> balance, Seat add) {
        balance.add(add);
    }

    @Override
    public Seat search(List<Seat> balance, UUID uuid) {
        for(Seat seat : balance){
            if(seat.getUuid().equals(uuid)){
                return seat;
            }
        }
        return null;
    }

    @Override
    public void update(Seat toUpdate, Seat updated) {
        toUpdate.setAmount(updated.getAmount());
        toUpdate.setUnitCost(updated.getUnitCost());
    }

    /**
     * PEPS (FIFO) implementation.
     * Contains three base cases: <p>
     * <code>Case 1:</code> where sale amount is the same as the seat's amount,
     * the seat will be removed from <code>balance</code> list and added as a
     * new entity to the return list.<p>
     *
     * <code>Case 2:</code> where sale amount is lesser than the Seat's amount,
     * the Seat's attribute will be set to the new corresponding value and added
     * as a new entity to the return list.<p>
     *
     * <code>Case 3:</code> where sale amount is greater than the Seat's amount,
     * the Seat's amount (insufficient) is subtracted from sale amount and Sear
     * is removed from the original list and added to the return list.
     *
     * @param balance list of balances to delete from.
     * @return a new list containing all the Seat movements made to the original list.
     */
    @Override
    public List<Seat> delete(List<Seat> balance, Integer saleAmount) throws ExceedsAvailableException{
        List<Seat> seatReturn = new ArrayList<>();

        Iterator<Seat> iterator = balance.iterator();
        if(saleAmount > getTotalUnits(balance)){
            throw new ExceedsAvailableException();
        }

        while (iterator.hasNext()){
            Seat currentSeat = iterator.next();

            if(saleAmount.equals(currentSeat.getAmount())){
                // Adds Seat to return list
                seatReturn.add(new Seat(currentSeat.getAmount(), currentSeat.getUnitCost()));
                // Deletes currentSeat because its amount should now be zero.
                iterator.remove();
                break;
            }
            if (saleAmount < currentSeat.getAmount()) {
                // Subtracts saleAmount from currentSeat and sets new value on entity
                currentSeat.setAmount(currentSeat.getAmount() - saleAmount);
                // Adds new Seat to the list to be returned
                seatReturn.add(new Seat(saleAmount, currentSeat.getUnitCost()));
                break;
            }
            if (saleAmount > currentSeat.getAmount()) {
                seatReturn.add(new Seat(currentSeat.getAmount(), currentSeat.getUnitCost()));
                saleAmount -= currentSeat.getAmount();
                iterator.remove();
            }
        }
        return seatReturn;
    }

    public Integer getTotalUnits(List<Seat> balance){
        Integer totalUnits = 0;
        for (Seat seat : balance){
            totalUnits += seat.getAmount();
        }
        return totalUnits;
    }

    public String toString(){
        return "FIFO";
    }
}
