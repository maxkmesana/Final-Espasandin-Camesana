package org.tpfinal.Exception;

public class PasswordLimitException extends RuntimeException{
    public PasswordLimitException() {
        super ("Password should have between 8 and 12 characters.");
    }
}
