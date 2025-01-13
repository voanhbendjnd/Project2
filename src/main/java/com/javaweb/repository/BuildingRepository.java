package com.javaweb.repository;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javaweb.beans.BuildingDTO;
import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.entity.BuildingEntity;
@Repository
public interface BuildingRepository{
//	Optional<BuildingEntity> findByIdWithDistrict(Long id);
	ArrayList<BuildingEntity> FindAll(String name, String basement);
	ArrayList<BuildingEntity> SaveAll(ArrayList<BuildingEntity> entities);
	List<BuildingEntity> Getdata(String name, Integer floorarea, String ward, String street, Integer numberofbasement, 
	String direction, String level, Integer startarea, Integer endarea, Integer startrentprice,
	Integer endrentprice, String managername, String managerphonenumber);
	List<BuildingEntity> timkiem(BuildingSearchBuilder buildingSerbuilder);
	List<BuildingEntity> Timkiem2(Map<String, Object> params, List<String> typecode);
	@Query("SELECT b FROM BuildingEntity b JOIN FETCH b.district WHERE b.id = :id")
    Optional<BuildingEntity> findByIdWithDistrict(@Param("id") Long id);
	BuildingEntity InsertJPA(BuildingEntity buildingEntity);
}
