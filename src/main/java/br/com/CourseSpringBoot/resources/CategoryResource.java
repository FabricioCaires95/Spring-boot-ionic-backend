package br.com.CourseSpringBoot.resources;

import br.com.CourseSpringBoot.domain.Category;
import br.com.CourseSpringBoot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fabricio
 */

@RestController
@RequestMapping("/categories")
public class CategoryResource {

    @Autowired
    private CategoryService service;


    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Integer id){

        Category cat = service.findById(id);


        return ResponseEntity.ok().body(cat);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Category cat){
        cat = service.insert(cat);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(cat.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCategory(@RequestBody Category cat, @PathVariable Integer id){
        cat.setId(id);
        cat = service.updateCategory(cat);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){

        service.delete(id);

        return ResponseEntity.noContent().build();

    }

}
