package com.baitaplon.lichkhamservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baitaplon.lichkhamservice.model.LichKham;
import com.baitaplon.lichkhamservice.repository.LichKhamRepository;


@Service
public class LichKhamService {
	@Autowired
	private LichKhamRepository lichKhamRepository;

	public List<LichKham> listAll(){
		return lichKhamRepository.findAll();
	}
	
	public void saveLichKham(LichKham lichKham) {
		lichKhamRepository.save(lichKham);
	}
	
	public List<LichKham> getLichKhamByIdBenhnhan(Integer id) {
		return lichKhamRepository.findLichKhamByIdBenhnhan(id);
		
	}
	public List<LichKham> getLichKhamByIdBacSi(Integer id) {
		return lichKhamRepository.findLichKhamByIdBacSi(id);
		
	}
	
	public List<LichKham> getLichKhamByNgay(String ngaydangky) {
		return lichKhamRepository.findLichKhamByNgay(ngaydangky);
	}
	
}
