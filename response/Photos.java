package com.yy3913.response;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Photos {
	private Long id;
	private Integer sol;
	private Camera camera;
	
	@JsonProperty("img_src")
	private String imgSrc;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonProperty("earth_date")
	private String earathDate;
	
	private Trav rover;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSol() {
		return sol;
	}

	public void setSol(Integer sol) {
		this.sol = sol;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	public String getEarathDate() {
		return earathDate;
	}

	public void setEarathDate(String earathDate) {
		this.earathDate = earathDate;
	}

	public Trav getRover() {
		return rover;
	}

	public void setRover(Trav rover) {
		this.rover = rover;
	}

	@Override
	public String toString() {
		return "Photos [id=" + id + ", sol=" + sol + ", camera=" + camera + ", imgSrc=" + imgSrc + ", earathDate="
				+ earathDate + ", rover=" + rover + "]";
	}


}
