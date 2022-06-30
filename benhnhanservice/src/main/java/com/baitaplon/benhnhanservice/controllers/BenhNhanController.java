package com.baitaplon.benhnhanservice.controllers;

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

import com.baitaplon.benhnhanservice.model.BenhNhan;
import com.baitaplon.benhnhanservice.service.BenhNhanService;


@RestController
@RequestMapping(path = "/", produces = "application/json;charset=UTF-8")
public class BenhNhanController {
	@Autowired
	private BenhNhanService benhNhanService;
	
	
	
	@GetMapping("/benhnhan/{id}")
	public ResponseEntity<BenhNhan> getBenhnhan(@PathVariable Integer id) {
		try {
			BenhNhan benhNhan = benhNhanService.getBenhnhan(id);
			return new ResponseEntity<BenhNhan>(benhNhan, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<BenhNhan>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/benhnhansdt/{sdt}")
	public ResponseEntity<BenhNhan> getBenhnhan(@PathVariable String sdt) {
		try {
			BenhNhan benhNhan = benhNhanService.getBenhNhanBySDT(sdt);
			return new ResponseEntity<BenhNhan>(benhNhan, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<BenhNhan>(HttpStatus.NOT_FOUND);
		}
	}
	
}
