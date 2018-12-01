package com.minibook.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "RELACAO_USUARIO")
public class RelacaoUsuario implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "RELACAOUSUARIO_ID")
  private Integer relacaoUsuarioId;
  @Column(name = "USUARIO_ID")
  private Integer usuarioId;
  @Column(name = "USUARIO_RELACAO_ID")
  private Integer usuarioRelacaoId;
  @Column(name = "DATA_CREATION")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataCreation;

  public Integer getRelacaoUsuarioId() {
    return relacaoUsuarioId;
  }

  public void setRelacaoUsuarioId(Integer relacaoUsuarioId) {
    this.relacaoUsuarioId = relacaoUsuarioId;
  }

  public Integer getUsuarioId() {
    return usuarioId;
  }

  public void setUsuarioId(Integer usuarioId) {
    this.usuarioId = usuarioId;
  }

  public Integer getUsuarioRelacaoId() {
    return usuarioRelacaoId;
  }

  public void setUsuarioRelacaoId(Integer usuarioRelacaoId) {
    this.usuarioRelacaoId = usuarioRelacaoId;
  }

  public Date getDataCreation() {
    return dataCreation;
  }

  public void setDataCreation(Date dataCreation) {
    this.dataCreation = dataCreation;
  }



}
