package com.api.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.api.main.entity.Buku;

public interface BukuRepository extends CrudRepository<Buku, Long> {
	@Query(value="Select * from buku where judul_buku Like %?1% " , nativeQuery=true)
	List<Buku> getDataByJudulBuku(String judulBuku);	
}
