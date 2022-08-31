package com.yy3913.response;

import java.util.ArrayList;
import java.util.List;

public class TravApiResponse {


	List<Photos> photos = new ArrayList<>();
	
	public List<Photos> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photos> photos) {
		this.photos = photos;
	}

	@Override
	public String toString() {
		return "TravApiResponse [photos=" + photos + "]";
	}
}
