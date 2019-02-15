package br.com.CourseSpringBoot.enums;

import lombok.Getter;

/**
 * @author fabricio
 */
@Getter
public enum ClientType {


    PHYISICALPERSON(1, "Pyisical Person "),
    LEGALPERSON(2, "Legal Person");

    private Integer cod;
    private String description;

    private ClientType(Integer cod, String description){
        this.cod = cod;
        this.description = description;
    }

    public static ClientType toEnum(Integer cod){

        if (cod == null){

            return null;
        }

        for (ClientType x : ClientType.values()){

            if (cod.equals(x.getCod())){
                return x;

            }
        }

        throw new IllegalArgumentException("The cod is invalid");

    }

}
