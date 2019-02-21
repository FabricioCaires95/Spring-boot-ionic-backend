package br.com.CourseSpringBoot.domain;

import br.com.CourseSpringBoot.enums.StatePayment;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;


/**
 * @author fabricio
 */

@Getter
@Setter
@Entity
public class CreditCard extends Payment{

    private static final long serialVersionUID = 1L;

    private Integer parcels;

    public CreditCard(Integer id, StatePayment state, Order order, Integer parcels) {
        super(id, state, order);
        this.parcels = parcels;
    }

    public CreditCard() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreditCard that = (CreditCard) o;

        return parcels.equals(that.parcels);
    }

    @Override
    public int hashCode() {
        return parcels.hashCode();
    }
}
