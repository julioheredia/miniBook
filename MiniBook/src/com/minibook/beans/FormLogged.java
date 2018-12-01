package com.minibook.beans;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.minibook.beans.util.ConversorPostImageUtil;
import com.minibook.beans.util.DataUtil;
import com.minibook.beans.util.FileUtil;
import com.minibook.beans.util.LocaleUtil;
import com.minibook.beans.util.PostImageRender;
import com.minibook.model.entity.Sexo;
import com.minibook.model.entity.Usuario;
import com.minibook.model.service.UsuarioService;
import com.minibook.model.service.impl.UsuarioServiceImpl;

@ManagedBean
@SessionScoped
public class FormLogged implements Serializable {

  private static final long serialVersionUID = 1L;
  private static Logger log = Logger.getLogger(FormLogged.class);

  private Usuario usuarioLogged;
  private String fotoPerfil;

  private String dia;
  private String mes;
  private String ano;
  private String sexo;

  private String post;

  private List<PostImageRender> listPostImage;
  private List<PostImageRender> listImageUsuario;

  private UsuarioService usuarioService;

  private List<UploadedFile> fotos;

  private String contentPage;

  public FormLogged() {

    contentPage = "principal.xhtml";

    usuarioService = new UsuarioServiceImpl();
    usuarioLogged =
        (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
            .get("usuarioLogged");

    ConversorPostImageUtil conversorPostImageUtil = new ConversorPostImageUtil(usuarioLogged);
    listPostImage = conversorPostImageUtil.getListPostImage();
    listImageUsuario = conversorPostImageUtil.getListImageUsuario();

    File foto =
        FileUtil
            .createImageFile(usuarioLogged.getFoto(), "logoUser" + usuarioLogged.getUsuarioId());
    fotoPerfil = "/temp/" + foto.getName();

  }

  public void refreshList() {
    usuarioLogged = usuarioService.getID(usuarioLogged.getUsuarioId());

    ConversorPostImageUtil conversorPostImageUtil = new ConversorPostImageUtil(usuarioLogged);
    listPostImage = conversorPostImageUtil.getListPostImage();
  }

  public void handleFileUpload(FileUploadEvent event) {

    if (fotos == null) {
      fotos = new ArrayList<UploadedFile>();
    }
    fotos.add(event.getFile());
  }

  public void saveFotos() {

    List<byte[]> bs = new ArrayList<byte[]>();
    for (UploadedFile foto : fotos) {
      bs.add(foto.getContents());
    }

    usuarioLogged = usuarioService.adcionaFotos(bs, usuarioLogged);

    ConversorPostImageUtil conversorPostImageUtil = new ConversorPostImageUtil(usuarioLogged);
    listImageUsuario = conversorPostImageUtil.getListImageUsuario();

  }

  public void sendPost() throws IOException {

    usuarioLogged = usuarioService.sendPost(post, usuarioLogged);
    post = new String();

    ConversorPostImageUtil conversorPostImageUtil = new ConversorPostImageUtil(usuarioLogged);
    listPostImage = conversorPostImageUtil.getListPostImage();

  }

  public void editUsuario() throws IOException {

    try {
      // if (fotoPerfil != null)
      // usuarioLogged.setFoto(fotoPerfil.getContents());
      // else
      // usuarioLogged.setFoto(new byte[1000]);
      GregorianCalendar dn = new GregorianCalendar();
      dn.set(Integer.valueOf(ano), Integer.valueOf(mes) - 1, Integer.valueOf(dia));
      usuarioLogged.setDataNascimento(dn.getTime());
      usuarioLogged.setSexo(Sexo.valueOf(sexo));

      usuarioLogged = usuarioService.update(usuarioLogged);

      // contentPage = "inicial.xhtml";
      contentPage = "principal.xhtml";
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public void goToFotosUsuario() throws IOException {
    contentPage = "fotosUsuario.xhtml";
  }

  public void goToPrincipal() throws IOException {
    contentPage = "principal.xhtml";
  }

  public void goToEditUsuario() throws IOException {
    try {
      GregorianCalendar dn = new GregorianCalendar();
      dn.setTime(usuarioLogged.getDataNascimento());

      Integer year = dn.get(Calendar.YEAR);
      ano = year.toString();
      Integer month = dn.get(Calendar.MONTH);
      mes = month.toString();
      Integer day = dn.get(Calendar.DAY_OF_MONTH);
      dia = day.toString();
    } catch (Exception e) {
      log.error("", e);
    }

    try {
      sexo = usuarioLogged.getSexo().toString();
    } catch (Exception e) {
      log.error("", e);
    }

    contentPage = "editUsuario.xhtml";
  }

  public void logOut() throws IOException {
    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    FacesContext.getCurrentInstance().getExternalContext().responseReset();

    FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
        .put("usuarioLogged", null);
    FacesContext.getCurrentInstance().getExternalContext().redirect("/MiniBook/index.xhtml");
  }

  public Usuario getUsuarioLogged() {
    return usuarioLogged;
  }

  public void setUsuarioLogged(Usuario usuarioLogged) {
    this.usuarioLogged = usuarioLogged;
  }

  public String getFotoPerfil() {
    return fotoPerfil;
  }

  public void setFotoPerfil(String fotoPerfil) {
    this.fotoPerfil = fotoPerfil;
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

  public String getPost() {
    return post;
  }

  public void setPost(String post) {
    this.post = post;
  }

  public List<PostImageRender> getListImageUsuario() {
    return listImageUsuario;
  }

  public void setListImageUsuario(List<PostImageRender> listImageUsuario) {
    this.listImageUsuario = listImageUsuario;
  }

  public List<PostImageRender> getListPostImage() {
    return listPostImage;
  }

  public void setListPostImage(List<PostImageRender> listPostImage) {
    this.listPostImage = listPostImage;
  }

  public List<UploadedFile> getFotos() {
    return fotos;
  }

  public void setFotos(List<UploadedFile> fotos) {
    this.fotos = fotos;
  }

  public String getContentPage() {
    return contentPage;
  }

  public void setContentPage(String contentPage) {
    this.contentPage = contentPage;
  }

}
