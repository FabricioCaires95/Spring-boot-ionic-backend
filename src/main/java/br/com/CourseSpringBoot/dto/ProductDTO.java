package br.com.CourseSpringBoot.dto;

import br.com.CourseSpringBoot.domain.Product;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author fabricio
 */

@Getter
@Setter
public class ProductDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private Double price;

    public ProductDTO(Product p){
        id = p.getId();
        name = p.getName();
        price = p.getPrice();
    }
}
