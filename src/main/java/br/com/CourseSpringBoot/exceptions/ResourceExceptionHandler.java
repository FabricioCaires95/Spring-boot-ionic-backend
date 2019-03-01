package br.com.CourseSpringBoot.exceptions;

import br.com.CourseSpringBoot.domain.Category;
import br.com.CourseSpringBoot.resources.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * @author fabricio
 */
@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ResponseMessage> resourceNotFound(ResourceNotFoundException e, HttpServletRequest req){

        ResponseMessage message = new ResponseMessage(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

    }

    @ExceptionHandler(value = DataIntegrityException.class)
    public ResponseEntity<ResponseMessage> dataIntegrityViolation(DataIntegrityException e, HttpServletRequest req){

        ResponseMessage message = new ResponseMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);

    }


}
