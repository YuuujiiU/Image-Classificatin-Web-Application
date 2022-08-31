package com.yy3913.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yy3913.dto.HomeDto;

public interface PreferencesRepository extends JpaRepository<HomeDto, Long>{

	// @Query("select dto from HomeDto dto where userId = :userId")
	@Query(value = "select * from api_preferences where user_id = :userId", nativeQuery = true)
	HomeDto findByUserId(Long userId);

}
