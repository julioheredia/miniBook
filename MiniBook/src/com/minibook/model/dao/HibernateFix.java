package com.minibook.model.dao;

import java.math.BigDecimal;
import java.util.List;

public class HibernateFix {

	public static Long count(List list) {

		Object resultado = list.iterator().next();

		return count(resultado);
	}

	public static Long count(Object resultado) {
		Long cant = 0l;

		if (resultado instanceof Integer) {
			cant = Long.valueOf(((Integer) resultado).longValue());
		} else if (resultado instanceof BigDecimal) {
			cant = ((BigDecimal) resultado).longValue();
		} else {
			cant = (Long) resultado;
		}
		return cant;
	}
}
