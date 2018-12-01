package com.minibook.beans.util;

import java.io.Serializable;
import java.util.Date;

public class PostImageRender implements Serializable {

  private static final long serialVersionUID = 1L;

  private String user;
  private String post;
  private String image;
  private Date dataCreation;

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getPost() {
    return post;
  }

  public void setPost(String post) {
    this.post = post;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Date getDataCreation() {
    return dataCreation;
  }

  public void setDataCreation(Date dataCreation) {
    this.dataCreation = dataCreation;
  }

}
