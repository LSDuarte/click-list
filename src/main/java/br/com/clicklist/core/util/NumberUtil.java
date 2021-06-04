package br.com.clicklist.core.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

/**
 * Esta é uma classe utilitária, para manipulação de objetos numéricos.
 * 
 * <p>
 * <b>Criado em:</b> 30/12/2010
 *
 * @author Ricardo Antonio de Souza
 */
public final class NumberUtil {

	/**
	 * Verifica se um determinado objeto {@link Integer} está nulo ou igual a zero.
	 * Caso esteja, ele retorna <code>true</code>.
	 * 
	 * @param value Valor a ser verificado.
	 * @return boolean
	 */
	public static boolean isEmpty(Integer value) {
		return value == null || value.intValue() == 0;
	}

	/**
	 * Verifica se um determinado objeto {@link Long} está nulo ou igual a zero.
	 * Caso esteja, ele retorna <code>true</code>.
	 * 
	 * @param value Valor a ser verificado.
	 * @return boolean
	 */
	public static boolean isEmpty(Long value) {
		return value == null || value.longValue() == 0;
	}

	/**
	 * Verifica se um determinado objeto {@link Double} está nulo ou igual a zero.
	 * Caso esteja, ele retorna <code>true</code>.
	 * 
	 * @param value Valor a ser verificado.
	 * @return boolean
	 */
	public static boolean isEmpty(Double value) {
		return value == null || value.doubleValue() == 0;
	}

	/**
	 * Verifica se o valor de um determinado string é um inteiro.
	 * 
	 * @param str Valor a ser verificado.
	 * 
	 * @return boolean
	 */
	public static boolean isInteger(String str) {
		return StringUtil.isEmpty(str) ? false : str.matches("\\d+$");
	}

	/**
	 * Verifica se o valor de um determinado string é um numero decimal.
	 * 
	 * @param str Valor a ser verificado.
	 * 
	 * @return boolean
	 */
	public static boolean isDecimal(String str) {
		return StringUtil.isEmpty(str) ? false : str.matches("-?\\d+(.\\d+)?");
	}

	/**
	 * Arredonda um determinado valor.
	 * 
	 * @param value Valor a ser arredondado.
	 * @param scale Quantidade de digitos decimais a serem arredondados.
	 * 
	 * @return double
	 */
	public static double round(double value, int scale) {
		return new BigDecimal(value).setScale(scale, RoundingMode.HALF_UP).doubleValue();
	}

	/**
	 * Arredonda um determinado valor para 2 casas decimais.
	 * 
	 * @param value Valor a ser arredondado.
	 * 
	 * @return double
	 */
	public static double round(double value) {
		return round(value, 2);
	}

	/**
	 * Converte um determinado valor para String.
	 * 
	 * @param value Valor a ser convertido.
	 * 
	 * @return String
	 */
	public static String toString(Double value) {
		return String.format(new Locale("pt", "BR"), "%,.2f", isEmpty(value) ? 0 : value);
	}

	@Deprecated
	@SuppressWarnings("unchecked")
	public static <T> T coalesce(T... values) {
		for (T value : values) {
			if (value != null) {
				return value;
			}
		}
		return null;
	}

}
