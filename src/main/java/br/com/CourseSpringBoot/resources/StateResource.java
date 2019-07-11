package br.com.CourseSpringBoot.resources;

import br.com.CourseSpringBoot.domain.City;
import br.com.CourseSpringBoot.domain.State;
import br.com.CourseSpringBoot.dto.CityDTO;
import br.com.CourseSpringBoot.dto.StateDTO;
import br.com.CourseSpringBoot.service.CityService;
import br.com.CourseSpringBoot.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fabricio
 */
@RestController
@RequestMapping(value = "/states")
public class StateResource {

    @Autowired
    private StateService stateService;

    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<List<StateDTO>> findAll(){

        List<State> states = stateService.findAll();
        List<StateDTO> stateDTOS = states.stream().map(obj -> new StateDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(stateDTOS);
    }

    @GetMapping("/{stateId}/cities")
    public ResponseEntity<List<CityDTO>> findCities(@PathVariable Integer stateId){

        List<City> cities = cityService.findByState(stateId);
        List<CityDTO> cityDTOS = cities.stream().map(obj -> new CityDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(cityDTOS);


    }


}
