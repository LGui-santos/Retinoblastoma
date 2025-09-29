package br.com.retinoblastoma.api.service;

public interface BaseService<T> {

	public Long create(T entity);

	T readById(Long id);

}
