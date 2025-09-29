package br.com.retinoblastoma.api.service;

import br.com.retinoblastoma.model.entities.User;

public interface JwtService {

	String getJWTToken(User usuario);

}
