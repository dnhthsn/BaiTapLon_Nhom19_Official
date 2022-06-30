package com.baitaplon.bacsiservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baitaplon.bacsiservice.model.BacSi;
import com.baitaplon.bacsiservice.service.BacSiService;

@RestController
@RequestMapping(path = "/", produces = "application/json;charset=UTF-8")
public class BacSiServiceController {
	@Autowired
	private BacSiService bacSiService;
	
	@GetMapping("/bacsi")
	public List<BacSi> list(){
		return bacSiService.listAll();
	}
	
	@GetMapping("/bacsi/{id}")
	public ResponseEntity<BacSi> getBacsi(@PathVariable Integer id) {
		try {
			BacSi bacSi = bacSiService.getBacsi(id);
			return new ResponseEntity<BacSi>(bacSi, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<BacSi>(HttpStatus.NOT_FOUND);
		}
	}
	
//	@PostMapping("/bacsi")
//	public void add(@RequestBody BacSi bacSi) {
//		bacSiService.saveBacsi(bacSi);
//	}
//	
//	@PutMapping("/bacsi/{id}")
//	public ResponseEntity<?> update(@RequestBody BacSi bacSi, @PathVariable Integer id) {
//		try {
//			BacSi existBacsi = bacSiService.getBacsi(id);
//			bacSiService.saveBacsi(bacSi);
//			return new ResponseEntity<>(HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		
//	}
}
