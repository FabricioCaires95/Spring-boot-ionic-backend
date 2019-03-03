package br.com.CourseSpringBoot.service;

import br.com.CourseSpringBoot.domain.Category;

import br.com.CourseSpringBoot.dto.CategoryDTO;
import br.com.CourseSpringBoot.exceptions.DataIntegrityException;
import br.com.CourseSpringBoot.exceptions.ResourceNotFoundException;
import br.com.CourseSpringBoot.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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
        return ob.orElseThrow(() ->
                new ResourceNotFoundException("Category not found" + Category.class.getName()));
    }

    public List<Category> findAll(){
        return repo.findAll();
    }

    public Category insert(Category cat){

        cat.setId(null);
        return repo.save(cat);
    }


    public Category update(Category cat){

        findById(cat.getId());
        return repo.save(cat);
    }

    public void delete(Integer id){
        findById(id);
        try {
            repo.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("This Category cannot be deleted, Referential integrity constraint violation");
        }

    }

    public Page<Category> findPage(Integer page, Integer linesPerPage, String orderby, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderby);

        return repo.findAll(pageRequest);
    }

    public Category toCategory(CategoryDTO catDTO){
        return new Category(catDTO.getId(), catDTO.getName());
    }

}
