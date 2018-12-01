package com.minibook.beans.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.minibook.model.entity.FotosUsuario;
import com.minibook.model.entity.PostUsuario;
import com.minibook.model.entity.Usuario;

public class ConversorPostImageUtil {

  private Usuario usuarioLogged;

  public ConversorPostImageUtil(Usuario usuarioLogged) {
    this.usuarioLogged = usuarioLogged;
  }

  public List<PostImageRender> getListPostImage() {
    try {
      List<PostImageRender> renderList = new ArrayList<PostImageRender>();

      List<FotosUsuario> fotos = usuarioLogged.getFotosUsuarioList();
      for (FotosUsuario foto : fotos) {
        File tempFile =
            FileUtil.createImageFile(foto.getFoto(), foto.getFotosUsuarioId().toString());
        PostImageRender imageRender = new PostImageRender();

        imageRender
            .setUser(usuarioLogged.getNome() + FileUtil.SPACE + usuarioLogged.getSobrenome());
        imageRender.setDataCreation(foto.getDataCreation());
        imageRender.setImage(FileUtil.PATH + tempFile.getName());
        renderList.add(imageRender);
      }

      List<PostUsuario> posts = usuarioLogged.getPostUsuarioList();
      for (PostUsuario post : posts) {
        PostImageRender imageRender = new PostImageRender();
        imageRender
            .setUser(usuarioLogged.getNome() + FileUtil.SPACE + usuarioLogged.getSobrenome());
        imageRender.setDataCreation(post.getDataCreation());
        imageRender.setPost(post.getText());
        renderList.add(imageRender);
      }

      List<Usuario> usuarios = usuarioLogged.getUsuarioList();
      for (Usuario userOfUser : usuarios) {

        fotos = userOfUser.getFotosUsuarioList();
        for (FotosUsuario foto : fotos) {
          File tempFile =
              FileUtil.createImageFile(foto.getFoto(), foto.getFotosUsuarioId().toString());
          PostImageRender imageRender = new PostImageRender();
          imageRender.setUser(userOfUser.getNome() + FileUtil.SPACE + userOfUser.getSobrenome());
          imageRender.setDataCreation(foto.getDataCreation());
          imageRender.setImage(FileUtil.PATH + tempFile.getName());
          renderList.add(imageRender);
        }

        posts = userOfUser.getPostUsuarioList();
        for (PostUsuario post : posts) {
          PostImageRender imageRender = new PostImageRender();
          imageRender.setUser(userOfUser.getNome() + FileUtil.SPACE + userOfUser.getSobrenome());
          imageRender.setDataCreation(post.getDataCreation());
          imageRender.setPost(post.getText());
          renderList.add(imageRender);
        }
      }

      Collections.sort(renderList, new Comparator<PostImageRender>() {
        public int compare(PostImageRender o1, PostImageRender o2) {
          if (o1.getDataCreation() == null || o2.getDataCreation() == null)
            return 0;
          return o2.getDataCreation().compareTo(o1.getDataCreation());
        }
      });

      return renderList;

    } catch (Exception e) {
      e.printStackTrace();
    }

    return Collections.emptyList();
  }


  public List<PostImageRender> getListImageUsuario() {
    try {
      List<PostImageRender> renderList = new ArrayList<PostImageRender>();

      List<FotosUsuario> fotos = usuarioLogged.getFotosUsuarioList();
      for (FotosUsuario foto : fotos) {
        File tempFile =
            FileUtil.createImageFile(foto.getFoto(), foto.getFotosUsuarioId().toString());
        PostImageRender imageRender = new PostImageRender();

        imageRender
            .setUser(usuarioLogged.getNome() + FileUtil.SPACE + usuarioLogged.getSobrenome());
        imageRender.setDataCreation(foto.getDataCreation());
        imageRender.setImage(FileUtil.PATH + tempFile.getName());
        renderList.add(imageRender);
      }

      Collections.sort(renderList, new Comparator<PostImageRender>() {
        public int compare(PostImageRender o1, PostImageRender o2) {
          if (o1.getDataCreation() == null || o2.getDataCreation() == null)
            return 0;
          return o2.getDataCreation().compareTo(o1.getDataCreation());
        }
      });

      return renderList;

    } catch (Exception e) {
      e.printStackTrace();
    }

    return Collections.emptyList();
  }
}
