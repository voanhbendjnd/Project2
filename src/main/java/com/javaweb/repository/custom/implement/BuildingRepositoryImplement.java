package com.javaweb.repository.custom.implement;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.javaweb.beans.BuildingDTO;
import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionJDBC;
import com.javaweb.utils.NumberUtil;
import com.javaweb.utils.StringUtil;
import com.mysql.cj.Query;

@Repository
// tang nay de chuyen du lieu tu json dto thanh entity qua database
public class BuildingRepositoryImplement implements BuildingRepositoryCustom{
	@PersistenceContext
	private EntityManager entityManager;

	public static void JoinTable(BuildingSearchBuilder builder, StringBuilder sql) {
		Long staffid = builder.getStaffid();
		if (staffid != null) {
			sql.append(" inner join assignmentbuilding on b.id = assignmentbuilding.buildingid ");
		}
		List<String> typecode = builder.getTypecode();
		if (typecode != null && !typecode.isEmpty()) {
			sql.append(" inner join buildingrenttype on b.id = buildingrenttype.buildingid ");
			sql.append(" inner join renttype on renttype.id = buildingrenttype.renttypeid ");

		}
		// params.get() là lấy dữ liệu từ Json qua check
//		Integer rentAreaTo = builder.getAreato();
//		Integer rentAreaFrom = builder.getAreafrom();;
//		if (rentAreaTo != null) {
//			sql.append(" inner join rentarea on rentarea.buildingid = b.id");
//		}
//		if(rentAreaFrom != null) {
//			sql.append(" inner join rentarea on rentarea.buildingid = b.id");
//
//		}
	}

	public static void QueryNoraml(BuildingSearchBuilder builder, StringBuilder where) {
//    	for(Map.Entry<String, Object> it : params.entrySet()) {
//    		if(!it.getKey().equals("staffId") && !it.getKey().equals("typecode") && !it.getKey().startsWith("area") && !it.getKey().startsWith("rentPrice")) {
//    			String value = it.getValue().toString();
//    			if(NumberUtil.isNumber(value)) {
//    				where.append(" and b." + it.getKey() + " = " +  value + " ");
//    			}
//    			else {
//    				where.append(" and b. " + it.getKey() + " like '%" + value + "%' ");
//    			}
//    		}
//    	}
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for (Field x : fields) {
				// set Accesssible de no co the duyet
				x.setAccessible(true);
				String fieldname = x.getName();
				if (fieldname.equals("staffid") && !fieldname.equals("typecode") && !fieldname.startsWith("area")
						&& !!fieldname.startsWith("rentprice")) {
					Object value = x.get(builder);
					if (value != null) {
						if (x.getType().getName().equals("java.lang.Integer"))
							where.append(" and b." + fieldname + " = " + value + " ");

					} else if (x.getType().getName().equals("java.lang.String")) {
						where.append(" and b. " + fieldname + "like '%" + value + "' ");
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void QuerySpecial(BuildingSearchBuilder builder, StringBuilder where) {
		Long staffid = builder.getStaffid();
		if (staffid != null) {
			where.append(" and assignmentbuilding.staffId = " + staffid);
		}
		Long rentAreaTo = builder.getAreato();
		Long rentAreaFrom = builder.getAreafrom();

		if (rentAreaTo != null || rentAreaFrom != null) {
			where.append(" and exists (select * from rentarea r where b.id = r.buildingid ");
			if (rentAreaFrom != null) {
				where.append(" and r.value >= " + rentAreaFrom);
			}
			if (rentAreaTo != null) {
				where.append(" and r.value <=" + rentAreaTo);
			}
			where.append(") ");
		}
		Long rentPriceTo = builder.getRentpriceto();
		Long rentPriceFrom = builder.getRentpricefrom();
		if (rentPriceTo != null || rentPriceFrom != null) {
			if (rentPriceFrom != null) {
				where.append(" and b.rentprice >= " + rentPriceFrom);
			}
			if (rentPriceTo != null) {
				where.append(" and b.rentprice <= " + rentPriceTo);
			}
		}
		List<String> typecode = builder.getTypecode();
		if (typecode != null && !typecode.isEmpty()) {
//    		List<String> code = new ArrayList<>();
//    		for(String x : typecode) {
//    			code.add("'" + x + "'");
//    		}
//    		where.append(" and renttype.code in(" + String.join(",", code) + ") ");
//    		where.append(" and");
			where.append(" and( ");
			String sql = typecode.stream().map(it -> "renttype.code like" + "'%" + it + "%'")
					.collect(Collectors.joining(" or "));
			where.append(sql);
			where.append(" ) ");
		}

	}

	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder("select b.* from building b ");
		JoinTable(buildingSearchBuilder, sql);
		StringBuilder where = new StringBuilder(" where 1 = 1 ");
		QueryNoraml(buildingSearchBuilder, where);
		QuerySpecial(buildingSearchBuilder, where);
		where.append(" group by b.id ");
		sql.append(where);
		javax.persistence.Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
		return query.getResultList();
//    	String s = sql.toString();
//    	System.out.println(s);
//		List<BuildingEntity> res = new ArrayList<>();
//		try (Connection con = ConnectionJDBC.getConnection()) {
//			Statement stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery(sql.toString());
//			while (rs.next()) {
//				BuildingEntity buiE = new BuildingEntity();
//				buiE.setId(rs.getLong("id"));
//				buiE.setName(rs.getString("name"));
//				buiE.setWard(rs.getString("ward"));
//				buiE.setNumberofbasement(rs.getLong("numberofbasement"));
////				buiE.setDistrictid(rs.getLong("districtid"));
//				buiE.setStreet(rs.getString("street"));
//				buiE.setFloorarea(rs.getLong("floorarea"));
//				buiE.setRentprice(rs.getLong("rentprice"));
//				buiE.setServicefee(rs.getLong("servicefee"));
//				buiE.setBrokeragefee(rs.getLong("brokeragefee"));
//				buiE.setManagername(rs.getString("managername"));
//				buiE.setManagerphonenumber(rs.getString("managerphonenumber"));
//				res.add(buiE);
//			}
//		} catch (Exception ex) {
//			System.out.println(ex);
//		}

//    	    try (Connection con = DriverManager.getConnection(url, username, password);
//    	         Statement stmt = con.createStatement();
//    	         ResultSet rs = stmt.executeQuery(sql.toString())) {
//
//    	        while (rs.next()) {
//    	            BuildingEntity buiE = new BuildingEntity();
//    	            buiE.setId(rs.getLong("id"));
//    	            buiE.setName(rs.getString("name"));
//    	            buiE.setWard(rs.getString("ward"));
//    	            buiE.setNumberofbasement(rs.getInt("numberofbasement"));
//    	            buiE.setDistrictid(rs.getLong("districtid"));
//    	            buiE.setStreet(rs.getString("street"));
//    	            buiE.setFloorarea(rs.getInt("floorarea"));
//    	            buiE.setRentprice(rs.getInt("rentprice"));
//    	            buiE.setServicefee(rs.getInt("servicefee"));
//    	            buiE.setBrokeragefee(rs.getInt("brokeragefee"));
//    	            buiE.setManagername(rs.getString("managername"));
//    	            buiE.setManagerphonenumber(rs.getString("managerphonenumber"));
//    	            res.add(buiE);
//    	        }
//    	    } catch (Exception ex) {
//    	        System.err.println(ex.getMessage());
//    	    }
//
//		return res;
	}


	


}
