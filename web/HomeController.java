package com.yy3913.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import java.lang.reflect.InvocationTargetException;

// import org.springframework.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yy3913.dto.HomeDto;
import com.yy3913.repository.PreferencesRepository;
import com.yy3913.response.TravApiResponse;
import com.yy3913.service.ApiService;


@Controller

public class HomeController {
		
	@Autowired
	private ApiService travService;
	
	@GetMapping("/")
	public String getHomeView(ModelMap model, Long userId, Boolean createUser) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		HomeDto homeDto = createDefaultHomeDto(userId);
		
		
		if(Boolean.TRUE.equals(createUser) && userId == null)
		{
			homeDto = travService.save(homeDto);
		}else {
			homeDto = travService.findByUserId(userId);
			if(homeDto == null)
			{
				homeDto =  createDefaultHomeDto(userId);
			}
		}
		
		
		TravApiResponse travData = travService.getData(homeDto);
		
		model.put("travData", travData);
		model.put("homeDto", homeDto);
		model.put("validCameras", travService.getValidCameras().get(homeDto.getMarsApiRoverData()));
		
		if(!Boolean.TRUE.equals(homeDto.getRememberPreferences()) && userId != null) {
			HomeDto defaultHomeDto = createDefaultHomeDto(userId);
			travService.save(defaultHomeDto);
		}
		
		return "index";
	}

	  @GetMapping("/savedPreferences")
	  @ResponseBody
	  public HomeDto getSavedPreferences (Long userId) {
	    if (userId != null)
	      return travService.findByUserId(userId);
	    else
	      return createDefaultHomeDto(userId);
	  }
	
	
	private HomeDto createDefaultHomeDto(Long userId) {
		HomeDto homeDto = new HomeDto();
		homeDto.setMarsApiRoverData("Opportunity");
		homeDto.setMarsSol(1);
		homeDto.setUserId(userId);
		return homeDto;
	}
	
	@PostMapping("/")
	public String postHomeView(HomeDto homeDto )
	{
//		if("curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData()))
//		{
//			
//		}
//		
//		if(homeDto.getMarsApiRoverData().equalsIgnoreCase("curiosity"))
//		{
//					
//		}
		
		homeDto = travService.save(homeDto);

		travService.save(homeDto);
		// System.out.println(homeDto);
		return "redirect:/?userId=" + homeDto.getUserId();
	}
	
}
