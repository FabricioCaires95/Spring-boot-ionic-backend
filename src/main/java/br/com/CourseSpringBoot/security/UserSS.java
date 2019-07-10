package br.com.CourseSpringBoot.security;

import br.com.CourseSpringBoot.enums.UserProfile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author fabricio
 */
public class UserSS implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorites;

    public UserSS(){

    }

    public UserSS(Integer id, String email, String password, Set<UserProfile> profiles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorites = profiles.stream().map(x -> new SimpleGrantedAuthority(x.getDescription())).collect(Collectors.toList());
    }

    public Integer getId(){
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorites;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean hasRole(UserProfile profile){

        return getAuthorities().contains(new SimpleGrantedAuthority(profile.getDescription()));

    }
}
