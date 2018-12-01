package com.minibook.model.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import com.minibook.model.dao.GenericDAO;
import com.minibook.model.util.JpaConectorUtil;

public class GenericHibernateDAO<T, ID extends Serializable> implements GenericDAO<T, ID> {

  private Class<T> persistentClass;
  public EntityManager entityManager;

  @SuppressWarnings("unchecked")
  public GenericHibernateDAO() {
    this.persistentClass =
        (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
  }

  public Class<T> getPersistentClass() {
    return this.persistentClass;
  }

  public EntityManager getEntityManager() {
    return JpaConectorUtil.getInstancia().getEntidadeManager();
  }

  @Override
  public void delete(T entity) {
    try {
      entityManager = getEntityManager();
      entityManager.getTransaction().begin();
      entityManager.remove(entity);
      entityManager.getTransaction().commit();
    } catch (Exception ex) {
      entityManager.getTransaction().rollback();
      ex.printStackTrace();
    } finally {
      entityManager.close();
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public T getID(ID id) {
    try {
      entityManager = getEntityManager();
      T t = (T) entityManager.find(persistentClass.newInstance().getClass(), id);
      entityManager.refresh(t);
      return t;
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      entityManager.close();
    }
    return null;
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  @Override
  public List<T> getAll() {
    try {
      entityManager = getEntityManager();
      CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
      cq.select(cq.from(persistentClass.newInstance().getClass()));
      Query q = entityManager.createQuery(cq);
      return q.getResultList();
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      entityManager.close();
    }
    return null;
  }

  @Override
  public T create(T entity) {
    try {
      entityManager = getEntityManager();
      entityManager.getTransaction().begin();
      entityManager.persist(entity);
      entityManager.getTransaction().commit();
      return entity;
    } catch (Exception ex) {
      entityManager.getTransaction().rollback();
      ex.printStackTrace();
    } finally {
      entityManager.close();
    }
    return null;
  }


  public T update(T entity) {
    try {
      entityManager = getEntityManager();
      entityManager.getTransaction().begin();
      entityManager.merge(entity);
      entityManager.getTransaction().commit();
      return entity;
    } catch (Exception ex) {

      entityManager.getTransaction().rollback();
      ex.printStackTrace();
    } finally {
      entityManager.close();
    }
    return null;
  }

}
