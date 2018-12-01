package com.minibook.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "USUARIO_ID")
  private Integer usuarioId;
  @Column(name = "NOME")
  private String nome;
  @Column(name = "SOBRENOME")
  private String sobrenome;
  @Column(name = "DATA_NASCIMENTO")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataNascimento;
  @Column(name = "EMAIL")
  private String email;
  @Column(name = "PASSWORD")
  private String password;
  @Enumerated(EnumType.STRING)
  @Column(name = "SEXO")
  private Sexo sexo;
  @Lob
  @Column(name = "FOTO")
  private byte[] foto;

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.ALL)
  private DadosUsuario dadosUsuario;
  @OneToOne(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.ALL)
  private EnderecoUsuario enderecoUsuario;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.ALL)
  private List<FotosUsuario> fotosUsuarioList;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.ALL)
  private List<PostUsuario> postUsuarioList;

  @OneToMany
  @JoinTable(name = "RELACAO_USUARIO", joinColumns = {@JoinColumn(name = "USUARIO_ID")},
      inverseJoinColumns = {@JoinColumn(name = "USUARIO_RELACAO_ID")})
  private List<Usuario> usuarioList;

  public Usuario() {
    super();
  }

  public Usuario(Integer usuarioId) {
    super();
    this.usuarioId = usuarioId;
  }

  public Usuario(Integer usuarioId, String nome, String sobrenome, Date dataNascimento,
      String email, String password, Sexo sexo) {
    super();
    this.usuarioId = usuarioId;
    this.nome = nome;
    this.sobrenome = sobrenome;
    this.dataNascimento = dataNascimento;
    this.email = email;
    this.password = password;
    this.sexo = sexo;
  }

  public Integer getUsuarioId() {
    return usuarioId;
  }

  public void setUsuarioId(Integer usuarioId) {
    this.usuarioId = usuarioId;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getSobrenome() {
    return sobrenome;
  }

  public void setSobrenome(String sobrenome) {
    this.sobrenome = sobrenome;
  }

  public Date getDataNascimento() {
    return dataNascimento;
  }

  public void setDataNascimento(Date dataNascimento) {
    this.dataNascimento = dataNascimento;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Sexo getSexo() {
    return sexo;
  }

  public void setSexo(Sexo sexo) {
    this.sexo = sexo;
  }

  public byte[] getFoto() {
    return foto;
  }

  public void setFoto(byte[] foto) {
    this.foto = foto;
  }

  public DadosUsuario getDadosUsuario() {
    return dadosUsuario;
  }

  public void setDadosUsuario(DadosUsuario dadosUsuario) {
    this.dadosUsuario = dadosUsuario;
  }

  public EnderecoUsuario getEnderecoUsuario() {
    return enderecoUsuario;
  }

  public void setEnderecoUsuario(EnderecoUsuario enderecoUsuario) {
    this.enderecoUsuario = enderecoUsuario;
  }

  public List<FotosUsuario> getFotosUsuarioList() {
    return fotosUsuarioList;
  }

  public void setFotosUsuarioList(List<FotosUsuario> fotosUsuarioList) {
    this.fotosUsuarioList = fotosUsuarioList;
  }

  public List<PostUsuario> getPostUsuarioList() {
    return postUsuarioList;
  }

  public void setPostUsuarioList(List<PostUsuario> postUsuarioList) {
    this.postUsuarioList = postUsuarioList;
  }

  public List<Usuario> getUsuarioList() {
    return usuarioList;
  }

  public void setUsuarioList(List<Usuario> usuarioList) {
    this.usuarioList = usuarioList;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((usuarioId == null) ? 0 : usuarioId.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Usuario other = (Usuario) obj;
    if (usuarioId == null) {
      if (other.usuarioId != null)
        return false;
    } else if (!usuarioId.equals(other.usuarioId))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Usuario [usuarioId=" + usuarioId + ", nome=" + nome + ", sobrenome=" + sobrenome
        + ", dataNascimento=" + dataNascimento + ", email=" + email + ", password=" + password
        + ", sexo=" + sexo + "]";
  }

}
