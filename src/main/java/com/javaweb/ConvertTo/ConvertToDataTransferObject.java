package com.javaweb.ConvertTo;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.javaweb.beans.BuildingDTO;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.custom.implement.DistrictRepositoryImpl;
import com.javaweb.repository.custom.implement.RentAreaRepositoryImpl;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;

@Component
//  dung de chuyen entity thanh dto tu database sang json
public class ConvertToDataTransferObject {
	private static ModelMapper modelMapper = new ModelMapper();

//	private static JDBCBuildingRepositoryImplement buildingrepository = new JDBCBuildingRepositoryImplement();
	private static DistrictRepositoryImpl districtrepository = new DistrictRepositoryImpl();
	private static RentAreaRepositoryImpl rent = new RentAreaRepositoryImpl();

	public static BuildingDTO ConvertToDTO(BuildingEntity enty) {
//		BuildingDTO dto = new BuildingDTO();dto.setId(enty.getId());
//		dto.setName(enty.getName());	dto.setNumberofbasement(enty.getNumberofbasement());
//		dto.setManagername(enty.getManagername());
//		dto.setFloorarea(enty.getFloorarea());
//		dto.setManagerphonenumber(enty.getManagerphonenumber());
//		dto.setFloorarea(enty.getFloorarea());
//		dto.setRentprice(enty.getRentprice());
//		DistrictEntity district = districtrepository.FindNameById(enty.getDistrict().getId());
//		List<RentAreaEntity> rentAreas = enty.getItems();
//		building.setAddress(enty.getStreet() + ", " + enty.getWard() + ", " + district.getName());
//	
		BuildingDTO building = modelMapper.map(enty, BuildingDTO.class);

//		DistrictEntity districtEntity = districtrepository.FindNameById(enty.getDistrictid());
//		building.setAddress(enty.getStreet() + ", " + enty.getWard() + ", " + districtEntity.getName());

		if (enty.getDistrict() != null) {
			building.setAddress(enty.getStreet() + ", " + enty.getWard() + ", " + enty.getDistrict().getName());
			building.setDistrictid(enty.getDistrict().getId());
		}

		else {
			building.setAddress(enty.getStreet() + ", " + enty.getWard());
		}
		building.setAddress(enty.getStreet() + ", " + enty.getWard());
		List<RentAreaEntity> rentAreas = enty.getRents();
//		List<RentAreaEntity> rentAreas = rent.getValueByBuildingId(enty.getId());
		String resArea = rentAreas.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(", "));
		building.setRentarea(resArea);

		return building;
	}
}
