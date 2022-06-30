package com.baitaplon.lichkhamservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baitaplon.lichkhamservice.model.LichKham;
import com.baitaplon.lichkhamservice.service.LichKhamService;

@RestController
@RequestMapping(path = "/", produces = "application/json;charset=UTF-8")
public class LichKhamController {
	@Autowired
	private LichKhamService lichKhamService;
	
	@GetMapping("/lichkham/{ngaydangky}")
	public ResponseEntity<List<LichKham>> getAllLichKhamNgay(@PathVariable String ngaydangky) {
		try {
			List<LichKham> lichKhamNgay = lichKhamService.getLichKhamByNgay(ngaydangky);
			return new ResponseEntity<List<LichKham>>(lichKhamNgay, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<LichKham>>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping("/lichkhamcu/{id}")
	public ResponseEntity<LichKham> getLichKhamCu(@PathVariable Integer id) {
		try {
			List<LichKham> lichKhamCuList = lichKhamService.getLichKhamByIdBenhnhan(id);
			return new ResponseEntity<LichKham>(lichKhamCuList.get(lichKhamCuList.size()-1), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<LichKham>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/lichkhambacsi/{id}")
	public ResponseEntity<List<LichKham>> getLichKhamBacSi(@PathVariable Integer id) {
		try {
			List<LichKham> lichKham = lichKhamService.getLichKhamByIdBacSi(id);
			return new ResponseEntity<List<LichKham>>(lichKham, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<LichKham>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/lichkham/them", method = RequestMethod.POST)
	public void add(@RequestBody LichKham lichKham) {
		lichKhamService.saveLichKham(lichKham);
	}
}
