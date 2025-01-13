package com.javaweb.repository.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository
@Primary
public class BuildingRepositoryImplement implements BuildingRepository{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public ArrayList<BuildingEntity> FindAll(String name, String basement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<BuildingEntity> SaveAll(ArrayList<BuildingEntity> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BuildingEntity> Getdata(String name, Integer floorarea, String ward, String street,
			Integer numberofbasement, String direction, String level, Integer startarea, Integer endarea,
			Integer startrentprice, Integer endrentprice, String managername, String managerphonenumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BuildingEntity> timkiem(BuildingSearchBuilder buildingSerbuilder) {
	    String sql = "select * FROM Building b where b.name like '%building%' ";
	    Query query =  (Query) entityManager.createNativeQuery(sql, BuildingEntity.class);
//  TypedQuery<BuildingEntity> query = entityManager.createQuery(sql, BuildingEntity.class);
	    List<BuildingEntity> res = ((javax.persistence.Query) query).getResultList();
	    return res;
	}


	@Override
	public List<BuildingEntity> Timkiem2(Map<String, Object> params, List<String> typecode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<BuildingEntity> findByIdWithDistrict(Long id) {
		// TODO Auto-generated method stub
		String jpql = "SELECT b FROM BuildingEntity b JOIN FETCH b.district WHERE b.id = :id";
		TypedQuery<BuildingEntity> query = entityManager.createQuery(jpql, BuildingEntity.class);
		query.setParameter("id", id);

		// Trả về Optional để tránh null
		try {
			BuildingEntity result = query.getSingleResult();
			return Optional.of(result);
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	@Override
	public BuildingEntity InsertJPA(BuildingEntity buildingEntity) {
		// TODO Auto-generated method stub
		entityManager.persist(buildingEntity);
		return buildingEntity;
	}

	

}
