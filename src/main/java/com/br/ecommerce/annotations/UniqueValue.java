package com.br.ecommerce.annotations;

import com.br.ecommerce.validators.UniqueValueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {UniqueValueValidator.class})
public @interface UniqueValue {
    String message() default "{com.br.ecommerce.annotations.uniquevalue" + "message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String fieldName();

    Class<?> domainClass();

}
