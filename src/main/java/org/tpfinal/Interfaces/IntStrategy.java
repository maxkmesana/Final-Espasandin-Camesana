package org.tpfinal.Interfaces;

import org.tpfinal.Seat.Entity.Seat;

import java.util.List;
import java.util.UUID;

public interface IntStrategy {
    void add(List<Seat> balance, Seat add);
    List<Seat> getList();
    Seat search(UUID uuid);
    void update(List<Seat> balance, Seat toUpdate, Seat updated);
    void delete(List<Seat> balance, Seat toDelete);
}
