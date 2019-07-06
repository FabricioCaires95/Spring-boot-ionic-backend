package br.com.CourseSpringBoot.dto;

import br.com.CourseSpringBoot.service.validation.ClientInsert;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@ClientInsert
public class ClientNewDTO  implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "This Field cannnot be null")
    @Size(min = 3, max = 80, message = "The Field's length must be between 5 and 80 characters")
    private String name;

    @NotEmpty(message = "This Field cannnot be null")
    @Email
    private String email;

    @NotEmpty(message = "This Field cannnot be null")
    private String cpfOrCnpj;
    private Integer clientType;

    @NotEmpty(message = "This Field cannot be null")
    private String password;

    @NotEmpty(message = "This Field cannnot be null")
    private String street;
    private String houseNumber;

    @NotEmpty(message = "This Field cannnot be null")
    private String zipCode;

    // only phone1 is required
    @NotEmpty(message = "This Field cannnot be null")
    private String phone1;
    private String phone2;
    private String phone3;

    private Integer cityId;

    public ClientNewDTO(){

    }


}
