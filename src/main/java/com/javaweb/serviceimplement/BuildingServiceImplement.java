package com.javaweb.serviceimplement;
//import java.sql.SQLException;
import java.util.ArrayList;
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
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.repository.implement.BuildingRepositoryImplement;
import com.javaweb.repository.implement.JDBCBuildingRepositoryImplement;
import com.javaweb.repository.implement.RentAreaRepositoryImplement;
import com.javaweb.service.BuildingService;

@Service

public class BuildingServiceImplement implements BuildingService{
//	@Autowired
	
	private BuildingSearchBuilderConverter Converter = new BuildingSearchBuilderConverter();
//	private ConvertToDataTransferObject ConvertToJson = new ConvertToDataTransferObject();
	private RentAreaRepositoryImplement rent = new RentAreaRepositoryImplement(); 
//	private DistrictRepositoryImplement districtrepository = new DistrictRepositoryImplement();
	private JDBCBuildingRepositoryImplement buildingrepository = new JDBCBuildingRepositoryImplement();
//	private BuildingRepository buildingrepository;
	@Override
	public ArrayList<BuildingDTO> FindAll(String name, String basement) {
		ArrayList<BuildingEntity> buildingentities = buildingrepository.FindAll(name, basement);
		ArrayList<BuildingDTO>res = new ArrayList<BuildingDTO>();
		for(BuildingEntity x : buildingentities) {
			res.add(ConvertToDataTransferObject.ConvertToDTO(x));
		}
		return res;
	}
	
	
	@Override
	public List<BuildingDTO> timkiem(Map<String, Object> params, List<String>typecode) {
		// TODO Auto-generated method stub
		BuildingSearchBuilder builder = Converter.toBuildingSearchBuilder(params, typecode);
		List<BuildingEntity> buildingEntity = buildingrepository.timkiem(builder);
		List<BuildingDTO> res  = new ArrayList<>();
		for(BuildingEntity x : buildingEntity) {
			res.add(ConvertToDataTransferObject.ConvertToDTO(x));
		}
		return res;
	}
//	private com.javaweb.ConvertTo.ConvertToEntity EntityConvertTo = new com.javaweb.ConvertTo.ConvertToEntity();
	@Override
	public BuildingDTO InsertJPA(BuildingDTO buildingDTO) {
		// TODO Auto-generated method stub
		BuildingEntity buildingEntity = ConvertToEntity.ConverToEntity(buildingDTO);
		BuildingEntity saveEntity = buildingRepository.InsertJPA(buildingEntity);
		
		return ConvertToDataTransferObject.ConvertToDTO(saveEntity);
	}
	@Override
	public List<BuildingDTO> Getdate(String name, Integer floorarea, Integer numberofbasement, String direction, String managername, String managerphonenumber) {
		// TODO Auto-generated method stub
		List<BuildingEntity> buildingEntity = buildingrepository.Getdata(managername, floorarea, managerphonenumber, name, numberofbasement, direction, direction, numberofbasement, numberofbasement, floorarea, numberofbasement, managername, managerphonenumber);
		List<BuildingDTO> res = new ArrayList<BuildingDTO>();
		for(BuildingEntity x : buildingEntity) {
			res.add(ConvertToDataTransferObject.ConvertToDTO(x));
		}
		return res;
	}
	@Override
	public ArrayList<BuildingDTO> AddAll(ArrayList<BuildingDTO> bui) throws OutputException {
		// TODO Auto-generated method stub
		// Check data
		valiDate(bui);
		ArrayList<BuildingEntity> entities = new ArrayList<>();
		// change DTO to entity
		for(BuildingDTO x : bui) {
			entities.add(ConvertToEntity(x));
		}
		// save entities to database
		ArrayList<BuildingEntity> SaveEntity = buildingrepository.SaveAll(entities);
		// convert back data DTO
		ArrayList<BuildingDTO> res = new ArrayList<>();
		for(BuildingEntity x : SaveEntity) {
		}
		return res;
	}
	
	private void valiDate(ArrayList<BuildingDTO> bui) throws OutputException {
		for(BuildingDTO x : bui) {
			if(x.getName().equals("") || x.getName() == null) {
				throw new OutputException("Name is not empty!");
			}
		}
		
	}
//	private RentAreaEntity rent = new RentAreaEntity();
	
	
	private BuildingEntity ConvertToEntity(BuildingDTO dto) {
		BuildingEntity be = new BuildingEntity();
		be.setName(dto.getName());
//		be.setNumberofbasement(dto.getNumberofbasement());
//		
//		
//		be.setRentprice(dto.getRentprice());
		return be;
	}
	@Override
	public List<BuildingDTO> Timkiem2(Map<String, Object> params, List<String> typecde) {
		List<BuildingEntity> buildingEntity = buildingrepository.Timkiem2(params, typecde);
		List<BuildingDTO> buildingDTO = new ArrayList<BuildingDTO>();
		for(BuildingEntity enty : buildingEntity) {
			buildingDTO.add(ConvertToDataTransferObject.ConvertToDTO(enty));
		}
		return buildingDTO;
	}
	
	private BuildingRepository buildingRepository;

	public BuildingDTO getBuildingById(Long id) {
		// TODO Auto-generated method stub
		BuildingEntity building = buildingRepository.findByIdWithDistrict(id)
				.orElseThrow(() -> new RuntimeException("Building not found"));
		return ConvertToDataTransferObject.ConvertToDTO(building);
	}



}