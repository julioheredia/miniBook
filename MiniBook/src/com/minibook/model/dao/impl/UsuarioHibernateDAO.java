package com.minibook.model.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.minibook.model.dao.UsuarioDAO;
import com.minibook.model.entity.RelacaoUsuario;
import com.minibook.model.entity.Usuario;

public class UsuarioHibernateDAO extends GenericHibernateDAO<Usuario, Integer> implements
    UsuarioDAO, Serializable {

  private static final long serialVersionUID = 1L;
  private static Logger log = Logger.getLogger(UsuarioHibernateDAO.class);

  @Override
  public Usuario login(String email, String password) {
    try {
      getEntityManager().clear();

      StringBuffer hql = new StringBuffer();
      hql.append(" SELECT u FROM  Usuario u ");
      hql.append("      WHERE u.email = :email  ");
      hql.append("  and u.password = :password   ");

      Query query = getEntityManager().createQuery(hql.toString());
      query.setParameter("email", email);
      query.setParameter("password", password);

      return (Usuario) query.getSingleResult();
    } catch (Exception e) {
    } finally {
      getEntityManager().close();
    }
    return null;

  }

  @Override
  public Boolean validateEmail(String email) {
    try {
      StringBuffer hql = new StringBuffer();
      hql.append(" SELECT u FROM  Usuario u ");
      hql.append("      WHERE  u.email = :email  ");

      Query query = getEntityManager().createQuery(hql.toString());
      query.setParameter("email", email);

      List<Usuario> list = query.getResultList();

      if (list.isEmpty())
        return true;
      else
        return false;

    } catch (Exception e) {
      log.error("", e);
    } finally {
      getEntityManager().close();
    }
    return false;

  }

  @Override
  public boolean insertUsuariosRelacionados(Usuario usuario) {

    EntityManager em = getEntityManager();

    try {

      List<Usuario> usuarioList = getAll();

      em.getTransaction().begin();

      for (Usuario u : usuarioList) {

        if (!usuario.equals(u)) {

          RelacaoUsuario relacaoUsuario = new RelacaoUsuario();
          relacaoUsuario.setUsuarioId(usuario.getUsuarioId());
          relacaoUsuario.setUsuarioRelacaoId(u.getUsuarioId());
          relacaoUsuario.setDataCreation(new Date());

          em.persist(relacaoUsuario);

          RelacaoUsuario usuarioRelacao = new RelacaoUsuario();
          usuarioRelacao.setUsuarioId(u.getUsuarioId());
          usuarioRelacao.setUsuarioRelacaoId(usuario.getUsuarioId());
          usuarioRelacao.setDataCreation(new Date());

          em.persist(usuarioRelacao);
        }

      }

      em.getTransaction().commit();
      return true;
    } catch (Exception e) {
      em.getTransaction().rollback();
      e.printStackTrace();
    } finally {
      em.close();
    }
    return false;
  }

}
