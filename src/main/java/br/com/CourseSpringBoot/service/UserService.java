package br.com.CourseSpringBoot.service;

import br.com.CourseSpringBoot.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author fabricio
 */
public class UserService {

    public static UserSS authenticated(){
        try {

            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        } catch (Exception e){

            return null;
        }
    }
}
