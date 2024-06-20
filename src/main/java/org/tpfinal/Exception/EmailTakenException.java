package org.tpfinal.Exception;

public class EmailTakenException extends RuntimeException{
    public EmailTakenException() {
        super ("Email already taken. Please use another one.");
    }
}
