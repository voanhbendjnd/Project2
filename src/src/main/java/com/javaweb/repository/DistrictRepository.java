package com.javaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.repository.entity.DistrictEntity;

public interface DistrictRepository{
	DistrictEntity FindNameById(Long id);
}
