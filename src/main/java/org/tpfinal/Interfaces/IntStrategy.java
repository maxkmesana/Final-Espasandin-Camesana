package org.tpfinal.Interfaces;

import org.tpfinal.Seat.Entity.Seat;

import java.util.Date;
import java.util.List;

public interface IntStrategy {
    void add(List<Seat> balance, Seat add);
    List<Seat> getList();
    Seat search(Integer id);
    void update(List<Seat> balance, Seat toUpdate, Seat updated);
    void delete(List<Seat> balance, Seat toDelete);
}
