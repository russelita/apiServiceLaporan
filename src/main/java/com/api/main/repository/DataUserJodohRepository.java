package com.api.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.main.entity.DataUserJodoh;

public interface DataUserJodohRepository extends JpaRepository<DataUserJodoh, Long> {
	@Query(value = "SELECT *\n"
			+ "from users\n"
			+ "Where (CASE "
			+ "WHEN 'username'=:type THEN username LIKE %:value% "
			+ "WHEN 'name'=:type THEN name LIKE %:value% "			
			+ "WHEN 'jenisKelamin'=:type THEN jenisKelamin LIKE %:value% "
			+ "WHEN 'phone'=:type THEN phone LIKE %:value% "
			+ "WHEN 'umur'=:type THEN umur LIKE %:value% "
			+ "WHEN 'image'=:type THEN image LIKE %:value% "
			+ "END)",nativeQuery=true)
	List<DataUserJodoh> findBySearchBy(@Param("type")String type,@Param("value")String value);
	
	@Query(value="SELECT * from users where username=?1 and phone=?2",nativeQuery = true)
	DataUserJodoh findByLogin(String username, String phone);
	
	DataUserJodoh findByJenisKelamin(String jenisKelamin);
	
	DataUserJodoh findByUsername(String username);
}
