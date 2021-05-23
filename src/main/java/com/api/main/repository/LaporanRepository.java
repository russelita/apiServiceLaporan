package com.api.main.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.api.main.entity.Laporan;

public interface LaporanRepository extends JpaRepository<Laporan, Long> {
	
	List<Laporan> findAllByDatetimeBetween(
			Date dateTimeStart,
			Date dateTimeEnd);
	@Query("select d from laporan d where d.datetime <= :datetime")
    List<Laporan> findAllWithDatetimeBefore(
    @Param("datetime") Date datetime);
}
