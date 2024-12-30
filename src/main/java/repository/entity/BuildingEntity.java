package repository.entity;

public class BuildingEntity {
	private String name;
	private String basement;
	private String ward;
	private String street;
	private String address, id;
	private int districtid, rentprice;
	public int getRentprice() {
		return rentprice;
	}
	public void setRentprice(int rentprice) {
		this.rentprice = rentprice;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	
	public int getDistrictid() {
		return districtid;
	}
	public void setDistrictid(int districtid) {
		this.districtid = districtid;
	}
	public String getAddress() {
		return address;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBasement() {
		return basement;
	}
	public void setBasement(String basement) {
		this.basement = basement;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getStreet() {
		
		return street;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddres() {
		return address;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	}
