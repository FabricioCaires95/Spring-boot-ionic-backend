package br.com.CourseSpringBoot.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @author fabricio
 */
@Getter
@Setter
@Entity
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();

    private Double discount;
    private Integer amount;
    private Double price;

    public OrderItem(){

    }

    public OrderItem(Order order, Product product ,Double discount, Integer amount, Double price) {
        id.setOrder(order);
        id.setProduct(product);
        this.discount = discount;
        this.amount = amount;
        this.price = price;
    }

    public Order getOrder(){
        return id.getOrder();
    }

    public Product getProduct(){
        return id.getProduct();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;

        return id.equals(orderItem.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
