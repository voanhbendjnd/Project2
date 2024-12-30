package repository;

import java.sql.SQLException;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import repository.entity.BuildingEntity;
public interface BuildingRepository{
	ArrayList<BuildingEntity> FindAll(String name, String basement);
	ArrayList<BuildingEntity> SaveAll(ArrayList<BuildingEntity> entities);
}
