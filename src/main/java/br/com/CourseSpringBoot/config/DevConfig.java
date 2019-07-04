package br.com.CourseSpringBoot.config;

import br.com.CourseSpringBoot.service.DbService;
import br.com.CourseSpringBoot.service.EmailService;
import br.com.CourseSpringBoot.service.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

/**
 * @author fabricio
 */
@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DbService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;
    @Bean
    public boolean instantiateDatabase() throws ParseException {

        if (!"create".equals(strategy)){
            return false;
        }

        dbService.instantiateDatabase();

        return true;
    }

    @Bean
    public EmailService emailService(){
        return new SmtpEmailService();
    }
}
