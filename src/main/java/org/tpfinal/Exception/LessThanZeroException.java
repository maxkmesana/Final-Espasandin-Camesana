package org.tpfinal.Exception;

public class LessThanZeroException extends RuntimeException{
    public LessThanZeroException() {
        super("The amount cannot be less than zero");
    }

    public LessThanZeroException(String message) {
        super(message);
    }
}
