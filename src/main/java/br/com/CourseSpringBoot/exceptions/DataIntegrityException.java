package br.com.CourseSpringBoot.exceptions;

/**
 * @author fabricio
 */
public class DataIntegrityException extends RuntimeException {

    public DataIntegrityException(){

    }


    public DataIntegrityException(String msg) {
        super(msg);
    }

    public DataIntegrityException(String message, Throwable cause) {
        super(message, cause);
    }


}
