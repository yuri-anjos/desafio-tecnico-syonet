package br.com.syonet.desafiotecnicosyonet.validator;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataNascimentoValidatorTest {

	private DataNascimentoValidator validator;
	private ConstraintValidatorContext context;

	@BeforeEach
	public void setUp() {
		validator = new DataNascimentoValidator();
		context = Mockito.mock(ConstraintValidatorContext.class);
	}

	@Test
	void validaDataNascimentoTest() {
		LocalDate passado = LocalDate.of(2000, 1, 1);
		assertTrue(validator.isValid(passado, context));
	}

	@Test
	void validaDataNascimentoFuturoErroTest() {
		LocalDate futuro = LocalDate.now().plusDays(1);
		assertFalse(validator.isValid(futuro, context));
	}

	@Test
	void validaDataNascimentoNullTest() {
		assertTrue(validator.isValid(null, context));
	}
}