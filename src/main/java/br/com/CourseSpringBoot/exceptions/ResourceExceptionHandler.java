package br.com.CourseSpringBoot.exceptions;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

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

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseMessage> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest req){

        ValidationError error  = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Validation Error", System.currentTimeMillis());

        for (FieldError x : e.getBindingResult().getFieldErrors()){
            error.addError(x.getField(), x.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }

    @ExceptionHandler(value = AuthorizationException.class)
    public ResponseEntity<ResponseMessage> authorization(AuthorizationException e, HttpServletRequest req){

        ResponseMessage message = new ResponseMessage(HttpStatus.FORBIDDEN.value(), e.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(message);

    }

    @ExceptionHandler(value = FileException.class)
    public ResponseEntity<ResponseMessage> file(FileException e, HttpServletRequest req){

        ResponseMessage message = new ResponseMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);

    }

    @ExceptionHandler(value = AmazonServiceException.class)
    public ResponseEntity<ResponseMessage> amazonService(AmazonServiceException e, HttpServletRequest req){
        HttpStatus codeError = HttpStatus.valueOf(e.getErrorCode());
        ResponseMessage message = new ResponseMessage(codeError.value(), e.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(codeError).body(message);

    }

    @ExceptionHandler(value = AmazonS3Exception.class)
    public ResponseEntity<ResponseMessage> amazonS3(AmazonS3Exception e, HttpServletRequest req){

        ResponseMessage message = new ResponseMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);

    }

    @ExceptionHandler(value = AmazonClientException.class)
    public ResponseEntity<ResponseMessage> amazonClient(AmazonClientException e, HttpServletRequest req){

        ResponseMessage message = new ResponseMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);

    }





}
