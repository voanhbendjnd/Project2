package com.javaweb.api;

import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.beans.BuildingDTO;

import CustomerException.OutputException;
import repository.BuildingRepository;
import repository.entity.BuildingEntity;
import service.BuildingService;
import serviceimplement.BuildingServiceImplement;
@RestController
public class BuildingAPI {
//	@Autowired
//	private ArrayList<BuildingDTO> buildingse = new ArrayList<>();
//	@Autowired
//	private BuildingRepository buildingRepository;
	private BuildingServiceImplement buildingService = new BuildingServiceImplement();
//	@GetMapping(value="/api/building/")
//	public ArrayList<BuildingDTO> getbuilding(@RequestParam(value = "name", required = false) String name,
//											 @RequestParam(value = "numberofbasement", required = false) String basement
//											){
//		ArrayList<BuildingEntity> buidingEntities = buildingRepository.FindAll(name, basement);
//		ArrayList<BuildingDTO> ress = new ArrayList<>();
//		for(BuildingEntity x :buidingEntities) {
//			BuildingDTO bui = new BuildingDTO();
//			bui.setName(x.getName());
//			bui.setAddress(x.getStreet() + "," + x.getWard());
//			bui.setBasement(x.getBasement());
//			ress.add(bui);
//		}
//		
//		return ress;
//	}
	
//	private BuildingService buildingService;
	@GetMapping(value = "api/building/")
	public ArrayList<BuildingDTO> bui(@RequestParam(value = "name", required = false) String name,
										@RequestParam(value = "numberofbasement", required = false) String basement){
		return buildingService.FindAll(name, basement);
	}
//	@PostMapping(value="api/building/")
//	public ResponseEntity<ArrayList<BuildingEntity>> postbuilding(@RequestBody ArrayList<BuildingEntity> bui){
//		ArrayList<BuildingEntity> res = buildingRepository.SaveAll(bui);
//		return ResponseEntity.status(201).body(res);
//		
//	}
}



