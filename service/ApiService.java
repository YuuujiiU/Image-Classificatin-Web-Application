package com.yy3913.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.yy3913.dto.HomeDto;
import com.yy3913.repository.PreferencesRepository;
import com.yy3913.response.Photos;
import com.yy3913.response.TravApiResponse;

/**
 * @author Roujr
 *
 */
@Service
public class ApiService {
	
	private static final String API_KEY ="oLNPhjo0qodZILv1FQZXf4PLOJctVCBGn2jZp7Ya";
	
	  private Map<String, List<String>> validCameras = new HashMap<>();

	  @Autowired
	  private PreferencesRepository preferencesRepo;
	  
	  public ApiService() {
		  validCameras.put("Opportunity", Arrays.asList("FHAZ", "RHAZ", "NAVCAM", "PANCAM", "MINITES"));
		  validCameras.put("Curiosity", Arrays.asList("FHAZ", "RHAZ", "MAST", "CHEMCAM", "MAHLI", "MARDI", "NAVCAM"));
		  validCameras.put("Spirit", Arrays.asList("FHAZ", "RHAZ", "NAVCAM", "PANCAM", "MINITES"));
	  }
	
	public TravApiResponse getData(HomeDto homeDto) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		RestTemplate rt = new RestTemplate();

		List<String> apiUrlEnpoints = getApiUrlEnpoints(homeDto);
		List<Photos> photos = new ArrayList<>();
		TravApiResponse response = new TravApiResponse();
		
	    apiUrlEnpoints.stream()
        .forEach(url -> { 
          TravApiResponse apiResponse = rt.getForObject(url, TravApiResponse.class);
          photos.addAll(apiResponse.getPhotos());
        });
		
	    response.setPhotos(photos);
		// System.out.println(response.getBody());
		return response;
	}

	
	public List<String> getApiUrlEnpoints (HomeDto homeDto) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
	    List<String> urls = new ArrayList<>();
	    
	    Method[] methods = homeDto.getClass().getMethods();
	    
	    for (Method method : methods) {
	      if (method.getName().indexOf("getCamera") > -1 && Boolean.TRUE.equals(method.invoke(homeDto))) {
	        String cameraName = method.getName().split("getCamera")[1].toUpperCase();
	        if (validCameras.get(homeDto.getMarsApiRoverData()).contains(cameraName)) {
	          urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+homeDto.getMarsApiRoverData()+"/photos?sol="+homeDto.getMarsSol()+"&api_key=" + API_KEY + "&camera=" + cameraName);
	        }
	      }
	    }
		
//		if(Boolean.TRUE.equals(homeDto.getCameraFhaz()))
//		{
//			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/" + homeDto.getMarsApiRoverData() + "/photos?sol=" + homeDto.getMarsSol() + "&api_key=" + API_KEY + "&camera=FHAZ");
//		}
//		if(Boolean.TRUE.equals(homeDto.getCameraChemcam()))
//		{
//			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/" + homeDto.getMarsApiRoverData() + "/photos?sol=" + homeDto.getMarsSol() + "&api_key=" + API_KEY + "&camera=CHEMCAM");
//		}
//		
		
	    return urls;
	}

	public Map<String, List<String>> getValidCameras() {
		return validCameras;
	}

	public HomeDto save(HomeDto homeDto) {
		// TODO Auto-generated method stub
		return preferencesRepo.save(homeDto);
	}

	public HomeDto findByUserId(Long userId) {
		// TODO Auto-generated method stub
		return preferencesRepo.findByUserId(userId);
	}
	
	
}
