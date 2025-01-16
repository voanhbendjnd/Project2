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
	List<BuildingDTO> findByNameContaining(String name);
	List<BuildingDTO> findAll(Map<String, Object> params, List<String>typecode);
	void deleteByIdIn(Long [] ids);
	void deleteByIdIn(List<Long> ids);
}
