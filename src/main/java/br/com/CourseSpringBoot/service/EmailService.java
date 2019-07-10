package br.com.CourseSpringBoot.service;

import br.com.CourseSpringBoot.domain.Client;
import br.com.CourseSpringBoot.domain.Order;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

/**
 * @author fabricio
 */
public interface EmailService {

    void sendOrderConfirmation(Order order);


    void sendEmail(SimpleMailMessage simpleMailMessage);


    void sendNewPasswordEmail(Client client, String newPassoword);
}
