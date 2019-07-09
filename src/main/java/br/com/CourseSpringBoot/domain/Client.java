package br.com.CourseSpringBoot.domain;

import br.com.CourseSpringBoot.enums.ClientType;
import br.com.CourseSpringBoot.enums.UserProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

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

    @Column(unique = true)
    private String email;
    private String cpfOrCnpj;
    private Integer clientType;

    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "client")
    private List<Address> addresses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "PHONE")
    private Set<String> phones = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PROFILES")
    private Set<Integer> profiles = new HashSet<>();


    public Client(){
        addProfile(UserProfile.CLIENT);
    }

    public Client(Integer id, String name, String email, String cpfOrCnpj, ClientType clientType, String password) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpfOrCnpj = cpfOrCnpj;
        this.clientType = (clientType==null) ? null : clientType.getCod();
        this.password = password;
        addProfile(UserProfile.CLIENT);
    }

    public ClientType getClientType(){
        return ClientType.toEnum(clientType);
    }

    public Set<UserProfile> getProfile(){

        return profiles.stream().map(x -> UserProfile.toEnum(x)).collect(Collectors.toSet());
    }

    public void addProfile(UserProfile profile){
        profiles.add(profile.getCod());
    }


}
