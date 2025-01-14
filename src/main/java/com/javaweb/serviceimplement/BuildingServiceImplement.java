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
import com.javaweb.repository.custom.impl.BuildingRepositoryImplement;
import com.javaweb.repository.custom.impl.JDBCBuildingRepositoryImplement;
import com.javaweb.repository.custom.impl.RentAreaRepositoryImplement;
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
	private JDBCBuildingRepositoryImplement buildingrepository = new JDBCBuildingRepositoryImplement();

    BuildingServiceImplement(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }
//	private BuildingRepository buildingrepository;

	@Override
	public List<BuildingDTO> findAll(Map<String, Object> params, List<String> typecode) {
		// TODO Auto-generated method stub
		BuildingSearchBuilder builder = Converter.toBuildingSearchBuilder(params, typecode);
		BuildingEntity buildingEntity = buildingRepository.findById(1L).get();
//		List<BuildingEntity> buildingEntity = buildingRepository.findAll();
		List<BuildingDTO> res = new ArrayList<>();
//		for (BuildingEntity x : buildingEntity) {
//			res.add(ConvertToDataTransferObject.ConvertToDTO(x));
//		}
		return res;
	}

	private void valiDate(ArrayList<BuildingDTO> bui) throws OutputException {
		for (BuildingDTO x : bui) {
			if (x.getName().equals("") || x.getName() == null) {
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

}