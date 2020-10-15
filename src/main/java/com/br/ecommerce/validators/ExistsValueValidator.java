package com.br.ecommerce.validators;

import com.br.ecommerce.annotations.ExistsValue;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsValueValidator implements ConstraintValidator<ExistsValue, Object> {

    private String atribute;
    private Class<?> theClass;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void initialize(ExistsValue value) {
        atribute = value.fieldName();
        theClass = value.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        Query query = entityManager.createQuery("select 1 from " + theClass.getName() + " where " + atribute + " =:value");
        query.setParameter("value", value);

        List<?> list = query.getResultList();
        Assert.isTrue(list.size() <= 1, "já existe um elemento " + theClass + " com atributo o " + atribute + "=" + value);

        return !list.isEmpty();
    }
}
