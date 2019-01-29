package br.com.CourseSpringBoot.exceptions;

/**
 * @author fabricio
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(){

    }


    public ResourceNotFoundException(String msg) {
        super(msg);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }


}
