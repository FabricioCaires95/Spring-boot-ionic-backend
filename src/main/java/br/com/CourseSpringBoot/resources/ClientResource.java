package br.com.CourseSpringBoot.resources;

import br.com.CourseSpringBoot.domain.Client;
import br.com.CourseSpringBoot.dto.ClientDTO;
import br.com.CourseSpringBoot.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author fabricio
 */
@RestController
@RequestMapping("/client")
public class ClientResource {

    @Autowired
    private ClientService service;

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable Integer id){

        Client cli = service.findById(id);

        return ResponseEntity.ok().body(cli);
    }


    public void insert(@RequestBody ClientDTO clientDTO){




    }
}
