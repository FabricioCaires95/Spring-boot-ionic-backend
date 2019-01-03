package br.com.CourseSpringBoot.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fabricio
 */

@RestController
@RequestMapping("/categories")
public class CategoryResource {

    @GetMapping("/list")
    public String listar(){
        return "SPRING IS WORKING AND DOING WELL";
    }
}
