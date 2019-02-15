package br.com.CourseSpringBoot.repositories;

import br.com.CourseSpringBoot.domain.Address;
import br.com.CourseSpringBoot.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author fabricio
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {


}
