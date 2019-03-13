package br.com.CourseSpringBoot.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ClientNewDTO  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String email;
    private String cpfOrCnpj;
    private Integer clientType;

    private String street;
    private String houseNumber;
    private String zipCode;

    // only phone1 is required
    private String phone1;
    private String phone2;
    private String phone3;

    private Integer cityId;

    public ClientNewDTO(){

    }


}
