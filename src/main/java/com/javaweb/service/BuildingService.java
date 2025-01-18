package com.javaweb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.javaweb.CustomerException.OutputException;
import com.javaweb.beans.BuildingDTO;
import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.entity.BuildingEntity;
@Service
public interface BuildingService {
	List<BuildingDTO> findByNameContaining(String name);
	List<BuildingDTO> findCustomBuildings(Map<String, Object> params, List<String>typecode);
	void deleteByIdIn(Long [] ids);
//	List<BuildingDTO> findAllBy(Map<String, Object> params, List<String> typecode);
	void deleteByIdIn(List<Long> ids);
	void saveAll(List<BuildingDTO> dto);
	void updateBuilding(List<BuildingDTO>dtoList);
	List<BuildingDTO> findByNameContainingAndWardContainingAndStreetContaining(String name, String ward, String street);
	
}
