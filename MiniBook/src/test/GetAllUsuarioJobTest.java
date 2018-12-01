package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.minibook.model.dao.UsuarioDAO;
import com.minibook.model.dao.impl.UsuarioHibernateDAO;
import com.minibook.model.entity.Usuario;

public class GetAllUsuarioJobTest {

  @Test
  public void test() {

    UsuarioDAO usuarioDAO = new UsuarioHibernateDAO();
    List<Usuario> usu = null;
    try {
      usu = usuarioDAO.getAll();
    } catch (Exception e) {
      e.printStackTrace();
      fail("DEU RUIM");
    }

    fail("DEU RUIM");
    
    for (Usuario usuario : usu) {
      System.out.println(usuario);

    }

  }

}
