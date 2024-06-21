package org.tpfinal.Strategies;

import org.tpfinal.Seat.Entity.Seat;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        PPP ppp = new PPP();
        ArrayList<Seat> list = new ArrayList<>();

        ppp.add(list, new Seat(2,2f));
        ppp.add(list, new Seat(3,3f));
        ppp.add(list, new Seat(4,4f));
//        for(Seat seat : list){
//            System.out.println(seat.getAmount());
//        }

        System.out.println("LISTA ORIGINAL NO MODIFICADA: ");
        for(int i = list.size() - 1; i >= 0; i--){
            System.out.println(list.get(i).getAmount()+ " : " + list.get(i).getUnitCost() + " : " + list.get(i).getTotalCost());

        }
        System.out.println("\n");

        List<Seat> response = ppp.delete(list, 10);

        System.out.println("LISTA ORIGINAL MODIFICADA: ");
        for(int i = list.size() - 1; i >= 0; i--){
            System.out.println(list.get(i).getAmount()+ " : " + list.get(i).getUnitCost() + " : " + list.get(i).getTotalCost());
        }
//        for(Seat seat : list){
//            System.out.println(seat.getAmount());
//        }
        System.out.println("\n");

        System.out.println("RESPUESTA: ");
        for(Seat seat : response){
            System.out.println(seat.getAmount()+ " : " + seat.getUnitCost() + " : " + seat.getTotalCost());
        }
    }
}
