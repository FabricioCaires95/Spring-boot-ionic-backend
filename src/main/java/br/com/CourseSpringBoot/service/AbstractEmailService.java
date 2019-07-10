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

    @Autowired
    private TemplateEngine templateEngine;

//    @Autowired
//    private JavaMailSender javaMailSender;

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

    protected String htmlFromTemplatePedido(Order order){
        Context context = new Context();
        context.setVariable("order", order);

        return templateEngine.process("orderConfirmation", context);
    }

//    @Override
//    public void sendOrderConfirmationHtmlEmail(Order order) {
//        try {
//            MimeMessage mm = prepareMimeMessage(order);
//            sendHtmlEmail(mm);
//        } catch (MessagingException e){
//            sendOrderConfirmation(order);
//        }
//
//    }
//
//    protected MimeMessage prepareMimeMessage(Order order) throws MessagingException {
//
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
//        mimeMessageHelper.setTo(order.getClient().getEmail());
//        mimeMessageHelper.setFrom(sender);
//        mimeMessageHelper.setSubject("Order Confirmation: " + order.getId());
//        mimeMessageHelper.setSentDate(new Date(System.currentTimeMillis()));
//        mimeMessageHelper.setText(htmlFromTemplatePedido(order), true);
//
//        return mimeMessage;
//
//    }

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
