package br.com.CourseSpringBoot.dto;

import br.com.CourseSpringBoot.domain.Client;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author fabricio
 */
@Getter
@Setter
public class ClientDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "This Field cannnot be null")
    @Size(min = 3, max = 120, message = "The Field's length must be between 5 and 120 characters")
    private String name;

    @NotEmpty(message = "This Field cannnot be null")
    @Email(message = "Email invalid")
    private String email;


    public ClientDTO(){

    }

    public ClientDTO(Client cli){
        id = cli.getId();
        name = cli.getName();
        email = cli.getEmail();
    }



}
