package br.com.CourseSpringBoot.service;

import br.com.CourseSpringBoot.domain.Client;
import br.com.CourseSpringBoot.domain.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
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

    @Override
    public void sendNewPasswordEmail(Client client, String newPassoword) {
        SimpleMailMessage msg = prepareNewPasswordEmail(client, newPassoword);
        sendEmail(msg);
    }


    protected SimpleMailMessage prepareNewPasswordEmail(Client client, String newPassword){

        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(client.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Your new password");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setTo("Your new passoword is: " + newPassword);

        return sm;
    }
}
