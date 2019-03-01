package br.com.CourseSpringBoot.dto;

import br.com.CourseSpringBoot.domain.Category;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author fabricio
 */
@Getter
@Setter
public class CategoryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "This Field cannnot be null")
    @Size(min = 3, max = 80, message = "The Field's length must be between 5 and 80 characters")
    private String name;

    public CategoryDTO(){

    }

    public CategoryDTO(Category cat){
        id = cat.getId();
        name = cat.getName();
    }

}
