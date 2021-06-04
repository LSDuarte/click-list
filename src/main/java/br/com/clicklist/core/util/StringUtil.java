package br.com.clicklist.core.util;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

/**
 * Esta é uma classe utilitária, que contém metodos para trabalhar valor do tipo
 * {@link String}.
 * 
 * 
 * @author LSDuarte
 */
public final class StringUtil {

	/**
	 * Verifica se um determinado objeto {@link String} está nulo ou vazio. Caso
	 * esteja, ele retorna <code>true</code>.
	 * 
	 * @param value
	 *            Valor a ser verificado.
	 * @return boolean
	 */
	public static boolean isEmpty(String value) {
		return value == null || value.trim().isEmpty();
	}

	/**
	 * Método utilizado para formação de valores.
	 * 
	 * <p>
	 * <b>Por exemplo:</b> <code><pre>
	 * String str = StringUtil.format("12345000", {@link com.core.util.constant.MaskType#MASK_CEP});
	 * System.out.println(str);
	 * 
	 * <b>Resultado:</b>
	 * 
	 * 12.345-000
	 * </pre></code>
	 * 
	 * @param value
	 *            Valor a ser formatado.
	 * @param mask
	 *            Máscara utilizada para a formatação. O caracter cutinga que
	 *            será utilizado para montar a máscara é ( <b>#</b> ). A
	 *            interface {@link com.core.util.constant.MaskType} contém algumas constantes com
	 *            máscaras genéricas para ser utilizada.
	 * @return String
	 */
	public static String format(String value, String mask) {
		MaskFormatter formatter;
		try {
			if (!isEmpty(value)  && !isEmpty(mask)) {
				formatter = new MaskFormatter(mask);
				formatter.setValueContainsLiteralCharacters(false);
				return formatter.valueToString(value.replaceAll("[\\s\\-_.:()/]", ""));
			}
			return value;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Método utilizado para converter um valor por extenso.
	 * 
	 * <p>
	 * <b>Por exemplo:</b> <code><pre>
	 * String str1 = StringUtil.toExtenso(153.27, true);
	 * String str2 = StringUtil.toExtenso(153.27, false);
	 * System.out.println(str1);
	 * System.out.println(str2);
	 * 
	 * <b>Resultado:</b>
	 * 
	 * cento e cinquenta e três reais e vinte e sete centavos
	 * cento e cinquenta e três
	 * </pre></code>
	 * 
	 * @param value
	 *            Valor a ser convertido.
	 * @param isCurrency
	 *            Especifica se o valor é tipo moeda.
	 * @return String
	 */
	public static String toExtenso(Double value, boolean isCurrency) {
		ExtensoUtil extensoUtil = new ExtensoUtil(value);
		extensoUtil.setCurrency(isCurrency);
		return extensoUtil.toString();
	}

	/**
	 * Método utilizado para converter um valor por extenso.
	 * 
	 * <p>
	 * <b>Por exemplo:</b> <code><pre>
	 * String str = StringUtil.toExtenso(153.27);
	 * System.out.println(str);
	 * 
	 * <b>Resultado:</b>
	 * 
	 * cento e cinquenta e três reais e vinte e sete centavos
	 * </pre></code>
	 * 
	 * @param value
	 *            Valor a ser convertido.
	 * @return String
	 */
	public static String toExtenso(Double value) {
		return toExtenso(value, true);
	}

}