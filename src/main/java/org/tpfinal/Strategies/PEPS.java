package org.tpfinal.Strategies;

import org.tpfinal.Interfaces.IntStrategy;
import org.tpfinal.Seat.Entity.Seat;

import java.util.List;
import java.util.UUID;

public class PEPS implements IntStrategy {

    @Override
    public void add(List<Seat> balance, Seat add) {

    }

    @Override
    public List<Seat> getList() {
        return List.of();
    }

    @Override
    public Seat search(UUID uuid) {
        return null;
    }

    @Override
    public void update(List<Seat> balance, Seat toUpdate, Seat updated) {

    }

    @Override
    public void delete(List<Seat> balance, Seat toDelete) {

    }
}
