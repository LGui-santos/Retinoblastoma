package br.com.retinoblastoma.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.retinoblastoma.api.mapper.DoctorMapper;
import br.com.retinoblastoma.api.repository.DoctorRepository;
import br.com.retinoblastoma.api.service.BaseService;
import br.com.retinoblastoma.model.dto.DoctorDto;
import br.com.retinoblastoma.model.dto.UserDto;
import br.com.retinoblastoma.model.entities.Doctor;

@Service
public class DoctorServiceImpl implements BaseService<DoctorDto> {

	@Autowired
	DoctorRepository doctorRepository;

	@Autowired
	BaseService<UserDto> userService;

	@Override
	public Long create(DoctorDto doctorDto) {

		UserDto userDto = DoctorMapper.toUserDto(doctorDto);
		Long userId = userService.create(userDto);

		doctorDto.setId(userId);
		Doctor doctor = DoctorMapper.toDoctor(doctorDto);
		Doctor doctorSaved = doctorRepository.save(doctor);
		return doctorSaved.getId();
	}

	@Override
	public DoctorDto readById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
