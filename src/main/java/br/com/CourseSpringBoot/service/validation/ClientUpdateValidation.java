package br.com.CourseSpringBoot.service.validation;

import br.com.CourseSpringBoot.domain.Client;
import br.com.CourseSpringBoot.dto.ClientDTO;
import br.com.CourseSpringBoot.dto.ClientNewDTO;
import br.com.CourseSpringBoot.enums.ClientType;
import br.com.CourseSpringBoot.exceptions.FieldMessage;
import br.com.CourseSpringBoot.repositories.ClientRepository;
import br.com.CourseSpringBoot.service.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author fabricio
 */
public class ClientUpdateValidation implements ConstraintValidator<ClientUpdate, ClientDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void initialize(ClientUpdate constraintAnnotation) {

    }

    @Override
    public boolean isValid(ClientDTO clientNewDTO, ConstraintValidatorContext constraintValidatorContext) {

        List<FieldMessage> list = new ArrayList<>();

        //Get the id by URI
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        Integer id = Integer.parseInt(map.get("id"));

        //chek if a email is duplicate
        Client aux = clientRepository.findByEmail(clientNewDTO.getEmail());

        if (aux != null && !aux.getId().equals(id)){
            list.add(new FieldMessage("email", "duplicate email, this email already exists"));
        }

        for (FieldMessage e: list){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                .addConstraintViolation();

        }


        return list.isEmpty();
    }
}
