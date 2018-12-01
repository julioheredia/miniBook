package com.minibook.model.dao;

import com.minibook.model.entity.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario, Integer> {

  public Usuario login(String email, String password);

  public Boolean validateEmail(String email);
  
  public boolean insertUsuariosRelacionados(Usuario usuario) ;

}
