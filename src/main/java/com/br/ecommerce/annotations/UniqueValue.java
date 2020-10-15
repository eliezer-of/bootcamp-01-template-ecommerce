package com.br.ecommerce.annotations;

import com.br.ecommerce.validators.UniqueValueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {UniqueValueValidator.class})
@Target({ FIELD})
@Retention(RUNTIME)
public @interface UniqueValue {
    String message() default "{com.br.ecommerce.beanvalidation.uniquevalue" + "message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String fieldName();

    Class<?> domainClass();

}
