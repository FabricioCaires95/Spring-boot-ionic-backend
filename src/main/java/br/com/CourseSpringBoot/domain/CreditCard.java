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
@JsonTypeName("PaymentWithCreditCard")
public class CreditCard extends Payment{

    private static final long serialVersionUID = 1L;

    private Integer parcels;

    public CreditCard(Integer id, StatePayment state, Order order, Integer parcels) {
        super(id, state, order);
        this.parcels = parcels;
    }

    public CreditCard() {
    }

}
