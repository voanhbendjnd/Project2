package com.javaweb.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "rentarea")
public class RentAreaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "buildingid") // Tên cột khóa ngoại
    private BuildingEntity buildings;

    // Getters và Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

	public BuildingEntity getBuildings() {
		return buildings;
	}

	public void setBuildings(BuildingEntity buildings) {
		this.buildings = buildings;
	}

   
}