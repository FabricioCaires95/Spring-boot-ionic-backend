package br.com.CourseSpringBoot.repositories;



import br.com.CourseSpringBoot.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author fabricio
 */
@Repository
public interface StateRepository extends JpaRepository<State, Integer> {


}
