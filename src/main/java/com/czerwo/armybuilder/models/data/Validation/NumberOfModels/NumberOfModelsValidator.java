package com.czerwo.armybuilder.models.data.Validation.NumberOfModels;


import com.czerwo.armybuilder.models.data.OrderedUnit;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumberOfModelsValidator implements ConstraintValidator<NumberOfModelsMatch, OrderedUnit> {

    private OrderedUnit orderedUnit;


    @Override
    public void initialize(NumberOfModelsMatch constraintAnnotation) {

    }

    @Override
    public boolean isValid(OrderedUnit orderedUnit, ConstraintValidatorContext constraintValidatorContext) {
        int maxNumberOfModelsInUnit = orderedUnit.getUnit().getMaxSizeOfUnit();
        int minNumberOfModelsInUnit = orderedUnit.getUnit().getMinSizeOfUnit();

        if(orderedUnit.getNumberOfModels() >= minNumberOfModelsInUnit && orderedUnit.getNumberOfModels() <= maxNumberOfModelsInUnit) return true;

        return false;
    }
}
