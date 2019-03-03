package br.com.CourseSpringBoot.service;


import br.com.CourseSpringBoot.domain.Client;
import br.com.CourseSpringBoot.dto.ClientDTO;
import br.com.CourseSpringBoot.exceptions.DataIntegrityException;
import br.com.CourseSpringBoot.exceptions.ResourceNotFoundException;
import br.com.CourseSpringBoot.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author fabricio
 */
@Service
public class ClientService {

    @Autowired
    private ClientRepository repo;

    public Client findById(Integer id){
        Optional<Client> ob = repo.findById(id);
        return ob.orElseThrow(() -> new ResourceNotFoundException(("Object not found" + Client.class.getName())));
    }

    public List<Client> findAll(){
        return repo.findAll();
    }



    public Client update(Client cli){
        Client newCli = findById(cli.getId());
        updateData(newCli,cli);

        return repo.save(newCli);
    }

    public void delete(Integer id){
        findById(id);
        try {
            repo.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("This Client cannot be deleted, Referential integrity constraint violation");
        }

    }

    public Page<Client> findPage(Integer page, Integer linesPerPage, String orderby, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderby);

        return repo.findAll(pageRequest);
    }

    public Client toClient(ClientDTO cliDTO){
        return new Client(cliDTO.getId(), cliDTO.getName(),cliDTO.getEmail(), null, null);
    }

    private void updateData(Client newCli, Client cli){
        newCli.setName(cli.getName());
        newCli.setEmail(cli.getEmail());
    }
}
