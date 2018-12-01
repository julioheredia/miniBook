package com.minibook.model.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.minibook.model.dao.UsuarioDAO;
import com.minibook.model.dao.impl.UsuarioHibernateDAO;
import com.minibook.model.entity.FotosUsuario;
import com.minibook.model.entity.PostUsuario;
import com.minibook.model.entity.Usuario;
import com.minibook.model.service.UsuarioService;
import com.minibook.model.util.CriptoException;
import com.minibook.model.util.CriptoUtil;

public class UsuarioServiceImpl implements UsuarioService, Serializable {

  private static final long serialVersionUID = 1L;
  private static Logger log = Logger.getLogger(UsuarioServiceImpl.class);

  private UsuarioDAO usuarioDAO;

  public UsuarioServiceImpl() {
    usuarioDAO = new UsuarioHibernateDAO();
  }

  @Override
  public Usuario adcionaFotos(List<byte[]> fotos, Usuario entidad) {

    for (byte[] bs : fotos) {
      FotosUsuario fotoUsuario = new FotosUsuario();
      fotoUsuario.setDataCreation(Calendar.getInstance().getTime());
      fotoUsuario.setFoto(bs);
      fotoUsuario.setUsuario(entidad);

      if (entidad.getPostUsuarioList().isEmpty()) {
        List<FotosUsuario> list = new ArrayList<FotosUsuario>();
        list.add(fotoUsuario);
        entidad.setFotosUsuarioList(list);
      } else {
        entidad.getFotosUsuarioList().add(fotoUsuario);
      }

    }

    try {
      entidad = usuarioDAO.update(entidad);
      entidad = usuarioDAO.getID(entidad.getUsuarioId());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return entidad;
  }

  @Override
  public Usuario sendPost(String post, Usuario entidad) {

    PostUsuario postUsuario = new PostUsuario();
    postUsuario.setDataCreation(Calendar.getInstance().getTime());
    postUsuario.setText(post);
    postUsuario.setUsuario(entidad);

    if (entidad.getPostUsuarioList().isEmpty()) {
      List<PostUsuario> list = new ArrayList<PostUsuario>();
      list.add(postUsuario);
      entidad.setPostUsuarioList(list);
    } else {
      entidad.getPostUsuarioList().add(postUsuario);
    }

    try {
      entidad = usuarioDAO.update(entidad);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return entidad;
  }

  @Override
  public List<Usuario> getAll() {
    return usuarioDAO.getAll();
  }

  @Override
  public Usuario getID(Integer id) {
    return usuarioDAO.getID(id);
  }

  @Override
  public Usuario create(Usuario entidad) {

    String criptoPassword = null;
    try {
      criptoPassword = CriptoUtil.encriptar(CriptoUtil.CHAVE, entidad.getPassword());
    } catch (CriptoException e) {
      log.error("", e);
    }
    entidad.setPassword(criptoPassword);

    return usuarioDAO.create(entidad);
  }

  @Override
  public Usuario update(Usuario entidad) {

    String criptoPassword = null;
    try {
      criptoPassword = CriptoUtil.encriptar(CriptoUtil.CHAVE, entidad.getPassword());
    } catch (CriptoException e) {
      log.error("", e);
    }
    entidad.setPassword(criptoPassword);

    return usuarioDAO.update(entidad);
  }

  @Override
  public void delete(Usuario entidad) {
    usuarioDAO.delete(entidad);
  }

  @Override
  public Usuario login(String email, String password) {
    String criptoPassword = null;
    try {
      criptoPassword = CriptoUtil.encriptar(CriptoUtil.CHAVE, password);
    } catch (CriptoException e) {
      log.error("", e);
    }
    Usuario usuario = usuarioDAO.login(email, criptoPassword);
    if (usuario != null)
      return getID(usuario.getUsuarioId());
    else
      return null;
  }

  @Override
  public Boolean validateEmail(String email) {
    return usuarioDAO.validateEmail(email);
  }

  @Override
  public boolean insertUsuariosRelacionados(Usuario usuario) {
    return usuarioDAO.insertUsuariosRelacionados(usuario);
  }

}
