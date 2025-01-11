package com.javaweb.repository.implement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.utils.ConnectionJDBC;
@Repository
public class DistrictRepositoryImplement implements DistrictRepository{
	@Override
	public DistrictEntity FindNameById(Long id) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder("select d.name from district d where d.id = " + id + ";");
		DistrictEntity districtEntity = new DistrictEntity();
		try(Connection con = ConnectionJDBC.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());
			while(rs.next()) {
				districtEntity.setName(rs.getString("name"));
			}
		}catch(Exception ex) {
			System.out.println(ex);
		}
	
		return districtEntity;
	}

}
