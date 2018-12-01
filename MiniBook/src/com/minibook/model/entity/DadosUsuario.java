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
@Table(name = "DADOS_USUARIO")
public class DadosUsuario implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Basic(optional = false)
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "DADOSUSUARIO_ID")
  private Integer dadosusuarioId;
  @OneToOne
  @JoinColumn(name = "USUARIO_ID", referencedColumnName = "USUARIO_ID")
  private Usuario usuario;
  @Column(name = "SOBRE")
  private String sobre;
  @Column(name = "TELEFONE")
  private String telefone;
  @Column(name = "CIDADE_NATAL")
  private String cidadeNatal;

  public Integer getDadosusuarioId() {
    return dadosusuarioId;
  }

  public void setDadosusuarioId(Integer dadosusuarioId) {
    this.dadosusuarioId = dadosusuarioId;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public String getSobre() {
    return sobre;
  }

  public void setSobre(String sobre) {
    this.sobre = sobre;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String getCidadeNatal() {
    return cidadeNatal;
  }

  public void setCidadeNatal(String cidadeNatal) {
    this.cidadeNatal = cidadeNatal;
  }

}
