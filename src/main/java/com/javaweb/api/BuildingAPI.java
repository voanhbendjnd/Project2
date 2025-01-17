package com.javaweb.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.CustomerException.OutputException;
import com.javaweb.beans.BuildingDTO;
import com.javaweb.beans.BuildingRequestDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.service.BuildingService;
import com.javaweb.serviceimplement.BuildingServiceImplement;
@RestController
@Transactional
public class BuildingAPI {
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private BuildingService buildingservice;
	@Autowired
	private BuildingRepository buildingRepository;

	@GetMapping(value = "/api/building/")
	public List<BuildingDTO> GetBuilding(@RequestParam Map<String, Object> params, @RequestParam(value = "typecode", required = false) List <String> typecode){
		List<BuildingDTO> res = buildingservice.findAll(params, typecode);
		return res;
		
	}
//	@GetMapping(value = "/api/building/{id}")
//	public BuildingDTO getBuildingById(@PathVariable Long id){
//		BuildingDTO res = new BuildingDTO();
//		BuildingEntity entity = buildingRepository.findById(id).get();
//		return res;
//	}
//	@DeleteMapping(value = "/api/building/{ids}")
//	public void DeteleBuidingById(@PathVariable Long[] ids) {
//		// xoa 1
////		buildingRepository.deleteById(id);
//		buildingRepository.deleteByIdIn(ids);
//		// xoa het
//		
//	}
	@PutMapping(value = "api/building/")
	public void updateBuilding(@RequestBody List<BuildingDTO> dtoList) {
		buildingservice.updateBuilding(dtoList);
	}
	@DeleteMapping(value = "/api/building/{ids}")
	public void DeleteBuildingById(@PathVariable Long[] ids) {
		// clear ở tầng service
		buildingservice.deleteByIdIn(ids);
		System.out.println("CLear successful");
	}
	
	@PostMapping(value = "/api/building/")
	public void addBuilding(@RequestBody List<BuildingDTO> dto) {
		buildingservice.saveAll(dto);
	}
//	@GetMapping(value = "/api/building/{name}/{street}")
//	public BuildingDTO getBuilding(@PathVariable String name, @PathVariable String street) {
//		BuildingDTO res = new BuildingDTO();
//		List<BuildingEntity> building = buildingRepository.findByNameContainingAndStreetContaining(name, street);
//		return res;
//	}
//	@PostMapping(value = "/api/building/")
//	public void insertBuilding(@RequestBody BuildingRequestDTO requestDTO) {
//		BuildingEntity entity = new BuildingEntity();
//		entity.setId(1L);
//		entity.setName(requestDTO.getName());
//		entity.setStreet(requestDTO.getStreet());
//		entity.setWard(requestDTO.getWard());
//		DistrictEntity districtentity = new DistrictEntity();
//		districtentity.setId(requestDTO.getDistrictid());
//		entity.setDistrict(districtentity);
//		buildingRepository.save(entity);
//		// bỏ vô model convert
//		// DTO sang entity
//		System.out.println("DA SAVE!");
//	}
//	@PutMapping(value = "/api/building/")
//	public void updateBuilding(@RequestBody BuildingRequestDTO dto) {
//		BuildingEntity entity = buildingRepository.findById(dto.getId()).get();
//		entity.setName(dto.getName());
//		entity.setWard(dto.getWard());
//		entity.setStreet(dto.getStreet());
//		DistrictEntity districtEntity = new DistrictEntity();
//		districtEntity.setId(dto.getDistrictid());
//		entity.setDistrict(districtEntity);
//		buildingRepository.save(entity);
//		System.out.println("UPDATE!");
//		
//		
//	}
	// insert function
//	@PostMapping(value = "/api/building/")
//	public void postBuilding(@RequestBody BuildingRequestDTO buildingRequest) {
//		BuildingEntity Entity = new BuildingEntity();
//		Entity.setName(buildingRequest.getName());
//		Entity.setWard(buildingRequest.getWard());
//		Entity.setStreet(buildingRequest.getStreet());
//		DistrictEntity districtEntity = new DistrictEntity();
//		districtEntity.setId(buildingRequest.getDistrictid());
//		entityManager.persist(Entity);
//		System.out.println("Succsessfull");
//		
//	}
	
	
}



