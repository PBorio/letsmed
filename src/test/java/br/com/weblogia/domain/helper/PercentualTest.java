package br.com.weblogia.domain.helper;

import org.junit.Assert;
import org.junit.Test;


public class PercentualTest {

	@Test
	public void dezPorCentoDe100EhDez(){
		double value = 1000.0;
		double percent = 10.0;
		Double result = value * (percent/100);
		Assert.assertEquals((Double)100.0, result);
	}
	
	@Test
	public void dezEhCincoPorCentoDeDuzentos(){
		double value = 23400.0;
		double result = 300.0;
		Double percent = (result/value)*100;
		Assert.assertEquals((Double)5.0, percent);
	}

}
