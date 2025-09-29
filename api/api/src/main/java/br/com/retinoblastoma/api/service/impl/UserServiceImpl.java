package br.com.retinoblastoma.api.service.impl;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.retinoblastoma.api.mapper.UserMapper;
import br.com.retinoblastoma.api.repository.UserRepository;
import br.com.retinoblastoma.api.service.BaseService;
import br.com.retinoblastoma.api.service.JwtService;
import br.com.retinoblastoma.api.service.UserService;
import br.com.retinoblastoma.model.dto.UserDto;
import br.com.retinoblastoma.model.entities.User;

@Service
public class UserServiceImpl implements BaseService<UserDto>, UserService {

	@Autowired
	private final UserRepository userRepository;

	@Autowired
	private JwtService jwtService;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Long create(UserDto userDto) {

		if (userDto.getCrm() != null && userDto.getSpecialty() != null) {
			userDto.setRole("Médico");
		} else {
			userDto.setRole("Usuário");
		}
		User user = UserMapper.toUser(userDto);
		User userSaved = userRepository.save(user);
		UserDto userMapped = UserMapper.toUserDto(userSaved);
		return userMapped.getId();
	}

	@Override
	public UserDto readById(Long id) {

		Optional<User> userOptional = userRepository.findById(id);
		if (!userOptional.isPresent()) {
			return null;
		}
		User user = userOptional.get();
		UserDto userDto = UserMapper.toUserDto(user);
		return userDto;
	}

	@Override
	public User validateUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto validateLogin(String encodedData) {

		Map<CREDENTIALS, String> credentialsMap = decodeAndGetUsernameAndPassword(encodedData);

		if (credentialsMap == null || credentialsMap.size() != 2) {
			return null;
		}

		String username = credentialsMap.get(CREDENTIALS.USERNAME);
		String password = credentialsMap.get(CREDENTIALS.PASSWORD);

		User user = userRepository.findByUsernameAndPassword(username, password);

		if (user == null) {
			return null;
		}

		String token = jwtService.getJWTToken(user);

		user.setPassword(null);
		user.setToken(token);
		System.out.println(user.getToken());

		UserDto userDto = UserMapper.toUserDto(user);
		return userDto;
	}

	private enum CREDENTIALS {
		USERNAME, // 0
		PASSWORD, // 1
	}

	private Map<CREDENTIALS, String> decodeAndGetUsernameAndPassword(String encodedData) {

		try {
			// Basic aHDKJaHSD32h9had9s&Dha78da8d
			String[] splittedData = encodedData.split("Basic ");
			if (splittedData.length != 2) {
				return null;
			}

			byte[] decodedBytes = Base64.getDecoder().decode(splittedData[1]);

			// Username=nome_do_cara;Password=senha_do_cara
			String decodedString = new String(decodedBytes, "utf-8");

			String[] firstPart = decodedString.split("Username=");
			if (firstPart.length != 2) {
				return null;
			}

			String[] credentials = firstPart[1].split(";Password=");
			if (credentials.length != 2) {
				return null;
			}

			Map<CREDENTIALS, String> credentialsMap = new HashMap<CREDENTIALS, String>();
			credentialsMap.put(CREDENTIALS.USERNAME, credentials[0]);
			credentialsMap.put(CREDENTIALS.PASSWORD, credentials[1]);

			return credentialsMap;
		} catch (Exception e) {

			System.out.println(e.getMessage());
			return null;
		}

	}

	@Override
	public Boolean deleteById(Long id) {

		Optional<User> optionalUser = userRepository.findById(id);

		if (optionalUser.get() != null) {
			userRepository.deleteById(id);
			return true;
		}
		return null;
	}

}
