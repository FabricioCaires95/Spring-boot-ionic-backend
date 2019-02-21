package br.com.CourseSpringBoot.enums;

import lombok.Getter;

/**
 * @author fabricio
 */
@Getter
public enum StatePayment {

    PENDENT(1, "pendent"),
    PAID(2, "paid out"),
    CANCELED(3, "canceled");

    private int cod;
    private String description;

    private StatePayment(int cod, String description){
        this.cod = cod;
        this.description = description;
    }

    public static StatePayment toEnum(Integer cod){

        if (cod == null){
            return null;
        }

        for (StatePayment x : StatePayment.values()){
            if (cod.equals(x.getCod())){
                return x;
            }
        }

        throw new IllegalArgumentException("Id invalid");

    }

}
