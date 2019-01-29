package br.com.CourseSpringBoot.repositories;

import br.com.CourseSpringBoot.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author fabricio
 */
@Repository
public interface ProductRepository  extends JpaRepository<Product, Integer> {




}
