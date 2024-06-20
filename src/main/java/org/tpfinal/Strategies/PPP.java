package org.tpfinal.Strategies;

import org.tpfinal.Exception.DivisionByZeroException;
import org.tpfinal.Exception.LessThanZeroException;
import org.tpfinal.Interfaces.IntRepository;
import org.tpfinal.Interfaces.IntStrategy;
import org.tpfinal.Seat.Entity.Seat;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PPP implements IntStrategy{

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
            first.setTotalCost(first.getTotalCost() + add.getTotalCost());
            first.setUnitCost(first.getTotalCost() / first.getUnitCost());
        }
    }
    @Override
    public Seat search(List<Seat> balance, UUID uuid) {
        return balance.getFirst();
    }

    @Override
    public void update(Seat toUpdate, Seat updated) {
        toUpdate.setAmount(updated.getAmount());
        toUpdate.setUnitCost(updated.getUnitCost());
    }

    @Override
    public List<Seat> delete(List<Seat> balance, Integer saleAmount) throws LessThanZeroException {
        Seat first = balance.getFirst();

        first.setAmount(first.getAmount() - saleAmount);
        if (first.getAmount() < 0){
            throw new LessThanZeroException();
        }
        first.setTotalCost(first.getAmount() * first.getUnitCost());

        return balance;
    }

}
