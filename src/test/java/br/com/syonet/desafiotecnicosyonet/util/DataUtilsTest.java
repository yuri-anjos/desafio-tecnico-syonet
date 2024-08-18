package br.com.syonet.desafiotecnicosyonet.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataUtilsTest {

	@Test
	void datasNoMesmoDiaEMesTest() {
		LocalDate date1 = LocalDate.of(2010, 8, 8);
		LocalDate date2 = LocalDate.of(2024, 8, 8);
		assertTrue(DataUtils.datasNoMesmoDiaEMes(date1, date2));
	}

	@Test
	void datasNoMesmoMesTest() {
		LocalDate date1 = LocalDate.of(2010, 10, 8);
		LocalDate date2 = LocalDate.of(2024, 8, 8);
		assertFalse(DataUtils.datasNoMesmoDiaEMes(date1, date2));
	}

	@Test
	void datasNoMesmoDiaTest() {
		LocalDate date1 = LocalDate.of(2010, 8, 10);
		LocalDate date2 = LocalDate.of(2024, 8, 8);
		assertFalse(DataUtils.datasNoMesmoDiaEMes(date1, date2));
	}

	@Test
	void datasEmDiaEMesDiferentes() {
		LocalDate date1 = LocalDate.of(2010, 10, 10);
		LocalDate date2 = LocalDate.of(2024, 8, 8);
		assertFalse(DataUtils.datasNoMesmoDiaEMes(date1, date2));
	}
}
