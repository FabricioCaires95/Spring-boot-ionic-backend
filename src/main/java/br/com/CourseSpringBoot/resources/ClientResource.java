package br.com.CourseSpringBoot.resources;

import br.com.CourseSpringBoot.domain.Client;
import br.com.CourseSpringBoot.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fabricio
 */
@RestController
@RequestMapping("/client")
public class ClientResource {

    @Autowired
    private ClientService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id){

        Client cat = service.findById(id);


        return ResponseEntity.ok().body(cat);
    }
}
