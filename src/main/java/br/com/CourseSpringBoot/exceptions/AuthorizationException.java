package br.com.CourseSpringBoot.exceptions;

/**
 * @author fabricio
 */
public class AuthorizationException extends RuntimeException {

    private static final long serialVersionUID = 1L;


    public AuthorizationException(String msg) {
        super(msg);
    }

    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }


}
