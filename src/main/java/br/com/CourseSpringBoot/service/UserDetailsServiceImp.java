package br.com.CourseSpringBoot.service;

import br.com.CourseSpringBoot.domain.Client;
import br.com.CourseSpringBoot.repositories.ClientRepository;
import br.com.CourseSpringBoot.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author fabricio
 */
@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client cli = clientRepository.findByEmail(email);
        if (cli == null){
            throw new UsernameNotFoundException(email);
        }


        return new UserSS(cli.getId(), cli.getEmail(), cli.getPassword(), cli.getProfile());
    }
}
