package com.javaweb.builder;

import java.util.ArrayList;
import java.util.List;
// function super'
public class BuildingSearchBuilder {
	// fill ma muon tim kiem
	private String name;
	private Long floorarea, districtid;
	private String ward, street;
	private Long numberofbasement;
	private List<String> typecode = new ArrayList<>();
	private String managername;
	private String managerphonenumber;
	private Long rentpricefrom, rentpriceto;
	private Long areafrom, areato, staffid;
	// thay vi dung nhiu tham so thi tach ra nguoi ta goi la builder parttent
	private BuildingSearchBuilder (Builder builder){
		this.name = builder.name;
		this.floorarea = builder.floorarea;
		this.ward = builder.ward;
		this.street = builder.street;
		this.numberofbasement = builder.numberofbasement;
		this.typecode = builder.typecode;
		this.managername = builder.managername;
		this.managerphonenumber = builder.managerphonenumber;
		this.rentpricefrom = builder.rentpricefrom;
		this.rentpriceto = builder.rentpriceto;
		this.areafrom = builder.areafrom;
		this.staffid = builder.staffid;
		this.districtid = builder.districtid;
	}
	public String getName() {
		return name;
	}
	public Long getFloorarea() {
		return floorarea;
	}
	public String getWard() {
		return ward;
	}
	public String getStreet() {
		return street;
	}
	
	public Long getDistrictid() {
		return districtid;
	}
	public Long getNumberofbasement() {
		return numberofbasement;
	}
	public List<String> getTypecode() {
		return typecode;
	}
	public String getManagername() {
		return managername;
	}
	public String getManagerphonenumber() {
		return managerphonenumber;
	}
	public Long getRentpricefrom() {
		return rentpricefrom;
	}
	public Long getRentpriceto() {
		return rentpriceto;
	}
	public Long getAreafrom() {
		return areafrom;
	}
	public Long getAreato() {
		return areato;
	}
	public Long getStaffid() {
		return staffid;
	}
	// funtion con dung ham set
	public static class Builder {
	    private Long districtid;
		private String name;
	    private Long floorarea;
	    private String ward, street;
	    private Long numberofbasement;
	    private List<String> typecode = new ArrayList<>();
	    private String managername;
	    private String managerphonenumber;
	    private Long rentpricefrom, rentpriceto;
	    private Long areafrom, areato, staffid;

	    public Builder setName(String name) {
	        this.name = name;
	        return this;
	    }

	    public Builder setFloorarea(Long floorarea) {
	        this.floorarea = floorarea;
	        return this;
	    }

	    public Builder setWard(String ward) {
	        this.ward = ward;
	        return this;
	    }

	    public Builder setStreet(String street) {
	        this.street = street;
	        return this;
	    }

	    public Builder setDistrictid(Long districtid) {
	        this.districtid = districtid;
	        return this;
	    }

	    public Builder setNumberofbasement(Long numberofbasement) {
	        this.numberofbasement = numberofbasement;
	        return this;
	    }

	    public Builder setTypecode(List<String> typecode) {
	        this.typecode = typecode;
	        return this;
	    }

	    public Builder setManagername(String managername) {
	        this.managername = managername;
	        return this;
	    }

	    public Builder setManagerphonenumber(String managerphonenumber) {
	        this.managerphonenumber = managerphonenumber;
	        return this;
	    }

	    public Builder setRentpricefrom(Long rentpricefrom) {
	        this.rentpricefrom = rentpricefrom;
	        return this;
	    }

	    public Builder setRentpriceto(Long rentpriceto) {
	        this.rentpriceto = rentpriceto;
	        return this;
	    }

	    public Builder setAreafrom(Long areafrom) {
	        this.areafrom = areafrom;
	        return this;
	    }

	    public Builder setAreato(Long areato) {
	        this.areato = areato;
	        return this;
	    }

	    public Builder setStaffid(Long staffid) {
	        this.staffid = staffid;
	        return this;
	    }

	    public BuildingSearchBuilder build() {
	        return new BuildingSearchBuilder(this);
	    }
	}

}
