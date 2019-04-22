package br.com.CourseSpringBoot.resources;

import br.com.CourseSpringBoot.domain.PayPal;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * @author fabricio
 */
@Service
public class BoletService {

    public void preencherPayment(PayPal payPal, Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, 7);



    }
}
