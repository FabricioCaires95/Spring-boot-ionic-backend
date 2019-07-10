package br.com.CourseSpringBoot.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author fabricio
 */
@Getter
@Setter
public class EmailDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "This Field cannnot be null")
    @Email(message = "Email invalid")
    private String email;

    public EmailDTO(){

    }

}
