package org.home.exceptions;

public class BadInputListDataException extends Exception {
    public BadInputListDataException() { super(); }
    public BadInputListDataException(String message) { super(message); }
    public BadInputListDataException(String message, Throwable cause) { super(message, cause); }
    public BadInputListDataException(Throwable cause) { super(cause); }
}
