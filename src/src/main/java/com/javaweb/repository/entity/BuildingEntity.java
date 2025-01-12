package com.javaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Id;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "building")
public class BuildingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "managername")
    private String managername;

    @Column(name = "managerphonenumber")
    private String managerphonenumber;

    @Column(name = "street")
    private String street;

    @Column(name = "ward")
    private String ward;

    @Column(name = "floorarea")
    private Long floorarea;

    @Column(name = "rentarea")
    private Long rentarea;

    @Column(name = "emptyarea")
    private Long emptyarea;

    @Column(name = "rentprice")
    private Long rentprice;

    @Column(name = "servicefee")
    private Long servicefee;

    @Column(name = "brokeragefee")
    private Long brokeragefee;

    @Column(name = "numberofbasement")
    private Long numberofbasement;
    
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "districtid", referencedColumnName = "id")
    private DistrictEntity district;

    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
    private List<RentAreaEntity> rents = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public Long getFloorarea() {
        return floorarea;
    }

    public void setFloorarea(Long floorarea) {
        this.floorarea = floorarea;
    }

    public Long getRentarea() {
        return rentarea;
    }

    public void setRentarea(Long rentarea) {
        this.rentarea = rentarea;
    }

    public Long getEmptyarea() {
        return emptyarea;
    }

    public void setEmptyarea(Long emptyarea) {
        this.emptyarea = emptyarea;
    }

    public Long getRentprice() {
        return rentprice;
    }

    public void setRentprice(Long rentprice) {
        this.rentprice = rentprice;
    }

    public Long getServicefee() {
        return servicefee;
    }

    public void setServicefee(Long servicefee) {
        this.servicefee = servicefee;
    }

    public Long getBrokeragefee() {
        return brokeragefee;
    }

    public void setBrokeragefee(Long brokeragefee) {
        this.brokeragefee = brokeragefee;
    }

    public Long getNumberofbasement() {
        return numberofbasement;
    }

    public void setNumberofbasement(Long numberofbasement) {
        this.numberofbasement = numberofbasement;
    }

    public DistrictEntity getDistrict() {
        return district;
    }

    public void setDistrict(DistrictEntity district) {
        this.district = district;
    }

	public List<RentAreaEntity> getRents() {
		return rents;
	}

	public void setRents(List<RentAreaEntity> rents) {
		this.rents = rents;
	}

    
}
