package com.minibook.beans.util;

public enum Estado {
  AC("Acre"), AL("Alagoas"), AM("Amazonas"), AP("Amap�"), BA("Bahia"), CE("Cear�"), DF(
      "Distrito Federal"), ES("Espirito Santo"), GO("Goias"), MA("Maranh�o"), MG("Minas Gerais"), MS(
      "Mato Grosso Sul"), MT("Mato Grosso"), PA("Par�"), PB("Paraiba"), PE("Pernanbuco"), PI(
      "Piaui"), PR("Parana"), RJ("Rio de Janeiro"), RN("Rio Grande do Norte"), RO("Rond�nia"), RR(
      "Roraima"), RS("Rio Grande do Sul"), SC("Santa Catarina"), SE("Sergipe"), SP("S�o Paulo"), TO(
      "Tocantins");
  private String estado;

  Estado(String estado) {
    this.estado = estado;
  }

  public String getEstado() {
    return estado;
  }

}
