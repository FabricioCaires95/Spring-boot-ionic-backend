package br.com.CourseSpringBoot.repositories;



import br.com.CourseSpringBoot.domain.State;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author fabricio
 */
@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

    @Transactional(readOnly = true)
    List<State> findAllByOrderByName();

}
