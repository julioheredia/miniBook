package com.minibook.model.service;

import java.util.List;

import com.minibook.model.entity.Usuario;

public interface UsuarioService extends GenericService<Usuario, Integer> {

  public Usuario login(String email, String password);

  public Boolean validateEmail(String email);

  public Usuario sendPost(String post, Usuario entidad);

  public Usuario adcionaFotos(List<byte[]> fotos, Usuario entidad);

  public boolean insertUsuariosRelacionados(Usuario usuario);

}
