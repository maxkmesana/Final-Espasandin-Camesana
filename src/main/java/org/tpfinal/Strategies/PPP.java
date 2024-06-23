package org.tpfinal.Strategies;

import org.tpfinal.Exception.DivisionByZeroException;
import org.tpfinal.Exception.ExceedsAvailableException;
import org.tpfinal.Interfaces.IntStrategy;
import org.tpfinal.Seat.Entity.Seat;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PPP implements IntStrategy{

    @Override
    public void add(List<Seat> balance, Seat add) throws DivisionByZeroException {
        if(balance.isEmpty()){
            balance.add(add);
        }else{
            Seat previous = balance.getFirst();
            if (previous.getAmount() == 0){
                throw new DivisionByZeroException();
            }
            Seat result = new Seat(previous.getTotalCost()+add.getTotalCost(),
                    previous.getAmount()+add.getAmount());
            balance.remove(previous);
            balance.add(result);
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
    public List<Seat> delete(List<Seat> balance, Integer saleAmount) throws ExceedsAvailableException {

        // TODO: descontar saleAmount a first. Aniadir a respuesta (RECALCULAR TOTALCOST). Retornar respuesta
        List<Seat> seatReturn = new ArrayList<>();
        Seat first = balance.getFirst();

        first.setAmount(first.getAmount() - saleAmount);
        if (first.getAmount() < 0){
            throw new ExceedsAvailableException();
        }
        seatReturn.add(new Seat(saleAmount, first.getUnitCost()));
        first.setTotalCost(first.getAmount() * first.getUnitCost());

        return seatReturn;
    }

    public String toString(){
        return "WAC";
    }

}
