package br.com.syonet.desafiotecnicosyonet.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DataNascimentoValidator.class)
public @interface ValidDateOfBirth {
	String message() default "Data de nascimento inválida";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
