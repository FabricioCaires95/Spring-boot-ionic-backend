package br.com.CourseSpringBoot.service;

import br.com.CourseSpringBoot.domain.Order;
import org.springframework.mail.SimpleMailMessage;

/**
 * @author fabricio
 */
public interface EmailService {

    void sendOrderConfirmation(Order order);


    void sendEmail(SimpleMailMessage simpleMailMessage);

}
