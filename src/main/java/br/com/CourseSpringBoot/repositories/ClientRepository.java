package br.com.CourseSpringBoot.repositories;

import br.com.CourseSpringBoot.domain.Category;
import br.com.CourseSpringBoot.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author fabricio
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {


}
