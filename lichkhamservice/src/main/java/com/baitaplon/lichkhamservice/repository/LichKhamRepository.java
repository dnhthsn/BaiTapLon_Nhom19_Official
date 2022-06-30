package com.baitaplon.lichkhamservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.baitaplon.lichkhamservice.model.LichKham;


public interface LichKhamRepository extends JpaRepository<LichKham, Integer> {
	@Query("SELECT lich FROM LichKham lich WHERE lich.idbenhnhan = ?1")
	public List<LichKham> findLichKhamByIdBenhnhan(Integer id);
	
	@Query("SELECT lich FROM LichKham lich WHERE lich.idbacsi = ?1")
	public List<LichKham> findLichKhamByIdBacSi(Integer id);

	@Query("SELECT lich FROM LichKham lich WHERE lich.ngaykham = ?1")
	public List<LichKham> findLichKhamByNgay(String ngaydangky);
}
