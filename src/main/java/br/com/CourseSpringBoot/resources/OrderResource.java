package br.com.CourseSpringBoot.resources;

import br.com.CourseSpringBoot.domain.Category;
import br.com.CourseSpringBoot.domain.Order;
import br.com.CourseSpringBoot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * @author fabricio
 */
@RestController
@RequestMapping("/order")
public class OrderResource {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable Integer id){

        Order order  = orderService.findById(id);


        return ResponseEntity.ok().body(order);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody Order order){

       order = orderService.insert(order);


        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(order.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping()
    public ResponseEntity<Page<Order>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24")Integer linesPerPage,
            @RequestParam(value = "orderby", defaultValue = "date")String orderby,
            @RequestParam(value = "direction", defaultValue = "DESC")String direction){

        Page<Order> list = orderService.findPage(page, linesPerPage, orderby, direction);


        return ResponseEntity.ok(list);
    }
}
