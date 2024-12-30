package serviceimplement;

//import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.BuildingRepository;

import repository.BuildingRepository;
import repository.entity.BuildingEntity;
import repository.implement.copy.BuildingRepositoryImplement;
//import repository.implement.buildingrepositoryimplement;
import service.BuildingService;
import com.javaweb.beans.BuildingDTO;
import CustomerException.OutputException;
@Service
public class BuildingServiceImplement implements BuildingService {
//	@Autowired
	private BuildingRepositoryImplement buildingrepository = new BuildingRepositoryImplement();
	
	@Override
	public ArrayList<BuildingDTO> FindAll(String name, String basement) {
		ArrayList<BuildingEntity> buildingentities = buildingrepository.FindAll(name, basement);
		ArrayList<BuildingDTO>res = new ArrayList<BuildingDTO>();
		for(BuildingEntity x : buildingentities) {
			res.add(convertDTO(x));
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
			entities.add(convertEntity(x));
		}
		// save entities to database
		ArrayList<BuildingEntity> SaveEntity = buildingrepository.SaveAll(entities);
		// convert back data DTO
		ArrayList<BuildingDTO> res = new ArrayList<>();
		for(BuildingEntity x : SaveEntity) {
			res.add(convertDTO(x));
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
	private BuildingDTO convertDTO(BuildingEntity enty) {
		BuildingDTO dto = new BuildingDTO();
		dto.setId(enty.getId());
		dto.setName(enty.getName());
		dto.setBasement(enty.getBasement());
		dto.setStreet(enty.getStreet());
		dto.setDistrictid(enty.getDistrictid());
		dto.setRentprice(enty.getRentprice());
		return dto;
	}
	private BuildingEntity convertEntity(BuildingDTO dto) {
		BuildingEntity be = new BuildingEntity();
		be.setName(dto.getName());
		be.setBasement(dto.getBasement());
		be.setStreet(dto.getStreet());
		be.setDistrictid(dto.getDistrictid());
		be.setRentprice(dto.getRentprice());
		return be;
	}

}
// 1 26 00
