package br.com.retinoblastoma.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.retinoblastoma.api.mapper.UserMapper;
import br.com.retinoblastoma.api.repository.UserRepository;
import br.com.retinoblastoma.model.dto.UserDto;
import br.com.retinoblastoma.model.entities.User;

@Service
public class UserService {

	@Autowired
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserDto saveUser(UserDto userDto) {

		if (userDto.getRole() == null || userDto.getRole().isEmpty()) {
			userDto.setRole("Usuário");
		}
		User user = UserMapper.toUser(userDto);
		User userSaved = userRepository.save(user);
		return UserMapper.toUserDto(userSaved);
	}

}
