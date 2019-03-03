package br.com.CourseSpringBoot.domain;

import br.com.CourseSpringBoot.enums.ClientType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * @author fabricio
 */
@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String cpfOrCnpj;
    private Integer clientType;


    @OneToMany(mappedBy = "client")
    private List<Address> addresses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "PHONE")
    private Set<String> phones = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();

    public Client(){

    }

    public Client(Integer id, String name, String email, String cpfOrCnpj, ClientType clientType) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpfOrCnpj = cpfOrCnpj;
        this.clientType = (clientType==null) ? null : clientType.getCod();
    }

    public ClientType getClientType(){
        return ClientType.toEnum(clientType);
    }


}
