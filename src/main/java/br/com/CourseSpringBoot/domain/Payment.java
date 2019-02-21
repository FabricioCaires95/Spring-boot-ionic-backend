package br.com.CourseSpringBoot.domain;

import br.com.CourseSpringBoot.enums.StatePayment;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author fabricio
 */
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    private Integer state;


    @OneToOne
    @JoinColumn(name = "order_id")
    @MapsId
    private Order order;

    public Payment(){

    }

    public Payment(Integer id, StatePayment state, Order order) {
        this.id = id;
        this.state = state.getCod();
        this.order = order;
    }

    public StatePayment getState(){
        return StatePayment.toEnum(state);
    }

    public void setState(StatePayment state){
        this.state = state.getCod();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        return id.equals(payment.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
