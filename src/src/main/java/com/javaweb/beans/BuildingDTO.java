package com.javaweb.beans;

public class BuildingDTO {
	private Long id, districtid;
	private String createDate, name, managername, managerphonenumber, address;
	private int numberofbasement, floorarea, emptyarea, rentprice, brokeragefee,  servicefee;
	private String rentarea;
	public String getRentarea() {
		return rentarea;
	}
	public void setRentarea(String rentarea) {
		this.rentarea = rentarea;
	}
	public int getNumberofbasement() {
		return numberofbasement;
	}
	public void setNumberofbasement(int numberofbasement) {
		this.numberofbasement = numberofbasement;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	public String getWard() {
//		return ward;
//	}
//	public void setWard(String ward) {
//		this.ward = ward;
//	}
//	public String getStreet() {
//		return street;
//	}
//	public void setStreet(String street) {
//		this.street = street;
//	}
	
	public Long getDistrictid() {
		return districtid;
	}
	public void setDistrictid(Long districtid) {
		this.districtid = districtid;
	}
	public String getManagername() {
		return managername;
	}
	public void setManagername(String managername) {
		this.managername = managername;
	}
	public String getManagerphonenumber() {
		return managerphonenumber;
	}
	public void setManagerphonenumber(String managerphonenumber) {
		this.managerphonenumber = managerphonenumber;
	}
	public int getFloorarea() {
		return floorarea;
	}
	public void setFloorarea(int floorarea) {
		this.floorarea = floorarea;
	}
	public int getEmptyarea() {
		return emptyarea;
	}
	public void setEmptyarea(int emptyarea) {
		this.emptyarea = emptyarea;
	}
	public int getRentprice() {
		return rentprice;
	}
	public void setRentprice(int rentprice) {
		this.rentprice = rentprice;
	}
	public int getBrokeragefee() {
		return brokeragefee;
	}
	public void setBrokeragefee(int brokeragefee) {
		this.brokeragefee = brokeragefee;
	}
	public int getServicefee() {
		return servicefee;
	}
	public void setServicefee(int servicefee) {
		this.servicefee = servicefee;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	
	
}