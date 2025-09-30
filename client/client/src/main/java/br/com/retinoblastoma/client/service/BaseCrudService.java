package br.com.retinoblastoma.client.service;

import java.util.List;

public interface BaseCrudService<T> {

	List<T> readAll();

	T readById(Long id);

}
