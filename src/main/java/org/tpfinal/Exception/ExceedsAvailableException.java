package org.tpfinal.Exception;

public class ExceedsAvailableException extends RuntimeException{
    public ExceedsAvailableException() {
        super("The sale amount exceeds available units.");
    }

    public ExceedsAvailableException(String message) {
        super(message);
    }
}
