package org.tpfinal.Strategies;

import org.tpfinal.Interfaces.IntStrategy;
import org.tpfinal.Seat.Entity.Seat;

import java.util.List;
import java.util.UUID;

public class PEPS implements IntStrategy {
    // Primero en Entrar, Primero en Salir = FIFO = COLA
    @Override
    public void add(List<Seat> balance, Seat add) {
        balance.addFirst(add);
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
     * PEPS (FIFO) implementation. Removes first item of the list.
     *
     * @param balance list of balances to delete from.
     */
    @Override
    public void delete(List<Seat> balance) {
        balance.removeFirst();
    }
}
