package br.com.weblogia.letsmed.domain.helpers;

import java.util.Arrays;


public class StringUtils {
	
	public static String fillCharacts(String texto , Integer tamanho,  char caract){

		if(texto.length()>tamanho)
			texto = texto.substring(0,tamanho);
		
		char[] brancos = new char[(tamanho - texto.length())];
		Arrays.fill(brancos, caract);

		return new String(brancos)+texto;
	}

}
