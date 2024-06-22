package org.tpfinal.Interfaces;

import org.tpfinal.Exception.DivisionByZeroException;
import org.tpfinal.Exception.ExceedsAvailableException;
import org.tpfinal.Seat.Entity.Seat;

import java.util.List;
import java.util.UUID;

public interface IntStrategy {
    void add(List<Seat> balance, Seat add);
    Seat search(List<Seat> balance, UUID uuid);
    void update(Seat toUpdate, Seat updated);
    List<Seat> delete(List<Seat> balance, Integer saleAmount);
}
