package br.com.CourseSpringBoot.repositories;

import br.com.CourseSpringBoot.domain.Category;
import br.com.CourseSpringBoot.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author fabricio
 */
@Repository
public interface ProductRepository  extends JpaRepository<Product, Integer> {

//    @Query(value = "SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories cat" +
//            " WHERE obj.name LIKE %:name% AND cat IN :categories")
//    Page<Product> search(@Param("name") String name, @Param("categories") List<Category>categories, Pageable pageRequest);

    @Transactional(readOnly = true)
    Page<Product> findDistinctByNameContainingAndCategoriesIn(String name,List<Category>categories, Pageable pageRequest);

}
