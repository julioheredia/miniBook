package test;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.minibook.model.entity.Usuario;
import com.minibook.model.service.UsuarioService;
import com.minibook.model.service.impl.UsuarioServiceImpl;

public class AddPostJobTest {

  @Test
  public void test() {

    UsuarioService usuarioService = new UsuarioServiceImpl();
    try {

      String post = "Test POST";

      Usuario entidad = usuarioService.getID(1);

      Usuario usu = usuarioService.sendPost(post, entidad);
      System.out.println(usu);

    } catch (Exception e) {
      e.printStackTrace();
      fail("DEU RUIM");
    }

  }

}
