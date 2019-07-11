package br.com.CourseSpringBoot.dto;

import br.com.CourseSpringBoot.domain.City;
import br.com.CourseSpringBoot.domain.State;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author fabricio
 */
@Getter
@Setter
public class CityDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;


    public CityDTO(){

    }

    public CityDTO(Integer id, String name) {
        this.id = id;
        this.name = name;

    }

    public CityDTO(City city){
        id = city.getId();
        name = city.getName();
    }
}
