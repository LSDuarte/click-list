package br.com.clicklist.core.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe utilitária para converter valores em extenço.
 * 
 * <p>
 * <b>Criado em:</b> 01/06/2010
 * 
 * @author Dominio público.
 */
public final class ExtensoUtil {

	/** Campo nro. */
	private List<Integer> nro;

	/** Campo num. */
	private BigInteger num;

	/** Campo isCurrency. */
	private boolean isCurrency;
	
	/** Campo qualificadores. */
	private String qualificadores[][] = {  
		{"centavo", "centavos"},  
		{"", ""},  
		{"mil", "mil"},  
		{"milhão", "milhões"},  
		{"bilhão", "bilhões"},  
		{"trilhão", "trilhões"},  
		{"quatrilhão", "quatrilhões"},  
		{"quintilhão", "quintilhões"},  
		{"sextilhão", "sextilhões"},  
		{"septilhão", "septilhões"}
		};  

	/** Campo numeros. */
	private String numeros[][] = {  
		{"zero", "um", "dois", "três", "quatro", "cinco", "seis", "sete", "oito", "nove", "dez",  
		"onze", "doze", "treze", "quatorze", "quinze", "desesseis", "desessete", "dezoito", "desenove"},  
		{"vinte", "trinta", "quarenta", "cinquenta", "sessenta", "setenta", "oitenta", "noventa"},  
		{"cem", "cento", "duzentos", "trezentos", "quatrocentos", "quinhentos", "seiscentos",  
		"setecentos", "oitocentos", "novecentos"}
		}; 
	
	/**
	 * Construtor de ExtensoUtil.
	 */
	public ExtensoUtil() {
		nro = new ArrayList<>();
		setCurrency(true);
	}

	/**
	 * Construtor de ExtensoUtil.
	 * 
	 * @param dec
	 *            Valor decimal a ser convertido.
	 */
	public ExtensoUtil(BigDecimal dec) {
		this();
		setNumber(dec);
	}

	/**
	 * Construtor de ExtensoUtil.
	 * 
	 * @param dec
	 *            Valor decimal a ser convertido.
	 */
	public ExtensoUtil(double dec) {
		this();
		setNumber(dec);
	}

