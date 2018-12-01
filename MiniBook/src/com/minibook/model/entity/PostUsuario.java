package com.minibook.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "POST_USUARIO")
public class PostUsuario implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "POSTUSUARIO_ID")
  private Integer postUsuarioId;
  @Column(name = "DATA_CREATION")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataCreation;
  @Column(name = "TEXT")
  private String text;
  @ManyToOne
  @JoinColumn(name = "USUARIO_ID", referencedColumnName = "USUARIO_ID")
  private Usuario usuario;

  public Integer getPostUsuarioId() {
    return postUsuarioId;
  }

  public void setPostUsuarioId(Integer postUsuarioId) {
    this.postUsuarioId = postUsuarioId;
  }

  public Date getDataCreation() {
    return dataCreation;
  }

  public void setDataCreation(Date dataCreation) {
    this.dataCreation = dataCreation;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

}
