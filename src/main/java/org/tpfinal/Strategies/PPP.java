package org.tpfinal.Strategies;

import org.tpfinal.Exception.DivisionByZeroException;
import org.tpfinal.Interfaces.IntRepository;
import org.tpfinal.Interfaces.IntStrategy;
import org.tpfinal.Seat.Entity.Seat;

import java.util.List;
import java.util.UUID;

public class PPP implements IntStrategy {

    // TODO> throws DivisionByZeroException
    @Override
    public void add(List<Seat> balance, Seat add) throws DivisionByZeroException {
        Seat first = balance.getFirst();
        if(first == null){
            balance.add(add);
        }else{
            first.setAmount(first.getAmount() + add.getAmount());

            if (first.getAmount() == 0){
                throw new DivisionByZeroException();
            }
            first.
        }
    }

    @Override
    public Seat search(List<Seat> balance, UUID uuid) {
        return null;
    }

    @Override
    public void update(Seat toUpdate, Seat updated) {

    }

    @Override
    public List<Seat> delete(List<Seat> balance, Integer saleAmount) {

    }
}
