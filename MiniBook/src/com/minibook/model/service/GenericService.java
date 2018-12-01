package com.minibook.model.service;

import java.util.List;

public interface GenericService<T, ID> {

	public List<T> getAll();

	public T getID(ID id);

	public T create(T entidad);

	public T update(T entidad);

	public void delete(T entidad);
}
