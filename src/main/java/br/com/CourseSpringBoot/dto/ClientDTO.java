package br.com.CourseSpringBoot.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author fabricio
 */
@Getter
@Setter
public class ClientDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String email;
    private String cpfOrCnpj;
    private Integer clientType;

    public ClientDTO(){

    }




}
