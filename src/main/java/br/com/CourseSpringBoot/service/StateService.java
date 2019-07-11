package br.com.CourseSpringBoot.service;


import br.com.CourseSpringBoot.domain.State;
import br.com.CourseSpringBoot.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fabricio
 */
@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;


    public List<State> findAll(){
        return stateRepository.findAllByOrderByName();
    }

}
