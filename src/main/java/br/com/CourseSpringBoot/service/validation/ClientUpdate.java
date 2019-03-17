package br.com.CourseSpringBoot.service.validation;

import br.com.CourseSpringBoot.service.validation.ClientInsertValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author fabricio
 */

@Constraint(validatedBy = ClientUpdateValidation.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ClientUpdate {

    String message() default "validation error";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
