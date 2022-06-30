package com.baitaplon.bacsiservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baitaplon.bacsiservice.model.BacSi;
import com.baitaplon.bacsiservice.repository.BacSiRepository;



@Service
public class BacSiService {

	@Autowired
	private BacSiRepository bacSiRepo;

	public List<BacSi> listAll(){
		return bacSiRepo.findAll();
	}
	
	public void saveBacsi(BacSi bacSi) {
		bacSiRepo.save(bacSi);
	}
	
	public BacSi getBacsi(Integer id) {
		return bacSiRepo.findById(id).get();
		
	}
	
	public void deleteBacsi(Integer id) {
		bacSiRepo.deleteById(id);
	}
}
