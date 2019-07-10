package br.com.CourseSpringBoot.service;

import br.com.CourseSpringBoot.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

/**
 * @author fabricio
 */
public class SmtpEmailService extends AbstractEmailService{

    @Autowired
    private MailSender mailSender;

    @Autowired
    private JavaMailSender javaMailSender;

    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage simpleMailMessage) {
        LOG.info("Simulando envio de email ");
        mailSender.send(simpleMailMessage);
        LOG.info("E-mail enviado");
    }

    //@Override
    public void sendHtmlEmail(MimeMessage msg) {
        LOG.info("Simulando envio de email ");
        javaMailSender.send(msg);
        LOG.info("E-mail enviado");
    }

   // @Override
    public void sendOrderConfirmationHtmlEmail(Order order) {

    }
}
