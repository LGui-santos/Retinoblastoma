package br.com.retinoblastoma.api.service;

import br.com.retinoblastoma.model.dto.UserDto;
import br.com.retinoblastoma.model.entities.User;

public interface UserService {

	User validateUsernameAndPassword(String username, String password);

	UserDto validateLogin(String encodedData);

	Boolean deleteById(Long id);
}
