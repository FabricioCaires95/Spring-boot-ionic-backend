package br.com.CourseSpringBoot.domain;

import br.com.CourseSpringBoot.enums.StatePayment;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;


/**
 * @author fabricio
 */

@Getter
@Setter
@EqualsAndHashCode
@Entity
@JsonTypeName("PaymentWithPayPal")
public class PayPal extends Payment {

    private static final long serialVersionUID = 1L;

    private String email;
    private String password;


    public  PayPal(){

    }

    public PayPal(Integer id, StatePayment state, Order order, String email, String password) {
        super(id, state, order);
        this.email = email;
        this.password = password;
    }
}
