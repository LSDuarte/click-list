package br.com.clicklist.core.util;

import java.time.format.DateTimeFormatter;

/**
 * Interface com constantes de data e hora.
 *
 *
 * @author LSDuarte
 *
 */
public interface DateTimeType {

	public static final String DATE_FORMAT = "dd/MM/yyyy";
	public static final String SDATE_FORMAT = "dd/MM/yy";
	public static final String DATE_SQL_FORMAT = "yyyy-MM-dd";
	public static final String TIME_FORMAT = "HH:mm:ss";
	public static final String STIME_FORMAT = "HH:mm";
	public static final String HOLIDAY_FORMAT = "dd/MM";
	public static final String DATETIME_FORMAT = DATE_FORMAT + " " + TIME_FORMAT;
	public static final String DATESTIME_FORMAT = DATE_FORMAT + " " + STIME_FORMAT;
	public static final String DATETIME_SQL_FORMAT = DATE_SQL_FORMAT + " " + TIME_FORMAT;

	public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
	public static final DateTimeFormatter SDATE_FORMATTER = DateTimeFormatter.ofPattern(SDATE_FORMAT);
	public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);
	public static final DateTimeFormatter STIME_FORMATTER = DateTimeFormatter.ofPattern(STIME_FORMAT);
	public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_FORMAT);
	public static final DateTimeFormatter DATESTIME_FORMATTER = DateTimeFormatter.ofPattern(DATESTIME_FORMAT);

	/**
	 * Quantidade de mile-segundos por um segundo.
	 */
	public static final int MSEG_PER_SECOND = 1000;

	/**
	 * Quantidade de mile-segundos por um minuto.
	 */
	public static final int MSEG_PER_MINUTE = MSEG_PER_SECOND * 60;

	/**
	 * Quantidade de mile-segundos por uma hora.
	 */
	public static final int MSEG_PER_HOUR = MSEG_PER_MINUTE * 60;

	/**
	 * Quantidade de mile-segundos por um dia.
	 */
	public static final int MSEG_PER_DAY = MSEG_PER_HOUR * 24;
}
