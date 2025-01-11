package com.javaweb.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.javaweb.CustomerException.OutputException;
import com.javaweb.beans.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;
import com.javaweb.serviceimplement.BuildingServiceImplement;
@RestController
public class BuildingAPI {
//	@Autowired
//	private ArrayList<BuildingDTO> buildingse = new ArrayList<>();
//	@Autowired
//	private BuildingRepository buildingRepository;
//	private BuildingServiceImplement buildingService = new BuildingServiceImplement();
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
//	@Autowired
//	private BuildingService buildingService;

	private BuildingServiceImplement buildingservice = new BuildingServiceImplement();
//	@GetMapping(value = "/api/building/")
//	public List<BuildingDTO> getBuildingDTO
//	(@RequestParam(value = "name", required = false) String name,
//	 @RequestParam(value = "floorarea", required = false) Integer floorarea,
//	 @RequestParam(value = "district", required = false) String district,
//	 @RequestParam(value = "ward", required = false) String ward,
//	 @RequestParam(value = "street", required = false) String street,
//	 @RequestParam(value = "numberofbasement", required = false) Integer numberofbasement,
//	 @RequestParam(value = "direction", required = false) String direction,
//	 @RequestParam(value = "level", required = false) String level,
//	 @RequestParam(value = "startarea", required = false) Integer startarea,
//	 @RequestParam(value = "endarea", required = false) Integer endarea,
//	 @RequestParam(value = "startrentprice", required = false) Integer startrentprice,
//	 @RequestParam(value = "endrentprice", required = false) Integer endrentprice,
//	 @RequestParam(value = "managername", required = false) String managername,
//	 @RequestParam(value = "managerphonenumber", required = false) String managerphonenumber){
//		return buildingService.Getdate(name, floorarea, numberofbasement, direction, managername, managerphonenumber);
//	}
//	@GetMapping(value = "/api/building/")
//	public List<BuildingDTO> getBuilding(@RequestParam Map<String, Object> params, @RequestParam(value = "typecode", required = false) List<String> typecode){
//		List<BuildingDTO> res = buildingService.timkiem(params, typecode);
//		return res;
//	}
	
	@Value("${benben}")
	private String data;
	@GetMapping(value = "/api/building/")
	public List<BuildingDTO> GetBuilding(@RequestParam Map<String, Object> params, @RequestParam(value = "typecode", required = false) List <String> typecode){
		List<BuildingDTO> res = buildingservice.timkiem(params, typecode);
		return res;
		
	}
	@DeleteMapping(value = "/api/building/{id}")
	public void Helio(@PathVariable Integer id) {
		System.out.println(data);
	}
//	private BuildingService buildingService;
//	@GetMapping(value = "api/building/")
//	public ArrayList<BuildingDTO> bui(@RequestParam(value = "name", required = false) String name,
//										@RequestParam(value = "numberofbasement", required = false) String basement){
//		return buildingService.FindAll(name, basement);
//	}
//	@PostMapping(value="api/building/")
//	public ResponseEntity<ArrayList<BuildingEntity>> postbuilding(@RequestBody ArrayList<BuildingEntity> bui){
//		ArrayList<BuildingEntity> res = buildingRepository.SaveAll(bui);
//		return ResponseEntity.status(201).body(res);
//		
//	}
//	@GetMapping(value = "/api/building/")
//	public void getBuilding(@RequestParam Map <String, String> params) {
//		buildingService.FindSecond(params);
//	}
	
}



