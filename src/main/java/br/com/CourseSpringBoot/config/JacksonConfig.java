package br.com.CourseSpringBoot.config;

import br.com.CourseSpringBoot.domain.CreditCard;
import br.com.CourseSpringBoot.domain.PayPal;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @author fabricio
 */
@Configuration
public class JacksonConfig {


    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder(){
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder(){
            public void configure(ObjectMapper objectMapper){
                objectMapper.registerSubtypes(PayPal.class);
                objectMapper.registerSubtypes(CreditCard.class);
                super.configure(objectMapper);
            }
        };
        return builder;
    }

}
