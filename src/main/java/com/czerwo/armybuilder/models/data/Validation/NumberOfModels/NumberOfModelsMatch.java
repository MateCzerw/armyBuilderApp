package com.czerwo.armybuilder.models.data.Validation.NumberOfModels;



import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;



@Constraint(validatedBy = NumberOfModelsValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NumberOfModelsMatch {

    String message() default "Invalid number of models in unit!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}