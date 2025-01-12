package com.javaweb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.javaweb.CustomerException.OutputException;
import com.javaweb.beans.BuildingDTO;
import com.javaweb.repository.entity.BuildingEntity;
@Service
public interface BuildingService {
	ArrayList<BuildingDTO> FindAll(String name, String basement);
	ArrayList<BuildingDTO> AddAll(ArrayList<BuildingDTO> bui) throws OutputException;
	List<BuildingDTO> Getdate(String name, Integer floorarea, Integer numberofbasement, 
			String direction, String managername, String managerphonenumber);
	List<BuildingDTO> timkiem(Map<String, Object> params, List<String>typecode);
	List<BuildingDTO> Timkiem2(Map<String, Object> params, List<String> typecde);
	BuildingDTO getBuildingById(Long id);
	BuildingDTO InsertJPA(BuildingDTO buildingDTO);
}
