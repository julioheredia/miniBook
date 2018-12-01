package test;

import org.junit.Test;

import com.minibook.model.dao.UsuarioDAO;
import com.minibook.model.dao.impl.UsuarioHibernateDAO;
import com.minibook.model.entity.Usuario;

public class GetUsuarioByIdJobTest {

  @Test
  public void test() {
    UsuarioDAO usuarioDAO = new UsuarioHibernateDAO();
    Usuario usu = usuarioDAO.getID(2);

    System.out.println(usu);
  }

}
