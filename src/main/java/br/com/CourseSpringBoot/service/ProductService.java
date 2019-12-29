package br.com.CourseSpringBoot.service;

import br.com.CourseSpringBoot.domain.Category;
import br.com.CourseSpringBoot.domain.Product;
import br.com.CourseSpringBoot.exceptions.ResourceNotFoundException;
import br.com.CourseSpringBoot.repositories.CategoryRepository;
import br.com.CourseSpringBoot.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author fabricio
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Product findById(Integer id){
        Optional<Product> ob = productRepository.findById(id);
        return ob.orElseThrow(() -> new ResourceNotFoundException(("Object not found  " + Product.class.getName())));
    }

    public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderby, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderby);
        List<Category> categories = categoryRepository.findAllById(ids);
        return productRepository.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);
    }

//    public List<Product> find(Integer id){
//
//        return productRepository.findAllByCategoriesId(id);
//    }

}
