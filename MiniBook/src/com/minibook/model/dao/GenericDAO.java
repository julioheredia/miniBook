package com.minibook.model.dao;

import java.util.List;

public interface GenericDAO<T, ID> {

	public List<T> getAll();

	public T getID(ID id);

	public T create(T entidad);

	public T update(T entidad);

	public void delete(T entidad);


}
