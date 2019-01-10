package br.com.CourseSpringBoot.resources;

import br.com.CourseSpringBoot.domain.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fabricio
 */

@RestController
@RequestMapping("/categories")
public class CategoryResource {

    @GetMapping("/list")
    public List<Category> listar(){

        Category c1 = new Category(1, "Informática");
        Category c2 = new Category(2, "Escritório");

        List<Category> list = new ArrayList<>();
        list.add(c1);
        list.add(c2);

        return list;
    }
}
