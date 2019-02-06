package br.com.CourseSpringBoot.repositories;


import br.com.CourseSpringBoot.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author fabricio
 */
@Repository
public interface CityRepository extends JpaRepository<City, Integer> {


}
