package com.yy3913.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Camera {
	private Long id;
	private String name;
	
	@JsonProperty("rover_id")
	private Long rover_Id;
	
	@JsonProperty("full_name")
	private String fullName;
	
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
	public Long getRover_Id() {
		return rover_Id;
	}
	public void setRover_Id(Long rover_Id) {
		this.rover_Id = rover_Id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
