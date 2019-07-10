package br.com.CourseSpringBoot.repositories;

import br.com.CourseSpringBoot.domain.Category;
import br.com.CourseSpringBoot.domain.Client;
import br.com.CourseSpringBoot.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author fabricio
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Transactional(readOnly = true)
    Page<Order> findOrderByClient(Client client, Pageable pageable);
}
