package br.com.CourseSpringBoot.resources;

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

    private Integer status;
    private String msg;
    private Long timestamp;


    public ResponseMessage(Integer status, String msg, Long timestamp) {
        this.status = status;
        this.msg = msg;
        this.timestamp = timestamp;

    }
}
