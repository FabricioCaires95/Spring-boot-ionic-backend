package br.com.CourseSpringBoot.exceptions;

import lombok.Getter;


import java.util.ArrayList;
import java.util.List;

/**
 * @author fabricio
 */

public class ValidationError extends ResponseMessage {

    private List<FieldMessage> listErrors = new ArrayList<>();

    public ValidationError(Long timestamp, Integer status, String error, String msg, String path) {
        super(timestamp, status, error, msg, path);

    }

    public List<FieldMessage> getErrors(){
        return listErrors;
    }

    public void addError(String fieldName, String msg){
        listErrors.add(new FieldMessage(fieldName,msg));
    }

}
