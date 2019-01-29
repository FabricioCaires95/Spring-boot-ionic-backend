package br.com.CourseSpringBoot.resources;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * @author fabricio
 */
@Getter
@Setter
@ToString
public class ResponseMessage {

    private Integer status;
    private String msg;
    private Long timestamp;
    private Object response;

    public ResponseMessage(Integer status, String msg, Long timestamp) {
        this.status = status;
        this.msg = msg;
        this.timestamp = timestamp;

    }
}
