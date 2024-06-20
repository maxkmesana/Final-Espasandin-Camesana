package org.tpfinal.Exception;

public class EmptyFieldException extends RuntimeException{
    public EmptyFieldException() {
        super ("There should be no empty fields");
    }
}
