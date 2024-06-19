package org.tpfinal.Interfaces;

import org.tpfinal.Seat.Entity.Seat;

import java.util.Date;
import java.util.List;

public interface IntStrategy {
    void add(List<Seat> balance, Seat add);
    void update(List<Seat> balance, Date date, String activity, Seat purchase, Seat Sale);
    List<Seat> get();
}
