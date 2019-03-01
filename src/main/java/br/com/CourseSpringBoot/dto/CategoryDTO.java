package br.com.CourseSpringBoot.dto;

import br.com.CourseSpringBoot.domain.Category;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author fabricio
 */
@Getter
@Setter
public class CategoryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;

    public CategoryDTO(){

    }

    public CategoryDTO(Category cat){
        id = cat.getId();
        name = cat.getName();
    }

}
