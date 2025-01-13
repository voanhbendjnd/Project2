package com.javaweb.repository.implement;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import com.javaweb.beans.BuildingDTO;
import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionJDBC;
import com.javaweb.utils.NumberUtil;
import com.javaweb.utils.StringUtil;
import com.mysql.cj.Query;

@Repository
@PropertySource("classpath:application.properties")
// tang nay de chuyen du lieu tu json dto thanh entity qua database
public class JDBCBuildingRepositoryImplement implements BuildingRepository{
//	private static String url = "jdbc:mysql://localhost:3306/estatebasic?autoReconnect=true&useSSL=false";
//	private static String username = "root";
//	private static String password = "1607";
	@PersistenceContext
	private EntityManager entityManager;
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;

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
						if (x.getType().getName().equals("java.lang.Integer")) where.append(" and b." + fieldname + " = " + value + " ");

					} else if(x.getType().getName().equals("java.lang.String")) {
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
			if(rentPriceFrom != null) {
				where.append(" and b.rentprice >= " + rentPriceFrom);
			}
			if(rentPriceTo != null) {
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

	@Override
	public List<BuildingEntity> Timkiem2(Map<String, Object> params, List<String> typecode) {
		StringBuilder sql = new StringBuilder(
				"SELECT b.id, b.name, b.districtid, b.street, b.ward, b.numberofbasement, b.floorarea, b.rentprice, b.managername, b.managerphonenumber, b.servicefee, b.brokeragefee FROM building b ");
//    	JoinTable(params, typecode, sql);
		StringBuilder where = new StringBuilder(" where 1 = 1 ");
//    	QueryNoraml(params, typecode, where);
//    	QuerySpecial(params, typecode,where);
		where.append(" group by b.id ");
		sql.append(where);
		List<BuildingEntity> res = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(url, username, password)) {
			PreparedStatement stmt = con.prepareStatement(sql.toString());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				BuildingEntity buiE = new BuildingEntity();
				buiE.setId(rs.getLong("id"));
				buiE.setName(rs.getString("name"));
				buiE.setWard(rs.getString("ward"));
				buiE.setNumberofbasement(rs.getLong("numberofbasement"));
//				buiE.setDistrictid(rs.getLong("districtid"));
				buiE.setStreet(rs.getString("street"));
				buiE.setFloorarea(rs.getLong("floorarea"));
				buiE.setRentprice(rs.getLong("rentprice"));
				buiE.setServicefee(rs.getLong("servicefee"));
				buiE.setBrokeragefee(rs.getLong("brokeragefee"));
				buiE.setManagername(rs.getString("managername"));
				buiE.setManagerphonenumber(rs.getString("managerphonenumber"));
				res.add(buiE);
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return res;
	}

	@Override
	public List<BuildingEntity> timkiem(BuildingSearchBuilder buildingSearchBuilder) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder(
				"SELECT b.id, b.name, b.districtid, b.street, b.ward, b.numberofbasement, b.floorarea, b.rentprice, b.managername, b.managerphonenumber, b.servicefee, b.brokeragefee FROM building b ");
		JoinTable(buildingSearchBuilder, sql);
		StringBuilder where = new StringBuilder(" where 1 = 1 ");
		QueryNoraml(buildingSearchBuilder, where);
		QuerySpecial(buildingSearchBuilder, where);
		where.append(" group by b.id ");
		sql.append(where);
//    	String s = sql.toString();
//    	System.out.println(s);
		List<BuildingEntity> res = new ArrayList<>();
		try (Connection con = ConnectionJDBC.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				BuildingEntity buiE = new BuildingEntity();
				buiE.setId(rs.getLong("id"));
				buiE.setName(rs.getString("name"));
				buiE.setWard(rs.getString("ward"));
				buiE.setNumberofbasement(rs.getLong("numberofbasement"));
//				buiE.setDistrictid(rs.getLong("districtid"));
				buiE.setStreet(rs.getString("street"));
				buiE.setFloorarea(rs.getLong("floorarea"));
				buiE.setRentprice(rs.getLong("rentprice"));
				buiE.setServicefee(rs.getLong("servicefee"));
				buiE.setBrokeragefee(rs.getLong("brokeragefee"));
				buiE.setManagername(rs.getString("managername"));
				buiE.setManagerphonenumber(rs.getString("managerphonenumber"));
				res.add(buiE);
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}

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
		return res;
	}

	@Override
	public List<BuildingEntity> Getdata(String name, Integer floorarea, String ward, String street,
			Integer numberofbasement, String direction, String level, Integer startarea, Integer endarea,
			Integer startrentprice, Integer endrentprice, String managername, String managerphonenumber) {
		StringBuilder sql = new StringBuilder("SELECT * FROM building b");

//		if (district != null && !district.isEmpty()) {
//		    sql.append(" INNER JOIN district d ON d.id = b.districtid");
//		}

		sql.append(" WHERE 1 = 1");

		if (name != null && !name.isEmpty()) {
			sql.append(" AND b.name LIKE ?");
		}

		if (floorarea != null) {
			sql.append(" AND b.floorarea = ?");
		}

		if (ward != null && !ward.isEmpty()) {
			sql.append(" AND b.ward = ?");
		}

		if (street != null && !street.isEmpty()) {
			sql.append(" AND b.street = ?");
		}

		if (numberofbasement != null) {
			sql.append(" AND b.numberofbasement = ?");
		}

		if (direction != null && !direction.isEmpty()) {
			sql.append(" AND b.direction = ?");
		}

		if (level != null && !level.isEmpty()) {
			sql.append(" AND b.level = ?");
		}

		if (startarea != null) {
			sql.append(" AND b.floorarea > ?");
		}

		if (endarea != null) {
			sql.append(" AND b.floorarea < ?");
		}

		if (startrentprice != null) {
			sql.append(" AND b.rentprice > ?");
		}

		if (endrentprice != null) {
			sql.append(" AND b.rentprice < ?");
		}

		if (managername != null && !managername.isEmpty()) {
			sql.append(" AND b.managername LIKE ?");
		}

		if (managerphonenumber != null && !managerphonenumber.isEmpty()) {
			sql.append(" AND b.managerphonenumber LIKE ?");
		}

		// Chạy câu lệnh SQL với PreparedStatement
		List<BuildingEntity> res = new ArrayList<>();
		try (Connection con = ConnectionJDBC.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql.toString())) {

			int cnt = 1;

			if (name != null && !name.isEmpty()) {
				pstmt.setString(cnt++, "%" + name + "%");
			}

			if (floorarea != null) {
				pstmt.setInt(cnt++, floorarea);
			}

			if (ward != null && !ward.isEmpty()) {
				pstmt.setString(cnt++, ward);
			}

			if (street != null && !street.isEmpty()) {
				pstmt.setString(cnt++, street);
			}

			if (numberofbasement != null) {
				pstmt.setInt(cnt++, numberofbasement);
			}

			if (direction != null && !direction.isEmpty()) {
				pstmt.setString(cnt++, direction);
			}

			if (level != null && !level.isEmpty()) {
				pstmt.setString(cnt++, level);
			}

			if (startarea != null) {
				pstmt.setInt(cnt++, startarea);
			}

			if (endarea != null) {
				pstmt.setInt(cnt++, endarea);
			}

			if (startrentprice != null) {
				pstmt.setInt(cnt++, startrentprice);
			}

			if (endrentprice != null) {
				pstmt.setInt(cnt++, endrentprice);
			}

			if (managername != null && !managername.isEmpty()) {
				pstmt.setString(cnt++, "%" + managername + "%");
			}

			if (managerphonenumber != null && !managerphonenumber.isEmpty()) {
				pstmt.setString(cnt++, "%" + managerphonenumber + "%");
			}

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					BuildingEntity bui = new BuildingEntity();
//					bui.setName(rs.getString("name"));
//					bui.setFloorarea(rs.getInt("floorarea"));
////		            bui.setDistrict(rs.getString("district"));
//					bui.setWard(rs.getString("ward"));
//					bui.setStreet(rs.getString("street"));
//					bui.setNumberofbasement(rs.getInt("numberofbasement"));
//					bui.setManagername(rs.getString("managername"));
//					bui.setManagerphonenumber(rs.getString("managerphonenumber"));
//					res.add(bui);
				}
			} catch (Exception ex) {
				System.out.println(ex);
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return res;
	}

	@Override
	public ArrayList<BuildingEntity> FindAll(String name, String basement) {
//    	String sql = "select * from building b where name like '%" + name + "%'";
		StringBuilder sql = new StringBuilder("select * from building where 1 = 1 ");
		if (name != null && !name.equals("")) {
			sql.append("and name like '%" + name + "%' ");
		}
		if (basement != null && !basement.equals("")) {
			sql.append("and numberofbasement = " + basement + " ");
		}
		ArrayList<BuildingEntity> res = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(url, username, password);
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString());) {
			while (rs.next()) {
				BuildingEntity bui = new BuildingEntity();
//    			bui.setName(rs.getString("name"));
//    			bui.setStreet(rs.getString("Street"));
//    			bui.setWard(rs.getString("ward"));
//    			bui.setNumberofbasement(rs.getString("numberofbasement"));
//    			bui.setFloorarea(rs.getString("floorarea"));
//    			bui.setPhone(rs.getString("managerphonenumber"));
//    			bui.setRentprice(rs.getInt("rentprice"));
//    			bui.setTenquanly(rs.getString("managername"));
				res.add(bui);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return res;
	}

	@Override
	public ArrayList<BuildingEntity> SaveAll(ArrayList<BuildingEntity> entities) {
		// TODO Auto-generated method stub
//		String sql = "insert into building (name, street, numberofbasement, districtid, rentprice) values (?, ? , ?, ?, ?)";
//		ArrayList<BuildingEntity> SaveEntity = new ArrayList<>();
//		try(Connection con = DriverManager.getConnection(url, username, password);
//				PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
//				for(BuildingEntity x : entities) {
//					stmt.setString(1, x.getName());
//					stmt.setString(2, x.getStreet());
//					stmt.setInt(3, x.getNumberofbasement());
//					stmt.setInt(4, x.getDistrictid());
//					stmt.setInt(5, x.getRentprice());
//					stmt.executeUpdate();
//					try (ResultSet rs = stmt.getGeneratedKeys()){
//						if(rs.next()) {
//							x.setId(rs.getString(1));
//						}
//					}
//					SaveEntity.add(x);
//				}
//				
//		}catch(Exception ex) {
//			System.out.println(ex);
//		}
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
