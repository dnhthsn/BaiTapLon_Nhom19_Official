package com.baitaplon.lichkhamservice.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Data;

@Data
@Entity
@Table(name = "bacsi")
public class BacSi {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idbacsi;
	
	
	private String tenbs;
	private String thamnien;
	private String sdt;
}

