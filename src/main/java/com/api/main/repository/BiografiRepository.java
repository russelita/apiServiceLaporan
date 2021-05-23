package com.api.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.api.main.entity.Biografi;

public interface BiografiRepository extends CrudRepository<Biografi, Long>{
	
	@Query(value="SELECT *\n"
			+"FROM user\n"
			+"WHERE (CASE "
			+"WHEN 'nama' =:type THEN nama LIKE %:value% "
			+"WHEN 'phone' =:type THEN phone LIKE %:value% "
			+"WHEN 'address' =:type THEN address LIKE %:value% "
			+"WHEN 'email' =:type THEN email LIKE %:value% "
			+"END)", nativeQuery=true)
	List<Biografi> findBySearchBy(@Param("type")String type,@Param("value")String value);

}
