package service;

import java.util.ArrayList;

import com.javaweb.beans.BuildingDTO;

import CustomerException.OutputException;
import repository.entity.BuildingEntity;

public interface BuildingService {
	ArrayList<BuildingDTO> FindAll(String name, String basement);
	ArrayList<BuildingDTO> AddAll(ArrayList<BuildingDTO> bui) throws OutputException;
}
