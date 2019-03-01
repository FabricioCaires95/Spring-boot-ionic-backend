package br.com.CourseSpringBoot.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author fabricio
 */
@Getter
@Setter
@EqualsAndHashCode
@Entity
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;


    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    public City(Integer id, String name, State state) {
        super();
        this.id = id;
        this.name = name;
        this.state = state;
    }

    public City(){

    }


}
