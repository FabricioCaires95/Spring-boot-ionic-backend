package br.com.CourseSpringBoot.service.validation;

import br.com.CourseSpringBoot.dto.ClientNewDTO;
import br.com.CourseSpringBoot.enums.ClientType;
import br.com.CourseSpringBoot.exceptions.FieldMessage;
import br.com.CourseSpringBoot.service.validation.utils.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fabricio
 */
public class ClientInsertValidation implements ConstraintValidator<ClientInsert, ClientNewDTO> {

    @Override
    public void initialize(ClientInsert constraintAnnotation) {

    }

    @Override
    public boolean isValid(ClientNewDTO clientNewDTO, ConstraintValidatorContext constraintValidatorContext) {

        List<FieldMessage> list = new ArrayList<>();

        //Testando qual o tipo do client
        if (clientNewDTO.getClientType().equals(ClientType.PHYISICALPERSON.getCod()) && !BR.isValidCPF(clientNewDTO.getCpfOrCnpj())){
            list.add(new FieldMessage("cpfOrCnpj", "CPF invalid"));
        }

        if (clientNewDTO.getClientType().equals(ClientType.LEGALPERSON.getCod()) && !BR.isValidCNPJ(clientNewDTO.getCpfOrCnpj())){
            list.add(new FieldMessage("cpfOrCnpj", "CNPJ invalid"));
        }

        for (FieldMessage e: list){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                .addConstraintViolation();

        }


        return list.isEmpty();
    }
}
