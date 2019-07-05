package br.com.CourseSpringBoot.service;


import br.com.CourseSpringBoot.domain.Order;
import br.com.CourseSpringBoot.domain.OrderItem;
import br.com.CourseSpringBoot.domain.PayPal;
import br.com.CourseSpringBoot.domain.Product;
import br.com.CourseSpringBoot.enums.StatePayment;
import br.com.CourseSpringBoot.exceptions.ResourceNotFoundException;
import br.com.CourseSpringBoot.repositories.OrderItemRepository;
import br.com.CourseSpringBoot.repositories.OrderRepository;
import br.com.CourseSpringBoot.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
