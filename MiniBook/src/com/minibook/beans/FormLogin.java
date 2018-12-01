package com.minibook.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.model.UploadedFile;

import com.minibook.beans.util.DataUtil;
import com.minibook.beans.util.FileUtil;
import com.minibook.beans.util.LocaleUtil;
import com.minibook.model.entity.DadosUsuario;
import com.minibook.model.entity.EnderecoUsuario;
import com.minibook.model.entity.Sexo;
import com.minibook.model.entity.Usuario;
import com.minibook.model.service.UsuarioService;
import com.minibook.model.service.impl.UsuarioServiceImpl;

@ManagedBean
// @ViewScoped
@SessionScoped
public class FormLogin implements Serializable {

  private static final long serialVersionUID = 1L;
  private static Logger log = Logger.getLogger(FormLogin.class);

  private String email;
  private String password;

  private String loginMessage;

  private String dia;
  private String mes;
  private String ano;
  private String sexo;
  private UploadedFile fotoPerfil;

  private UsuarioService usuarioService;

  private Usuario usuario;
  private DadosUsuario dadosUsuario;
  private EnderecoUsuario enderecoUsuario;

  private static String messageValidate;
  private static boolean accountUsuarioErro;
  public static final String INPUT_ACCOUNT_VALIDATE = "Você deve preencher todos os campos.";


  public FormLogin() {
    try {
      usuario = new Usuario();
      accountUsuarioErro = false;

      usuarioService = new UsuarioServiceImpl();
    } catch (Exception e) {
      log.error("", e);
    }
  }

  public void login() throws IOException {

    usuario = usuarioService.login(email, password);

    if (usuario != null) {
      setLoginMessage(null);
      FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
          .put("usuarioLogged", usuario);
      FacesContext.getCurrentInstance().getExternalContext().redirect("pagesLogger/inicial.xhtml");

    } else {
      // FacesContext.getCurrentInstance().addMessage("codigoMessage",
      // new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou Senha incorreto!", ""));

      // loginMessage = "Login ou Senha incorreto!";
    }

  }

  public void dadosUsuario() throws IOException {

    if (usuario.getNome() == null || usuario.getNome().equals("")) {
      FormLogin.setMessageValidateForm(INPUT_ACCOUNT_VALIDATE);
      return;
    }

    if (usuario.getSobrenome() == null || usuario.getSobrenome().equals("")) {
      FormLogin.setMessageValidateForm(INPUT_ACCOUNT_VALIDATE);
      return;
    }

    GregorianCalendar dn = new GregorianCalendar();
    try {
      dn.set(Integer.valueOf(ano), Integer.valueOf(mes) - 1, Integer.valueOf(dia));
      usuario.setDataNascimento(dn.getTime());
    } catch (NumberFormatException e) {
      FormLogin.setMessageValidateForm(INPUT_ACCOUNT_VALIDATE);
      return;
    }

    try {
      usuario.setSexo(Sexo.valueOf(sexo));
    } catch (Exception e) {
      FormLogin.setMessageValidateForm(INPUT_ACCOUNT_VALIDATE);
      return;
    }

    if (usuario != null) {

      dadosUsuario = new DadosUsuario();
      enderecoUsuario = new EnderecoUsuario();

      FacesContext.getCurrentInstance().getExternalContext().redirect("dadosUsuario.xhtml");
    }

  }

  public void salvarUsuario() throws IOException {

    dadosUsuario.setUsuario(usuario);
    enderecoUsuario.setUsuario(usuario);

    usuario.setDadosUsuario(dadosUsuario);
    usuario.setEnderecoUsuario(enderecoUsuario);

    if (fotoPerfil != null)
      usuario.setFoto(fotoPerfil.getContents());
    else
      usuario.setFoto(new byte[1000]);

    usuario = usuarioService.create(usuario);

    FileUtil.createImageFile(usuario.getFoto(), "logoUser" + usuario.getUsuarioId() + ".jpg");

    if (usuario.getUsuarioId() != null) {
      if (usuarioService.insertUsuariosRelacionados(usuario)) {
        usuario = new Usuario();
        dadosUsuario = new DadosUsuario();
        enderecoUsuario = new EnderecoUsuario();
        FacesContext.getCurrentInstance().getExternalContext().redirect("confirmUsuario.xhtml");
      }
    }
  }

  public List<String> getDias() {
    return DataUtil.getDiasMes();
  }

  public List<String> getMeses() {
    return DataUtil.getMesesAno();
  }

  public List<String> getAnos() {
    return DataUtil.getPeridoAnos();
  }

  public List<String> getPaises() {
    return LocaleUtil.getPaises();
  }

  public List<String> getEstados() {
    return LocaleUtil.getEstados();
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public String getDia() {
    return dia;
  }

  public void setDia(String dia) {
    this.dia = dia;
  }

  public String getMes() {
    return mes;
  }

  public void setMes(String mes) {
    this.mes = mes;
  }

  public String getAno() {
    return ano;
  }

  public void setAno(String ano) {
    this.ano = ano;
  }

  public String getSexo() {
    return sexo;
  }

  public void setSexo(String sexo) {
    this.sexo = sexo;
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

  public boolean isAccountUsuarioErro() {
    return accountUsuarioErro;
  }

  public String getMessageValidate() {
    return messageValidate;
  }

  public static void setMessageValidateForm(final String message) {
    accountUsuarioErro = true;
    messageValidate = message;
  }

  public static void desabiliteMessageValidateForm() {
    accountUsuarioErro = false;
  }

  public UploadedFile getFotoPerfil() {
    return fotoPerfil;
  }

  public void setFotoPerfil(UploadedFile fotoPerfil) {
    this.fotoPerfil = fotoPerfil;
  }

  public String getLoginMessage() {
    return loginMessage;
  }

  public void setLoginMessage(String loginMessage) {
    this.loginMessage = loginMessage;
  }
}
