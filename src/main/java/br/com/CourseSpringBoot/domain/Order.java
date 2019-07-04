package br.com.CourseSpringBoot.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * @author fabricio
 */

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "order_table")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date date;


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private Payment payment;


    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> orderItems = new HashSet<>();

    public Order(Integer id, Date date, Client client, Address address) {
        this.id = id;
        this.date = date;
        this.client = client;
        this.address = address;
    }

    public Order(){

    }

    public double OrderValueTotal(){
        double sum = 0.0;
        for (OrderItem op: orderItems){
            sum = sum + op.getSubTotal();
        }

        return sum;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt" , "BR"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        builder.append("Order number ");
        builder.append(getId());
        builder.append("Instante ");
        builder.append(sdf.format(getDate()));
        builder.append("Client ");
        builder.append(getClient().getName());
        builder.append("Payment status ");
        builder.append(getPayment().getState().getDescription());
        builder.append("\nDetalhes: \n");

        for (OrderItem orderItem: orderItems){
            builder.append(orderItems.toString());
        }

        builder.append("Total value ");
        builder.append(nf.format(OrderValueTotal()));
        return builder.toString();
    }

}
