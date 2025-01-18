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
@Primary
// tang nay de chuyen du lieu tu json dto thanh entity qua database
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
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
	}

	public static void QueryNoraml(BuildingSearchBuilder builder, StringBuilder where) {
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for (Field x : fields) {
				// set Accesssible de no co the duyet
				x.setAccessible(true);
				String fieldname = x.getName();
				Object value = x.get(builder);
				if (!fieldname.equals("staffid") && !fieldname.equals("typecode") && !fieldname.startsWith("area")
						&& !fieldname.startsWith("rentprice")) {
					if (value != null) {
						if (x.getType().getName().equals("java.lang.String")) {
							String stringvalue = (String) value;
							if (!stringvalue.isEmpty())
								where.append(" and b." + fieldname + " like '%" + value + "%' ");
						}
						if (x.getType().getName().equals("java.lang.Integer")
								|| x.getType().getName().equals("java.lang.Long"))
							where.append(" and b." + fieldname + " = " + value + " ");

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
				where.append(" and r.value <= " + rentAreaTo);
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
			where.append(" and( ");
			String sql = typecode.stream().map(it -> "renttype.code like" + "'%" + it + "%' ")
					.collect(Collectors.joining(" or "));
			where.append(sql);
			where.append(" ) ");
		}

	}

	public List<BuildingEntity> findCustomBuildings(BuildingSearchBuilder builder) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder("select b.* from building b ");
		JoinTable(builder, sql);
		StringBuilder where = new StringBuilder(" where 1 = 1 ");
		QueryNoraml(builder, where);
		QuerySpecial(builder, where);
		where.append(" group by b.id ");
		sql.append(where);
		javax.persistence.Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
		return query.getResultList();
	}

}