package br.com.CourseSpringBoot.service;

import br.com.CourseSpringBoot.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

/**
 * @author fabricio
 */
public class MockEmailService extends AbstractEmailService {

    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage simpleMailMessage) {
        LOG.info("Simulando envio de email ");
        LOG.info(simpleMailMessage.toString());
        LOG.info("Email enviado ");
    }

    //@Override
    public void sendHtmlEmail(MimeMessage msg) {
        LOG.info("Simulando envio de email ");
        LOG.info(msg.toString());
        LOG.info("Email enviado ");
    }

    //@Override
    public void sendOrderConfirmationHtmlEmail(Order order) {

    }
}
