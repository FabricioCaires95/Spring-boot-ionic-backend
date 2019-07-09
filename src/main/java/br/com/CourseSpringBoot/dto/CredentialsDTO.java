package br.com.CourseSpringBoot.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author fabricio
 */
@Getter
@Setter
public class CredentialsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;
    private String password;

    public CredentialsDTO() {

    }
}
