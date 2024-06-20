package org.tpfinal.Exception;

public class DivisionByZeroException extends RuntimeException {
    public DivisionByZeroException() {
        super("No se puede realizar una division por 0.");
    }

    public DivisionByZeroException(String message) {
        super(message);
    }
}
