package br.com.CourseSpringBoot.service;

import br.com.CourseSpringBoot.domain.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

/**
 * @author fabricio
 */
public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;


    @Override
    public void sendOrderConfirmation(Order order) {
        SimpleMailMessage msg = prepareSimpleMailMessageFromOrder(order);
        sendEmail(msg);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromOrder(Order order){

        SimpleMailMessage sm = new SimpleMailMessage();

        sm.setTo(order.getClient().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Your Order has been done  " + order.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(order.toString());

        return sm;
    }
}
