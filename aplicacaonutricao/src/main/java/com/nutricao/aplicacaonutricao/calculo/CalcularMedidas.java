package com.nutricao.aplicacaonutricao.calculo;

import java.math.BigDecimal;

public class CalcularMedidas {

	
	public static BigDecimal imc(Integer altura,BigDecimal peso) {
		return BigDecimal.valueOf(10000*peso.doubleValue()/(altura*altura));
	}
}
