package com.javaweb.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.repository.entity.BuildingEntity;

@Repository
public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>, BuildingRepositoryCustom{
	List<BuildingEntity> findByNameContainingAndStreetContaining(String name, String street);
	void deleteByIdIn(Long [] ids);
	void deleteByIdIn(List <Long> ids);
}
