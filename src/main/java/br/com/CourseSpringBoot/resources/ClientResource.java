package br.com.CourseSpringBoot.resources;


import br.com.CourseSpringBoot.domain.Client;
import br.com.CourseSpringBoot.dto.ClientDTO;
import br.com.CourseSpringBoot.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fabricio
 */
@RestController
@RequestMapping("/clients")
public class ClientResource {

    @Autowired
    private ClientService service;

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable Integer id){

        Client cli = service.findById(id);

        return ResponseEntity.ok().body(cli);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO clientDTO, @PathVariable Integer id){
        Client cli = service.toClient(clientDTO);
        cli.setId(id);
        cli = service.update(cli);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){

        service.delete(id);

        return ResponseEntity.noContent().build();

    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll(){

        List<ClientDTO> listDTO = service.findAll().stream()
                .map(obj -> new ClientDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok(listDTO);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ClientDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24")Integer linesPerPage,
            @RequestParam(value = "orderby", defaultValue = "name")String orderby,
            @RequestParam(value = "direction", defaultValue = "ASC")String direction){

        Page<Client> list = service.findPage(page, linesPerPage, orderby, direction);
        Page<ClientDTO> pageDto = list.map(obj -> new ClientDTO(obj));

        return ResponseEntity.ok().body(pageDto);
    }

}
