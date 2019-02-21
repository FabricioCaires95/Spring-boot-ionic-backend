package br.com.CourseSpringBoot.repositories;

import br.com.CourseSpringBoot.domain.Order;
import br.com.CourseSpringBoot.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author fabricio
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {


}
