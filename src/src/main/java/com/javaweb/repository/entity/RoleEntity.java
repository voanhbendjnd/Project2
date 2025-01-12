package com.javaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
@Table(name = "role")
public class RoleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(unique = true, nullable = false)
	private String code;
	
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	private List<UserEntity> users = new ArrayList<>();
//	private List<UserRoleEntity> userRoleEntity = new ArrayList<>();
//	public List<UserRoleEntity> getUserRoleEntity() {
//		return userRoleEntity;
//	}
//	public void setUserRoleEntity(List<UserRoleEntity> userRoleEntity) {
//		this.userRoleEntity = userRoleEntity;
//	}

	public List<UserEntity> getUsers() {
		return users;
	}
	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}
}
