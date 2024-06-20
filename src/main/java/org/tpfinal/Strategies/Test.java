package org.tpfinal.Strategies;

import org.tpfinal.Seat.Entity.Seat;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        UEPS ueps = new UEPS();
        ArrayList<Seat> list = new ArrayList<>();

        ueps.add(list, new Seat(2,2f));
        ueps.add(list, new Seat(3,3f));
        ueps.add(list, new Seat(4,4f));
//        for(Seat seat : list){
//            System.out.println(seat.getAmount());
//        }

        System.out.println("LISTA ORIGINAL NO MODIFICADA: ");
        for(int i = list.size() - 1; i >= 0; i--){
            System.out.println(list.get(i).getAmount());
        }
        System.out.println("\n");

        List<Seat> response = ueps.delete(list, 5);

        System.out.println("LISTA ORIGINAL MODIFICADA: ");
        for(int i = list.size() - 1; i >= 0; i--){
            System.out.println(list.get(i).getAmount());
        }
//        for(Seat seat : list){
//            System.out.println(seat.getAmount());
//        }
        System.out.println("\n");

        System.out.println("RESPUESTA: ");
        for(Seat seat : response){
            System.out.println(seat.getAmount());
        }
    }
}
