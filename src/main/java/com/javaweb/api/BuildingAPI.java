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

	private BuildingServiceImplement buildingservice = new BuildingServiceImplement();
	
	
	@GetMapping(value = "/api/building/")
	public List<BuildingDTO> GetBuilding(@RequestParam Map<String, Object> params, @RequestParam(value = "typecode", required = false) List <String> typecode){
		List<BuildingDTO> res = buildingservice.timkiem(params, typecode);
		return res;
		
	}
	@DeleteMapping(value = "/api/building/{id}")
	public void Helio(@PathVariable Integer id) {
		System.out.println("succsecfull!");
	}
	
	// insert function
	@PostMapping(value = "/api/building/")
	public void postBuilding(@RequestBody BuildingRequestDTO buildingRequest) {
		BuildingEntity Entity = new BuildingEntity();
		Entity.setName(buildingRequest.getName());
		Entity.setWard(buildingRequest.getWard());
		Entity.setStreet(buildingRequest.getStreet());
		DistrictEntity districtEntity = new DistrictEntity();
		districtEntity.setId(buildingRequest.getDistrictid());
		entityManager.persist(Entity);
		System.out.println("Succsessfull");
		
	}
	
	
}



