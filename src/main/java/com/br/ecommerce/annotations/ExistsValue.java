package com.br.ecommerce.annotations;

import com.br.ecommerce.validators.ExistsValueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {ExistsValueValidator.class})
public @interface ExistsValue {

    String message() default "{com.br.ecommerce.beanvalidation.existsvalue" + "message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String fieldName();

    Class<?> domainClass();

}