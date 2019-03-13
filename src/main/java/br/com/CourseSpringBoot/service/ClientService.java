package br.com.CourseSpringBoot.service;


import br.com.CourseSpringBoot.domain.Address;
import br.com.CourseSpringBoot.domain.City;
import br.com.CourseSpringBoot.domain.Client;
import br.com.CourseSpringBoot.dto.ClientDTO;
import br.com.CourseSpringBoot.dto.ClientNewDTO;
import br.com.CourseSpringBoot.enums.ClientType;
import br.com.CourseSpringBoot.exceptions.DataIntegrityException;
import br.com.CourseSpringBoot.exceptions.ResourceNotFoundException;
import br.com.CourseSpringBoot.repositories.AddressRepository;
import br.com.CourseSpringBoot.repositories.CityRepository;
import br.com.CourseSpringBoot.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author fabricio
 */
@Service
public class ClientService {

    @Autowired
    private ClientRepository repo;

    @Autowired
    private AddressRepository addressRepository;

    public Client findById(Integer id){
        Optional<Client> ob = repo.findById(id);
        return ob.orElseThrow(() -> new ResourceNotFoundException(("Object not found  " + Client.class.getName())));
    }

    public List<Client> findAll(){
        return repo.findAll();
    }

    @Transactional
    public Client insert(Client client){
        client.setId(null);
        client = repo.save(client);
        addressRepository.saveAll(client.getAddresses());
        return client;
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

    public Client toClient(ClientNewDTO cliDTO) {
        Client cli = new Client(null, cliDTO.getName(), cliDTO.getEmail(), cliDTO.getCpfOrCnpj(), ClientType.toEnum(cliDTO.getClientType()));
        City city = new City(cliDTO.getCityId(), null, null);
        Address ad = new Address(null, cliDTO.getStreet(), cliDTO.getHouseNumber(), cliDTO.getZipCode(), cli, city);
        cli.getAddresses().add(ad);
        cli.getPhones().add(cliDTO.getPhone1());

        if (cliDTO.getPhone2() != null){
            cli.getPhones().add(cliDTO.getPhone2());
        }

        if (cliDTO.getPhone3() != null){
            cli.getPhones().add(cliDTO.getPhone3());
        }
        return cli;
    }

}
