package com.javaweb.serviceimplement;

//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
// tang nay chuyen entity lay duoc tu database truyen va cho dto roi dem qua cho json
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javaweb.ConvertTo.ConvertToEntity;
import com.javaweb.ConvertTo.BuildingSearchBuilderConverter;
import com.javaweb.ConvertTo.ConvertToDataTransferObject;
import com.javaweb.CustomerException.OutputException;
import com.javaweb.beans.BuildingDTO;
import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.custom.implement.BuildingRepositoryImplement;
import com.javaweb.repository.custom.implement.RentAreaRepositoryImplement;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImplement implements BuildingService {
	@Autowired
	private BuildingRepository buildingRepository;

	
	private BuildingSearchBuilderConverter Converter = new BuildingSearchBuilderConverter();
//	private ConvertToDataTransferObject ConvertToJson = new ConvertToDataTransferObject();
	private RentAreaRepositoryImplement rent = new RentAreaRepositoryImplement();
//	private DistrictRepositoryImplement districtrepository = new DistrictRepositoryImplement();
	@Override
	public void saveAll(List<BuildingDTO> dto) {
		// TODO Auto-generated method stub
//		List<BuildingEntity> buildingEntities = new ArrayList<>();
//		for(BuildingDTO x : dto) {
//			buildingEntities.add(ConvertToEntity.ConvertToEntity(x));
//		}
		// stream
		List<BuildingEntity> buildingEntities = dto.stream()
				.map(ConvertToEntity::ConvertToEntity)
				.collect(Collectors.toList());
		buildingRepository.saveAll(buildingEntities);
		
		
	}
	@Override
	public void updateBuilding(List<BuildingDTO> dtoList) {
		// TODO Auto-generated method stub
		List<Long> ids = dtoList.stream()
				.map(BuildingDTO::getId)
				.collect(Collectors.toList());
		List<BuildingEntity> entities = buildingRepository.findAllById(ids);
		entities = dtoList.stream()
				.map(ConvertToEntity::ConvertToEntity)
				.collect(Collectors.toList());
		buildingRepository.saveAll(entities);
		
	}
	@Override
	public void deleteByIdIn(Long [] ids) {
		buildingRepository.deleteByIdIn(ids);
	}
	@Override
	public void deleteByIdIn(List<Long> ids) {
		buildingRepository.deleteByIdIn(ids);
	}
	@Override
	public List<BuildingDTO> findAll(Map<String, Object> params, List<String> typecode) {
		// TODO Auto-generated method stub
		BuildingSearchBuilder builder = Converter.toBuildingSearchBuilder(params, typecode);
//		BuildingEntity buildingEntity = buildingRepository.findById(1L).get();
//		List<BuildingEntity> buildingEntity = buildingRepository.findByNameContaining(String name);
//		List<BuildingEntity> buildingEntity = buildingRepository.findByNameContaining("Building");
//		List<BuildingEntity> buildingEntity = buildingRepository.findAll(builder);
		List<BuildingEntity> buildingEntity = buildingRepository.findAll();
		List<BuildingDTO> res = new ArrayList<>();
		for (BuildingEntity x : buildingEntity) {
			res.add(ConvertToDataTransferObject.ConvertToDTO(x));
		}
		return res;
	}
	@Override
	public List<BuildingDTO> findByNameContaining(String name){
		List<BuildingEntity> entity = buildingRepository.findAll();
		List<BuildingDTO> res = new ArrayList<>();
		for(BuildingEntity x : entity) {
			res.add(ConvertToDataTransferObject.ConvertToDTO(x));
		}
		return res;
	}
	
	

}