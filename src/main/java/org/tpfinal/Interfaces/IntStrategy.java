package org.tpfinal.Interfaces;

import org.tpfinal.Seat.Entity.Seat;

import java.util.List;
import java.util.UUID;

public interface IntStrategy {
    void add(List<Seat> balance, Seat add);
    Seat search(List<Seat> balance, UUID uuid);
    void update(Seat toUpdate, Seat updated);
    void delete(List<Seat> balance);
}
