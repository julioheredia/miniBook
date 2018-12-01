package test;

import org.junit.Test;

import com.minibook.model.dao.UsuarioDAO;
import com.minibook.model.dao.impl.UsuarioHibernateDAO;
import com.minibook.model.entity.Usuario;

public class LoginUsuarioJobTest {

  @Test
  public void test() {

    UsuarioDAO usuarioDAO = new UsuarioHibernateDAO();
    Usuario usu = usuarioDAO.login("jul.heredia@yahoo.com.br", "pEOybVIVDUCCzgGOLHqvCA==");

    System.out.println(usu);

  }

}
