package com.minibook.model.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ENDERECO_USUARIO")
public class EnderecoUsuario implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "ENDERECOUSUARIO_ID")
  private Integer enderecoUsuarioId;
  @Column(name = "LOGRADORO")
  private String logradoro;
  @Column(name = "COMPLEMENTO")
  private String complemento;
  @Column(name = "BAIRRO")
  private String bairro;
  @Column(name = "CIDADE")
  private String cidade;
  @Column(name = "UF")
  private String uf;
  @Column(name = "PAIS")
  private String pais;
  @Column(name = "CEP")
  private String cep;
  @OneToOne
  @JoinColumn(name = "USUARIO_ID", referencedColumnName = "USUARIO_ID")
  private Usuario usuario;

  public Integer getEnderecoUsuarioId() {
    return enderecoUsuarioId;
  }

  public void setEnderecoUsuarioId(Integer enderecoUsuarioId) {
    this.enderecoUsuarioId = enderecoUsuarioId;
  }

  public String getLogradoro() {
    return logradoro;
  }

  public void setLogradoro(String logradoro) {
    this.logradoro = logradoro;
  }

  public String getBairro() {
    return bairro;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public String getComplemento() {
    return complemento;
  }

  public void setComplemento(String complemento) {
    this.complemento = complemento;
  }

  public String getCidade() {
    return cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public String getUf() {
    return uf;
  }

  public void setUf(String uf) {
    this.uf = uf;
  }

  public String getPais() {
    return pais;
  }

  public void setPais(String pais) {
    this.pais = pais;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

}
