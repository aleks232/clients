package com.client.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Vladimir Ugay
 * Date: 15.09.13
 * Time: 15:19
 */
public class ClientException extends RuntimeException {
    private static final long serialVersionUID = -4484860407219708897L;

    public ClientException() {
    }

    public ClientException(String message) {
        super(message);
    }

    public ClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientException(Throwable cause) {
        super(cause);
    }
}