	/**
	 * Atribui um novo valor ao campo number.
	 * 
	 * @param dec
	 *            Valor decimal a ser convertido.
	 */
	public void setNumber(BigDecimal dec) {
		num = dec.setScale(2, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100)).toBigInteger();
		nro.clear();
		if (num.equals(BigInteger.ZERO)) {
			nro.add(new Integer(0));
			nro.add(new Integer(0));
		} else {
			addRemainder(100);
			while (!num.equals(BigInteger.ZERO)) {
				addRemainder(1000);
			}
		}
	}

	/**
	 * Atribui um novo valor ao campo number.
	 * 
	 * @param dec
	 *            Valor decimal a ser convertido.
	 */
	public void setNumber(double dec) {
		setNumber(new BigDecimal(dec));
	}

	/**
	 * Atribui um novo valor ao campo currency.
	 * 
	 * @param isCurrency
	 *            Valor booleano que define se o valor a ser convertido é
	 *            inteiro ou moeda.
	 */
	public void setCurrency(boolean isCurrency) {
		this.isCurrency = isCurrency;
	}

	/**
	 * Imprime no console os valores converidos.
	 */
	public void show() {
		Iterator<Integer> valores = nro.iterator();
		while (valores.hasNext()) {
			System.out.println(((Integer) valores.next()).intValue());
		}
		System.out.println(toString());
	}

	/**
	 * Retorna o valor por extenso.
	 */
	public String toString() {
		StringBuffer buf = new StringBuffer();

		int ct;

		for (ct = nro.size() - 1; ct > 0; ct--) {
			if (buf.length() > 0 && !ehGrupoZero(ct)) {
				buf.append(" e ");
			}
			buf.append(numToString(((Integer) nro.get(ct)).intValue(), ct));
		}
		if (buf.length() > 0) {
			if (ehUnicoGrupo()) {
				buf.append(" de ");
			}
			while (buf.toString().endsWith(" ")) {
				buf.setLength(buf.length() - 1);
			}
			if (ehPrimeiroGrupoUm()) {
				buf.insert(0, "h");
			}
			if (isCurrency) {
				if (nro.size() == 2 && ((Integer) nro.get(1)).intValue() == 1) {
					buf.append(" real");
				} else {
					buf.append(" reais");
				}
				if (((Integer) nro.get(0)).intValue() != 0) {
					buf.append(" e ");
				}
			}
		}
		if (isCurrency) {
			if (((Integer) nro.get(0)).intValue() != 0) {
				buf.append(numToString(((Integer) nro.get(0)).intValue(), 0));
			}
		}
		return buf.toString();
	}

	/**
	 * Eh primeiro grupo um.
	 * 
	 * @return boolean
	 */
	private boolean ehPrimeiroGrupoUm() {
		if (((Integer) nro.get(nro.size() - 1)).intValue() == 1)
			return true;
		return false;
	}

	/**
	 * Adiciona remainder.
	 * 
	 * @param divisor
	 *            the divisor
	 */
	private void addRemainder(int divisor) {
		BigInteger[] newNum = num.divideAndRemainder(BigInteger.valueOf(divisor));
		nro.add(new Integer(newNum[1].intValue()));
		num = newNum[0];
	}

	// private boolean temMaisGrupos(int ps) {
	// for (; ps > 0; ps--) {
	// if (((Integer) nro.get(ps)).intValue() != 0) {
	// return true;
	// }
	// }
	// return false;
	// }

	// private boolean ehUltimoGrupo(int ps) {
	// return (ps > 0) && ((Integer) nro.get(ps)).intValue() != 0 &&
	// !temMaisGrupos(ps - 1);
	// }

	/**
	 * Eh unico grupo.
	 * 
	 * @return boolean
	 */
	private boolean ehUnicoGrupo() {
		if (nro.size() <= 3)
			return false;
		if (!ehGrupoZero(1) && !ehGrupoZero(2))
			return false;
		boolean hasOne = false;
		for (int i = 3; i < nro.size(); i++) {
			if (((Integer) nro.get(i)).intValue() != 0) {
				if (hasOne)
					return false;
				hasOne = true;
			}
		}
		return true;
	}

	/**
	 * Eh grupo zero.
	 * 
	 * @param ps
	 *            the ps
	 * @return boolean
	 */
	private boolean ehGrupoZero(int ps) {
		if (ps <= 0 || ps >= nro.size())
			return true;
		return ((Integer) nro.get(ps)).intValue() == 0;
	}

	/**
	 * Converte numero para texto.
	 * 
	 * @param numero
	 *            the numero
	 * @param escala
	 *            the escala
	 * @return String
	 */
	private String numToString(int numero, int escala) {
		int unidade = (numero % 10);
		int dezena = (numero % 100); 
		int centena = (numero / 100);
		StringBuffer buf = new StringBuffer();

		if (numero != 0) {
			if (centena != 0) {
				if (dezena == 0 && centena == 1) {
					buf.append(numeros[2][0]);
				} else {
					buf.append(numeros[2][centena]);
				}
			}

			if ((buf.length() > 0) && (dezena != 0)) {
				buf.append(" e ");
			}
			if (dezena > 19) {
				dezena /= 10;
				buf.append(numeros[1][dezena - 2]);
				if (unidade != 0) {
					buf.append(" e ");
					buf.append(numeros[0][unidade]);
				}
			} else if (centena == 0 || dezena != 0) {
				buf.append(numeros[0][dezena]);
			}

			buf.append(" ");
			if (numero == 1) {
				buf.append(qualificadores[escala][0]);
			} else {
				buf.append(qualificadores[escala][1]);
			}
		}

		return buf.toString();
	}
}