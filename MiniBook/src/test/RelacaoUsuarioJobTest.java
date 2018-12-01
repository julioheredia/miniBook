package test;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.minibook.model.entity.Usuario;
import com.minibook.model.service.UsuarioService;
import com.minibook.model.service.impl.UsuarioServiceImpl;

public class RelacaoUsuarioJobTest {

  @Test
  public void test() {
    UsuarioService usuarioService = new UsuarioServiceImpl();
    try {
      Usuario usu = usuarioService.getID(5);
      System.out.println(usu);

      boolean result = usuarioService.insertUsuariosRelacionados(usu);
      System.out.println(result);

    } catch (Exception e) {
      e.printStackTrace();
      fail("DEU RUIM");
    }
  }

}
