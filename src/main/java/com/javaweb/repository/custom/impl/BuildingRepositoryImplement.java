package com.javaweb.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository
@Primary
public class BuildingRepositoryImplement{
	@PersistenceContext
	private EntityManager entityManager;

//	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSerbuilder) {
		String sql = "select * from building b where b.name like '%building%' ";
		javax.persistence.Query query = entityManager.createNativeQuery(sql, BuildingEntity.class);
		return query.getResultList();
	}

}
