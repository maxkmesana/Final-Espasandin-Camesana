package org.tpfinal.Exception;

public class EmptyFieldException extends RuntimeException{
    public EmptyFieldException() {
        super ("No debe haber campos vacios.");
    }
}
