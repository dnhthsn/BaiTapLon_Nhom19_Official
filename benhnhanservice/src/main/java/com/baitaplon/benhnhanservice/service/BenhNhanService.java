package com.baitaplon.benhnhanservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baitaplon.benhnhanservice.model.BenhNhan;
import com.baitaplon.benhnhanservice.repository.BenhNhanRepository;


@Service
public class BenhNhanService {

	@Autowired
	private BenhNhanRepository benhnhanRepo;
	
	
	
	public BenhNhan getBenhnhan(Integer id) {
		return benhnhanRepo.findById(id).get();
		
	}
	
	public BenhNhan getBenhNhanBySDT(String sdt) {
		return benhnhanRepo.findBySDT(sdt);
	}
	
	
}