package br.com.CourseSpringBoot.service;

import br.com.CourseSpringBoot.domain.City;
import br.com.CourseSpringBoot.domain.State;
import br.com.CourseSpringBoot.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fabricio
 */
@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;


    public List<City> findByState(Integer stateId){
        return cityRepository.findCities(stateId);
    }

}
