package com.javaweb.ConvertTo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.javaweb.beans.BuildingDTO;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;
@Component
public class ConvertToEntity {
	private static ModelMapper modelMapper = new ModelMapper();
	public static BuildingEntity ConvertToEntity(BuildingDTO dto) {
		BuildingEntity buildingEntity = modelMapper.map(dto, BuildingEntity.class);
		DistrictEntity district = new DistrictEntity();
		district.setId(dto.getDistrictid());
		buildingEntity.setDistrict(district);
		if (dto.getRentarea() != null && !dto.getRentarea().isEmpty()) {
	        List<RentAreaEntity> rentAreas = Arrays.stream(dto.getRentarea().split(","))
	                .map(value -> {
	                    RentAreaEntity rentArea = new RentAreaEntity();
	                    rentArea.setValue(value.trim()); // Set value là String
	                    return rentArea;
	                }).collect(Collectors.toList());
	        buildingEntity.setRents(rentAreas);
	    }
		
		return buildingEntity;
	}
}
