package br.com.CourseSpringBoot.dto;

import br.com.CourseSpringBoot.domain.City;
import br.com.CourseSpringBoot.domain.State;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fabricio
 */
@Getter
@Setter
public class StateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    public StateDTO(){

    }

    public StateDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public StateDTO(State state){
        id = state.getId();
        name = state.getName();
    }
}
