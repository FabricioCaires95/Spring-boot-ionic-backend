package br.com.CourseSpringBoot.resources;

import br.com.CourseSpringBoot.domain.Category;
import br.com.CourseSpringBoot.domain.Order;
import br.com.CourseSpringBoot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fabricio
 */
@RestController
@RequestMapping("/order")
public class OrderResource {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id){

        Order order  = orderService.findById(id);


        return ResponseEntity.ok().body(order);
    }
}
