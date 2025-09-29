package br.com.retinoblastoma.api.mapper;

import br.com.retinoblastoma.model.dto.DoctorDto;
import br.com.retinoblastoma.model.dto.UserDto;
import br.com.retinoblastoma.model.entities.Doctor;

public class DoctorMapper {

	public static Doctor toDoctor(DoctorDto doctorDto) {
		return Doctor.builder().crm(doctorDto.getCrm()).specialty(doctorDto.getSpecialty()).build();
	}

	public static UserDto toUserDto(DoctorDto doctorDto) {
		return UserDto.builder()
				.name(doctorDto.getName())
				.email(doctorDto.getEmail())
				.username(doctorDto.getUsername())
				.password(doctorDto.getPassword())
				.role(doctorDto.getRole())
				.build();
	}

}
