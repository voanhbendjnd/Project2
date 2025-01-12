package com.javaweb.ConvertTo;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.javaweb.beans.BuildingDTO;
import com.javaweb.repository.entity.BuildingEntity;
@Component
public class ConvertToEntity {
	private static ModelMapper modelMapper = new ModelMapper();
	public static BuildingEntity ConverToEntity(BuildingDTO buildingDTO) {
		return modelMapper.map(buildingDTO, BuildingEntity.class);

	}
}
