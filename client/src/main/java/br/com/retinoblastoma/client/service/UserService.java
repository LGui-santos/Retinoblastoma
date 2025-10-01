package br.com.retinoblastoma.client.service;

import org.springframework.web.multipart.MultipartFile;

import br.com.retinoblastoma.model.dto.UserDto;

public interface UserService extends BaseCrudService<UserDto> {

	boolean updateAvatar(Long userId, MultipartFile file);

	UserDto validateUsernameAndPassword(String username, String password);

}
