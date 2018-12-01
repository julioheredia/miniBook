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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "FOTOS_USUARIO")
public class FotosUsuario implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = " FOTOSUSUARIO_ID")
  private Integer fotosUsuarioId;
  @Lob
  @Column(name = "FOTO")
  private byte[] foto;
  @Column(name = "DATA_CREATION")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataCreation;
  @ManyToOne
  @JoinColumn(name = "USUARIO_ID", referencedColumnName = "USUARIO_ID")
  private Usuario usuario;


  public Integer getFotosUsuarioId() {
    return fotosUsuarioId;
  }

  public void setFotosUsuarioId(Integer fotosUsuarioId) {
    this.fotosUsuarioId = fotosUsuarioId;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public byte[] getFoto() {
    return foto;
  }

  public void setFoto(byte[] foto) {
    this.foto = foto;
  }

  public Date getDataCreation() {
    return dataCreation;
  }

  public void setDataCreation(Date dataCreation) {
    this.dataCreation = dataCreation;
  }

}
