package com.minibook.model.entity;

public enum Sexo {

	MASCULINO("M"), FEMININO("F");

	private String value;

	private Sexo(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
