package repository.implement.copy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Repository;

import repository.BuildingRepository;
import repository.entity.BuildingEntity;

@Repository
public class BuildingRepositoryImplement implements BuildingRepository {
    private static String url = "jdbc:mysql://localhost:3306/estatebasic?autoReconnect=true&useSSL=false";
    private static String username = "root";
    private static String password = "1607";

    @Override
    public ArrayList<BuildingEntity> FindAll(String name, String basement) {
//    	String sql = "select * from building b where name like '%" + name + "%'";
    	StringBuilder sql = new StringBuilder("select * from building where 1 = 1 ");
    	if(name != null && !name.equals("")) {
    		sql.append("and name like '%" + name + "%' ");
    	}
    	if(basement != null && !basement.equals("")) {
    		sql.append("and numberofbasement = " + basement + " ");
    	}
    	ArrayList<BuildingEntity> res = new ArrayList<>();
    	try(Connection con = DriverManager.getConnection(url, username, password);
    				Statement stmt = con.createStatement();
    				ResultSet rs = stmt.executeQuery(sql.toString());){
    		while(rs.next()) {
    			BuildingEntity bui = new BuildingEntity();
    			bui.setName(rs.getString("name"));
    			bui.setStreet(rs.getString("Street"));
    			bui.setWard(rs.getString("ward"));
    			bui.setBasement(rs.getString("numberofbasement"));
    			res.add(bui);
    		}
    	}catch(SQLException ex) {
    		ex.printStackTrace();
    	}
    	return res;
    }

	@Override
	public ArrayList<BuildingEntity> SaveAll(ArrayList<BuildingEntity> entities) {
		// TODO Auto-generated method stub
		String sql = "insert into building (name, street, numberofbasement, districtid, rentprice) values (?, ? , ?, ?, ?)";
		ArrayList<BuildingEntity> SaveEntity = new ArrayList<>();
		try(Connection con = DriverManager.getConnection(url, username, password);
				PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
				for(BuildingEntity x : entities) {
					stmt.setString(1, x.getName());
					stmt.setString(2, x.getStreet());
					stmt.setString(3, x.getBasement());
					stmt.setInt(4, x.getDistrictid());
					stmt.setInt(5, x.getRentprice());
					stmt.executeUpdate();
					try (ResultSet rs = stmt.getGeneratedKeys()){
						if(rs.next()) {
							x.setId(rs.getString(1));
						}
					}
					SaveEntity.add(x);
				}
				
		}catch(Exception ex) {
			System.out.println(ex);
		}
		return SaveEntity;
	}
}
