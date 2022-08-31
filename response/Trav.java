package com.yy3913.response;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Trav {
	private Long id;
	private String name;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonProperty("landing_date")
	private String landingDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonProperty("launch_date")
	private String launchDate;
	
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

	public String getLandingDate() {
		return landingDate;
	}

	public void setLandingDate(String landingDate) {
		this.landingDate = landingDate;
	}

	public String getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(String launchDate) {
		this.launchDate = launchDate;
	}

	@Override
	public String toString() {
		return "Trav [id=" + id + ", name=" + name + ", landingDate=" + landingDate + ", launchDate=" + launchDate
				+ "]";
	}

	
}
