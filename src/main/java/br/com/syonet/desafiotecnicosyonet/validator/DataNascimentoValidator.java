package br.com.syonet.desafiotecnicosyonet.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class DataNascimentoValidator implements ConstraintValidator<ValidDateOfBirth, LocalDate> {

	@Override
	public boolean isValid(LocalDate dataNascimento, ConstraintValidatorContext context) {
		if (dataNascimento == null) {
			return Boolean.TRUE;
		}
		return dataNascimento.isBefore(LocalDate.now());
	}
}