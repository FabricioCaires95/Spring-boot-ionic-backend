package br.com.CourseSpringBoot.service;

import br.com.CourseSpringBoot.domain.Category;
import br.com.CourseSpringBoot.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author fabricio
 */

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;


    public Category findById(Integer id){
        Optional<Category> ob = repo.findById(id);
        return ob.orElse(null);
    }

}
