package org.tpfinal.Strategies;

import org.tpfinal.Interfaces.IntStrategy;
import org.tpfinal.Seat.Entity.Seat;

import java.util.*;

public class PEPS implements IntStrategy {
    // Primero en Entrar, Primero en Salir = FIFO = COLA
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
     *
     * @param balance list of balances to delete from.
     */
    @Override
    public List<Seat> delete(List<Seat> balance, Integer saleAmount) {
        List<Seat> seatReturn = new ArrayList<>();

        Iterator<Seat> iterator = balance.iterator();

        while (iterator.hasNext()){
            Seat currentSeat = iterator.next();

            if(saleAmount.equals(currentSeat.getAmount())){
                seatReturn.add(new Seat(currentSeat.getAmount(), currentSeat.getUnitCost()));
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
}
