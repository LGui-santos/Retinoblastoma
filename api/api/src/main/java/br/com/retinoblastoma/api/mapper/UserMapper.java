package br.com.retinoblastoma.api.mapper;

import br.com.retinoblastoma.model.dto.UserDto;
import br.com.retinoblastoma.model.entities.User;

public class UserMapper {

	private UserMapper() {
	}

	public static User toUser(UserDto userDto) {
		return User.builder()
				.id(userDto.getId())
				.name(userDto.getName())
				.email(userDto.getEmail())
				.username(userDto.getUsername())
				.password(userDto.getPassword())
				.role(userDto.getRole())
				.build();
	}

	public static UserDto toUserDto(User user) {
		return UserDto.builder()
				.id(user.getId())
				.name(user.getName())
				.email(user.getEmail())
				.role(user.getRole())
				.build();
	}

}
