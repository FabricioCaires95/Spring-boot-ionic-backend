package br.com.CourseSpringBoot.service;

import br.com.CourseSpringBoot.domain.Client;
import br.com.CourseSpringBoot.exceptions.ResourceNotFoundException;
import br.com.CourseSpringBoot.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author fabricio
 */
@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client findById(Integer id){
        Optional<Client> ob = clientRepository.findById(id);
        return ob.orElseThrow(() -> new ResourceNotFoundException(("Object not found" + Client.class.getName())));
    }

    public Client insert(Client cli){

        return clientRepository.save(cli);
    }
}
