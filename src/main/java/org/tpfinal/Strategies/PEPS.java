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

    }

    @Override
    public void update(List<Seat> balance, Seat toUpdate, Seat updated) {

    }

    @Override
    public void delete(List<Seat> balance, Seat toDelete) {

    }
}
