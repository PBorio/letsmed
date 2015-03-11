package br.com.weblogia.letsmed.domain.helpers;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Arredondamento {
	
	public double arredondar(double valor) {
		return new BigDecimal(String.valueOf(valor)).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}
	
	public double arredondar(double valor,int scale) {
		return new BigDecimal(String.valueOf(valor)).setScale(scale, RoundingMode.HALF_UP).doubleValue();
	}

}
