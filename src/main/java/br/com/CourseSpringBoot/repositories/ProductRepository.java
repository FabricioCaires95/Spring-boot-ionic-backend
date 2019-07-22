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

    @Transactional(readOnly = true)
    @Query(value = "SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories cat WHERE obj.name LIKE %:name% AND cat IN :categories", nativeQuery = false)
    Page<Product> findDistinctByNameContainingAndCategoriesIn(@Param("name") String name, @Param("categories") List<Category>categories, Pageable pageRequest);

    @Transactional(readOnly = true)
    @Query(value = "SELECT P.NAME, P.ID, P.PRICE FROM PRODUCT P\n" +
            "INNER JOIN CATEGORY_PRODUCT CP ON P.ID = CP.PRODUCT_ID \n" +
            "INNER JOIN CATEGORY C ON C.ID = CP.CATEGORY_ID\n" +
            "WHERE CATEGORY_ID = ?1 ", nativeQuery = true)
    List<Product> findAllByCategoriesId(Integer id);

}
