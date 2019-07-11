package br.com.CourseSpringBoot.exceptions;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author fabricio
 */
@Getter
@Setter
@ToString
public class ResponseMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long timestamp;
    private Integer status;
    private String error;
    private String msg;
    private String path;


    public ResponseMessage(){

    }

    public ResponseMessage(Long timestamp, Integer status, String error, String msg, String path) {
        super();
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.msg = msg;
        this.path = path;

    }
}
