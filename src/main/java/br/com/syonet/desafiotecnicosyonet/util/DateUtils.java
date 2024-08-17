package br.com.syonet.desafiotecnicosyonet.util;


import java.time.LocalDate;

public class DateUtils {

	private DateUtils() {
		throw new UnsupportedOperationException();
	}

	public static boolean areDatesInSameDayAndMonth(LocalDate date1, LocalDate date2) {
		return date1.getDayOfMonth() == date2.getDayOfMonth() && date1.getMonth() == date2.getMonth();
	}
}
