package com.minibook.model.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaConectorUtil {

  private static JpaConectorUtil instancia;
  private EntityManagerFactory factory;

  private JpaConectorUtil() {
    factory = Persistence.createEntityManagerFactory("MiniBook");
  }

  public static JpaConectorUtil getInstancia() {

    if (instancia == null) {
      instancia = new JpaConectorUtil();
    }
    return instancia;
  }

  public EntityManager getEntidadeManager() {
    return factory.createEntityManager();
  }
}
