package br.com.CourseSpringBoot.enums;

import lombok.Getter;

/**
 * @author fabricio
 */
@Getter
public enum UserProfile {

    ADMIN(1, "ROLE_ADMIN"),
    CLIENT(2, "ROLE_CLIENT");

    private int cod;
    private String description;

    private UserProfile(int cod, String description){
        this.cod = cod;
        this.description = description;

    }

    public static UserProfile toEnum(Integer cod){

        if (cod == null){
            return null;
        }

        for (UserProfile x : UserProfile.values()){
            if (cod.equals(x.getCod())){
                return x;
            }
        }

        throw new IllegalArgumentException("Id invalid " + cod);

    }




}
