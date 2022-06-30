package com.baitaplon.benhnhanservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.baitaplon.benhnhanservice.model.BenhNhan;


public interface BenhNhanRepository extends JpaRepository<BenhNhan, Integer> {
	@Query("SELECT bn FROM BenhNhan bn WHERE bn.sdt = ?1")
	public BenhNhan findBySDT(String sdt);
}