package br.com.CourseSpringBoot.service;


import br.com.CourseSpringBoot.domain.*;
import br.com.CourseSpringBoot.enums.StatePayment;
import br.com.CourseSpringBoot.exceptions.AuthorizationException;
import br.com.CourseSpringBoot.exceptions.ResourceNotFoundException;
import br.com.CourseSpringBoot.repositories.OrderItemRepository;
import br.com.CourseSpringBoot.repositories.OrderRepository;
import br.com.CourseSpringBoot.repositories.PaymentRepository;
import br.com.CourseSpringBoot.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * @author fabricio
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private EmailService emailService;

    public Order findById(Integer id){
        Optional<Order> ob = orderRepository.findById(id);
        return ob.orElseThrow(() -> new ResourceNotFoundException("Object not found" + Order.class.getName()));
    }

    public Order insert(Order order){
        order.setDate(new Date());
        order.getPayment().setState(StatePayment.PENDENT);
        order.setClient(clientService.findById(order.getClient().getId()));
        order.getPayment().setOrder(order);

        order = orderRepository.save(order);
        paymentRepository.save(order.getPayment());

        for (OrderItem op: order.getOrderItems()){
            op.setDiscount(0.0);
            op.setProduct(productService.findById(op.getProduct().getId()));
            op.setPrice(op.getProduct().getPrice());
            op.setOrder(order);
        }
        orderItemRepository.saveAll(order.getOrderItems());

        //emailService.sendOrderConfirmationHtmlEmail(order);

        return order;
    }

    public Page<Order> findPage(Integer page, Integer linesPerPage, String orderby, String direction){

        UserSS userSS = UserService.authenticated();

        if (userSS == null){
            throw new AuthorizationException("Access Denied");
        }

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderby);
        Client client = clientService.findById(userSS.getId());

        return orderRepository.findOrderByClient(client, pageRequest);
    }
}
